package com.hnz.controller;

import com.hnz.entity.ChatEntity;
import com.hnz.service.ChatRecordService;
import com.hnz.service.OllamaService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @Author：hnz
 * @Project：ds-doctor
 * @name：OllamaController
 * @Date：2025/6/29 19:24
 * @Filename：OllamaController
 */
@Slf4j
@RestController
@RequestMapping("ollama")
public class OllamaController {
    @Resource
    private ChatRecordService chatRecordService;
    @Resource
    private OllamaService ollamaService;
    @GetMapping("/chat")
    public Object Chat(@RequestParam String question) {
        return ollamaService.toChat(question);
    }
    @GetMapping("/stream")
    public Flux<ChatResponse> Stream(@RequestParam String question) {
        return ollamaService.toStreamChatV1(question);
    }
    @GetMapping("/toStream")
    public List<String> ToStream(@RequestParam String question) {
        return ollamaService.toStreamChatV2(question);
    }
    @PostMapping("/doctor/stream")
    public void DoctorStream(@RequestBody ChatEntity chatEntity) {
        log.info("chatEntity: {}", chatEntity.toString());
        ollamaService.doDoctorStream(chatEntity.getCurrentUserName(), chatEntity.getMessage());
    }
    @GetMapping("/getRecords")
    public Object GetChatRecordList(@RequestParam String who) {
        return chatRecordService.getChatRecordList(who);
    }
}
