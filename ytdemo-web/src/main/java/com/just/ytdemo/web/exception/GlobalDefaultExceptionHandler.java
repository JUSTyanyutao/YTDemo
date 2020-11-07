package com.just.ytdemo.web.exception;

import com.just.ytdemo.exception.BaseErrorCode;
import com.just.ytdemo.web.base.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yutao.yan
 * @date 2018/12/6 10:26
 */
@RestControllerAdvice
@Slf4j
public class GlobalDefaultExceptionHandler {


    @ExceptionHandler(Exception.class)
//    @ResponseBody
    public WebResponse defaultExceptionHandler(Exception e) {

        if (e instanceof IllegalArgumentException) {
            IllegalArgumentException exception = (IllegalArgumentException) e;
            log.error("Illegal argument exception", e);
            return WebResponse.from(BaseErrorCode.PARAM_EXCEPTION);
        } else if (e instanceof BindException) {
            String msg = ((BindException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage();
            msg = "".equals(msg) ? "fail":msg;
            return WebResponse.from(BaseErrorCode.PARAM_EXCEPTION, msg,null);
        }

        log.error("Unexpected exception", e);
        return WebResponse.error(BaseErrorCode.FAILED.getMsg());
    }

}
