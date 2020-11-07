package com.just.ytdemo.schedule;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author yutao.yan
 * @date 2018/6/20 18:37
 *
 * https://www.cnblogs.com/weiqihome/p/8922937.html
 */
@Component
public class InitTask implements InitializingBean {

    /**
     * 初始化InitTask 时默认调用的方法
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("我进来了 ------  init");
    }

    public void test(){
        System.out.println("测试 -------- init");
    }
}
