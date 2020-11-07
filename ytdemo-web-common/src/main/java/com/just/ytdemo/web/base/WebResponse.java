package com.just.ytdemo.web.base;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.just.ytdemo.exception.BaseErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Web层调用返回通用结构
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WebResponse<T> implements Serializable {

    private static final long serialVersionUID = -9009950023183744177L;

    /**
     * 返回值 0：正确 ：其他 不正确
     */
    private Long code;

    /**
     * 操作消息
     */
    @Setter
    private String msg;

    private T data;

    public static WebResponse ok() {
        return from(BaseErrorCode.SUCCESS);
    }

    public static <T> WebResponse<T> ok(T data) {
        return from(BaseErrorCode.SUCCESS, data);
    }

    public static WebResponse error(String errorMsg) {
        WebResponse instance = from(BaseErrorCode.FAILED);
        instance.setMsg(errorMsg);
        return instance;
    }

    public static WebResponse from(BaseErrorCode errorCode) {
        return from(errorCode, null);
    }

    public static <K> WebResponse<K> from(BaseErrorCode errorCode, K data) {
        return from(errorCode, errorCode.getMsg(), data);
    }

    public static <K> WebResponse<K> from(BaseErrorCode errorCode, String message, K data) {
        return new WebResponse<>(errorCode.getCode(), StringUtils.isBlank(message) ? errorCode.getMsg() : message, data);
    }

}
