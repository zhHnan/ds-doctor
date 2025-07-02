package com.hnz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hnz.entity.ChatRecord;
import com.hnz.mapper.ChatRecordMapper;
import com.hnz.utils.ChatTypeEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author：hnz
 * @Project：ds-doctor
 * @name：ChatRecordService
 * @Date：2025/6/30 19:36
 * @Filename：ChatRecordService
 */


@Service
public class ChatRecordService {

    @Resource
    private ChatRecordMapper chatRecordMapper;

    public void saveChatRecord(String userName, String message, ChatTypeEnum chatType) {

        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setFamilyMember(userName);
        chatRecord.setContent(message);
        chatRecord.setChatType(chatType.type);
        chatRecord.setChatTime(LocalDateTime.now());

        chatRecordMapper.insert(chatRecord);
    }

    public List<ChatRecord> getChatRecordList(String who) {

        QueryWrapper<ChatRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("family_member", who);

        return chatRecordMapper.selectList(queryWrapper);
    }
}
