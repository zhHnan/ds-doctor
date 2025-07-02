package com.hnz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @Author：hnz
 * @Project：ds-doctor
 * @name：ChatRecord
 * @Date：2025/6/30 19:25
 * @Filename：ChatRecord
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChatRecord {

    private String id;
    private String content;
    private String chatType;
    private LocalDateTime chatTime;
    private String familyMember;
}
