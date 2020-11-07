package com.just.ytdemo.exception;

import lombok.Data;

/**
 * @author yutao.yan
 * @date 2018/12/4 13:48
 */

public enum  BaseErrorCode {

    SUCCESS(0L,"SUCCESS"),
    FAILED(19930001L,"System Error"),
    PARAM_EXCEPTION(19930002L,"参数异常");

    private Long code;
    private String msg;

    BaseErrorCode(Long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
