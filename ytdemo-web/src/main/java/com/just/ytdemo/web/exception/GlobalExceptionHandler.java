package com.just.ytdemo.web.exception;

import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.just.ytdemo.exception.BaseErrorCode;
import com.just.ytdemo.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 全局异常处理类
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        FastJsonJsonView view = new FastJsonJsonView();
        view.setAttributesMap(buildException(ex));
        return new ModelAndView(view);
    }


    private Map<String, Object> buildException(Exception ex) {
        log.error("globalExceptionHandler catch exception", ex);
        if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) ex;
            Set<ConstraintViolation<?>> cvs = cve.getConstraintViolations();
            return buildResultMap(BaseErrorCode.PARAM_EXCEPTION.getCode(), cvs.iterator().next().getMessage());
        } else if (ex instanceof BaseException) {
            BaseException be = (BaseException) ex;
            return buildResultMap(be.getCode(), be.getMsg());
        } else if (ex instanceof RpcException) {
            return buildResultMap(((RpcException) ex).getCode(), ex.getMessage());
        } else if (ex instanceof IllegalArgumentException) {
            return buildResultMap(BaseErrorCode.PARAM_EXCEPTION.getCode(), ex.getMessage());
        } else if (ex instanceof BindException) {
            String msg = ((BindException) ex).getBindingResult().getAllErrors().get(0).getDefaultMessage();
            msg = "".equals(msg) ? "fail":msg;
            return buildResultMap(BaseErrorCode.PARAM_EXCEPTION.getCode(), msg);
        } else if ( ex instanceof HttpMessageNotReadableException) {
            return buildResultMap(BaseErrorCode.PARAM_EXCEPTION.getCode(), "参数异常");
        } else{
            return buildResultMap(BaseErrorCode.FAILED.getCode(), BaseErrorCode.FAILED.getMsg());
        }
    }

    private static Map<String, Object> buildResultMap(long code, String msg) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("code", code);
        attributes.put("msg", msg);
        attributes.put("data",null);
        return attributes;
    }
}

