package com.baayso.springboot.config;

import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttpClientHttpRequestFactory;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import com.baayso.commons.security.AESCoder;
import com.baayso.commons.spring.SpringUtils;

/**
 * 公共配置。
 *
 * @author ChenFangjie (2016/4/11 15:52)
 * @since 1.0.0
 */
@Configuration
public class CommonConfig {

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttpClientHttpRequestFactory());
    }

    @Bean
    public SpringUtils springUtils() {
        return new SpringUtils();
    }

    @Bean
    public AESCoder aesCoder() {
        return new AESCoder("G7jX/RpqbqzbvUoxJ2fEaVdhk8e/axGXbhEXli2dR0TI=");
    }

}