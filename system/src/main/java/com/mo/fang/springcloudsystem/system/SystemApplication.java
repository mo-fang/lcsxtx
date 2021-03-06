package com.mo.fang.springcloudsystem.system;

import com.mo.fang.springcloudsystem.system.listener.ApplicationStartupListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan(value = "com.mo.fang.springcloudsystem.system.mapper")
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SystemApplication.class);
        springApplication.addListeners(new ApplicationStartupListener());
        springApplication.run(args);
    }
}

