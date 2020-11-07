package com.just.ytdemo.exception;

import lombok.Data;

/**
 * @author yutao.yan
 * @date 2018/12/4 13:46
 */
@Data
public class BaseException extends RuntimeException{


    private BaseErrorCode baseErrorCode;

    private Long code;

    private String msg;


    public BaseException(BaseErrorCode baseErrorCode) {
        this.baseErrorCode = baseErrorCode;
        this.code = baseErrorCode.getCode();
        this.msg = baseErrorCode.getMsg();
    }

    public BaseException(Long code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
