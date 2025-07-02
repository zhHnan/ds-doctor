package com.hnz.service;

import com.hnz.utils.ChatTypeEnum;
import com.hnz.utils.SSEMessageType;
import com.hnz.utils.SSEServer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author：hnz
 * @Project：ds-doctor
 * @name：OllamaService
 * @Date：2025/6/29 19:53
 * @Filename：OllamaService
 */
@Slf4j
@Service
public class OllamaService {
    @Resource
    OllamaChatClient ollamaChatClient;
    @Resource
    ChatRecordService chatRecordService;
    public String toChat(String question) {
        return ollamaChatClient.call(question);
    }
    public Flux<ChatResponse> toStreamChatV1(String question) {
        Prompt prompt = new Prompt(new UserMessage(question));
        return ollamaChatClient.stream(prompt);
    }
    public List<String> toStreamChatV2(String question) {
        Prompt prompt = new Prompt(new UserMessage(question));
        Flux<ChatResponse> stream = ollamaChatClient.stream(prompt);
        return stream.toStream().map(res -> {
            String content = res.getResult().getOutput().getContent();
            log.info("content: {}", content);
            return content;
        }).collect(Collectors.toList());
    }

    public void doDoctorStream(String userName, String message){
        chatRecordService.saveChatRecord(userName, message, ChatTypeEnum.USER);
        Prompt prompt = new Prompt(new UserMessage(message));
        Flux<ChatResponse> stream = ollamaChatClient.stream(prompt);
        List<String> list = stream.toStream().map(res -> {
            String content = res.getResult().getOutput().getContent();
            SSEServer.sendMessage(userName, content, SSEMessageType.ADD);
            return content;
        }).toList();
        StringBuilder botRecord = new StringBuilder();
        for (String s : list) {
            botRecord.append(s);
        }
        chatRecordService.saveChatRecord(userName, botRecord.toString(), ChatTypeEnum.BOT);
        SSEServer.sendMessage(userName, "done", SSEMessageType.DONE);
    }
}
