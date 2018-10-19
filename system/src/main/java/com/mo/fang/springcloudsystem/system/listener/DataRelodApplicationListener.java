package com.mo.fang.springcloudsystem.system.listener;

import com.mo.fang.springcloudsystem.system.event.LoadingDataEvent;
import com.mo.fang.springcloudsystem.system.serviceImpl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class DataRelodApplicationListener implements ApplicationListener<LoadingDataEvent> {
    @Autowired
    private RedisServiceImpl redisService;
    @Value("${oa.redis.system-data.prefix}")
    private String PREFIX;
    @Async
    @Override
    public void onApplicationEvent(LoadingDataEvent applyEvent) {
        if (applyEvent.getMenuMapper()!=null)
            applyEvent.menu_reload(redisService,PREFIX);

        if (applyEvent.getMenuAndButtonMapper()!=null)
            applyEvent.setMenuAndButton_reload(redisService,PREFIX);

        if (applyEvent.getCategoryMapper()!=null)
            applyEvent.setCategory_reload(redisService,PREFIX);

        if (applyEvent.getSysParaMapper()!=null)
            applyEvent.setSysPara_reload(redisService,PREFIX);

        if (applyEvent.getDepartmentMapper()!=null)
            applyEvent.setDepartment_reload(redisService,PREFIX);

        if (applyEvent.getRoleMapper()!=null)
            applyEvent.setRole_reload(redisService,PREFIX);
    }
}
