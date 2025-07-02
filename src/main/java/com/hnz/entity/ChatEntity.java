package com.hnz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author：hnz
 * @Project：ds-doctor
 * @name：ChatEntity
 * @Date：2025/6/30 17:29
 * @Filename：ChatEntity
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChatEntity {

    private String currentUserName;
    private String message;
}
