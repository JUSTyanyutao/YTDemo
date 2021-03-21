package com.just.ytdemo.service;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author yutao.yan
 * @date 2018/12/3 18:05
 */
@Component
public class BeanColorFctory implements FactoryBean {

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public Object getObject() throws Exception {
        return new BeanColor();
    }

    @Override
    public Class<?> getObjectType() {
        return BeanColor.class;
    }
}
