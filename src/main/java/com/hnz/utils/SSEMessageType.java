package com.hnz.utils;

import lombok.Data;

/**
 * @Author：hnz
 * @Project：ds-doctor
 * @name：SSEMessageType
 * @Date：2025/6/30 10:01
 * @Filename：SSEMessageType
 */


public enum SSEMessageType {
    MESSAGE("message", "单次发送的普通消息"),
    ADD("add", "消息追加，用于流式stream推送"),
    FINISH("finish", "结束，用于结束流式stream推送"),
    DONE("done", "完成，用于结束流式stream推送");
    private String type;
    private String value;
    SSEMessageType(String type, String value) {
        this.type = type;
        this.value = value;
    }
    public String getType() {
        return type;
    }
    public String getValue() {
        return value;
    }
}
