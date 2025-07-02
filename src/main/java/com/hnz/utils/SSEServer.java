package com.hnz.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * @Author：hnz
 * @Project：ds-doctor
 * @name：SSEServer
 * @Date：2025/6/29 20:33
 * @Filename：SSEServer
 */

@Slf4j
public class SSEServer {
    private static final Map<String, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();
    // 在线人数
    private static AtomicInteger onlineCounts = new AtomicInteger(0);

    public static SseEmitter connection(String userId) {
        // 0l 表示不设置超时时间, 默认30秒
        SseEmitter sseEmitter = new SseEmitter(0L);
        // 注册sse的回调函数
        sseEmitter.onCompletion(CompletionCallback(userId));
        sseEmitter.onError(ErrorCallback(userId));
        sseEmitter.onTimeout(TimeOutCallback(userId));
        sseEmitterMap.put(userId, sseEmitter);
        log.info("sse连接成功, userId: {}", userId);
        onlineCounts.getAndIncrement();
        return sseEmitter;
    }

    private static Runnable CompletionCallback(String userId){
        return () -> {
            removeConnection(userId);
            log.info("sse连接断开, userId: {}", userId);
        };
    }
    private static Runnable TimeOutCallback(String userId){
        return () -> {
            removeConnection(userId);
            log.info("sse连接超时, userId: {}", userId);
        };
    }
    private static Consumer<Throwable> ErrorCallback(String userId){
        return Throwable -> {
            removeConnection(userId);
            log.info("sse连接超时, userId: {}", userId);
        };
    }
    private static void removeConnection(String userId){
        onlineCounts.getAndDecrement();
        sseEmitterMap.remove(userId);

    }

    public static void sendMessage(String userId, String message, SSEMessageType type){
        if (CollectionUtils.isEmpty(sseEmitterMap)){
            return;
        }
        if (sseEmitterMap.containsKey(userId)){
            SseEmitter sseEmitter = sseEmitterMap.get(userId);
            sendEmitterMessage(sseEmitter, userId, message, type);
        }
    }
    public static void sendMessageToAllUsers(String message){
        if (CollectionUtils.isEmpty(sseEmitterMap)){
            return;
        }
        sseEmitterMap.forEach((userId, sseEmitter) -> {
            sendEmitterMessage(sseEmitter, userId, message, SSEMessageType.MESSAGE);
        });
    }
    public static void sendEmitterMessage(SseEmitter sseEmitter, String userId, String message, SSEMessageType type){
        try {
            // 添加连接状态检查
            if (sseEmitter == null || sseEmitterMap.get(userId) != sseEmitter) {
                log.debug("忽略无效连接: {}", userId);
                return;
            }
            System.out.printf("发送消息: {%s}", message);
            SseEmitter.SseEventBuilder msg = SseEmitter.event()
                    .id(userId)
                    .data(message)
                    .name(type.getType());
            sseEmitter.send(msg);
        } catch (IOException e) {
            // 完整关闭连接
            try {
                sseEmitter.completeWithError(e);
            } catch (IllegalStateException ex) {
                log.debug("连接已关闭: {}", userId);
            }
            removeConnection(userId);
        }
    }
    public static Integer getConnectCount(){
        return onlineCounts.intValue();
    }
    public static void stopServerByUser(String userId){
        if(CollectionUtils.isEmpty(sseEmitterMap)){
            return;
        }
        SseEmitter sseEmitter = sseEmitterMap.get(userId);
        if(sseEmitter != null){
            sseEmitter.complete();
//            removeConnection(userId);
            log.info("【{}】已关闭sse连接", userId);
        }else {
            log.warn("【{}】sse连接不存在", userId);
        }
    }
}
