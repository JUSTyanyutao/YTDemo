package com.just.ytdemo.web.config;

import com.just.ytdemo.web.exception.GlobalExceptionHandler;
import com.just.ytdemo.web.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  配置
 * @author yutao.yan
 * @date 2018/12/4 17:10
 */
@Configuration
@EnableWebMvc
public class MvcBeanConfig implements WebMvcConfigurer {


//    /**
//     * 全局异常声明
//     * @return
//     */
//    @Bean("handlerExceptionResolver")
//    public HandlerExceptionResolver handlerExceptionResolver() {
//        return new GlobalExceptionHandler();
//    }



    @Bean
    public FilterRegistrationBean registration(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new LoginFilter());//实例化Filter类
        filterRegistrationBean.addUrlPatterns("/*");//设置匹配模式,这里设置为所有，可以按需求设置为"/hello"等等
        filterRegistrationBean.setName("LogCostFilter");//设置过滤器名称
        filterRegistrationBean.setOrder(1);//设置执行顺序
        return filterRegistrationBean;
    }




}
