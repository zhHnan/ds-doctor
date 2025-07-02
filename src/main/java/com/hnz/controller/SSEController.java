package com.hnz.controller;

import com.hnz.utils.SSEMessageType;
import com.hnz.utils.SSEServer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @Author：hnz
 * @Project：ds-doctor
 * @name：SSEController
 * @Date：2025/6/29 20:53
 * @Filename：SSEController
 */


@RestController
@RequestMapping("sse")
public class SSEController {

    @GetMapping(path = "/connect", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter Connect(String userId) {
        return SSEServer.connection(userId);
    }
    @GetMapping("/sendMessage")
    public Object SendMessage(@RequestParam String userId, @RequestParam String message) {
        SSEServer.sendMessage(userId, message, SSEMessageType.MESSAGE);
        return "ok";
    }
    @GetMapping("/sendMessageAdd")
    public Object SendMessageAdd(@RequestParam String userId, @RequestParam String message) throws InterruptedException {
        for (int i = 0; i < 10; i++){
            Thread.sleep(200);
            SSEServer.sendMessage(userId, message + i, SSEMessageType.ADD);
        }
        return "ok";
    }

    @GetMapping("/sendMessageAll")
    public Object SendMessageAll(@RequestParam String message) {
        SSEServer.sendMessageToAllUsers(message);
        return "ok";
    }

    @GetMapping("/stopServer")
    public Object StopServer(@RequestParam String userId) {
        SSEServer.stopServerByUser(userId);
        return "ok";
    }

    @GetMapping("/getOnlineCount")
    public Integer GetOnlineCount() {
        return SSEServer.getConnectCount();
    }
}
