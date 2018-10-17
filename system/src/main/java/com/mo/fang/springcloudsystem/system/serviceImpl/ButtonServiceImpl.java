package com.mo.fang.springcloudsystem.system.serviceImpl;

import com.mo.fang.springcloudsystem.system.entity.Button;
import com.mo.fang.springcloudsystem.system.mapper.ButtonMapper;
import com.mo.fang.springcloudsystem.system.serviceI.ButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/21 0021
 */
@Service
public class ButtonServiceImpl implements ButtonService {
    @Autowired
    private ButtonMapper buttonMapper;
    @Override
    public List<Button> getAllButtons() {
        return buttonMapper.getAllButtons();
    }
}
