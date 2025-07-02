package com.hnz.utils;

/**
 * @Author：hnz
 * @Project：ds-doctor
 * @name：ChatTypeEnum
 * @Date：2025/6/30 19:39
 * @Filename：ChatTypeEnum
 */
public enum ChatTypeEnum {

    USER("user", "用户发的内容"),
    BOT("bot", "AI回复的内容");

    public final String type;
    public final String value;

    ChatTypeEnum(String type, String value) {
        this.type = type;
        this.value = value;
    }
}
