package com.mo.fang.springcloudsystem.system.contracts;

import feign.Contract;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * create by Mofang_ysc on 2018/9/3 0003
 */
@Configuration
public class FileFeignConfiguration {
    /**
     * Contract 契约
     *
     * @return
     */
//    @Bean
//    public Contract feignConfiguration() {
//        return new feign.Contract.Default();
//    }


    @Bean
    @Primary
    @Scope("prototype")
    public Encoder multipartFormEncoder() {
        return new SpringFormEncoder();
    }

//    @Bean
//    public feign.Logger.Level multipartLoggerLevel() {
//        return feign.Logger.Level.FULL;
//    }


}
