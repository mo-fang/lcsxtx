package com.mo.fang.springcloudsystem.system.serviceImpl;

import com.mo.fang.springcloudsystem.system.entity.Pozts;
import com.mo.fang.springcloudsystem.system.mapper.PoztsMapper;
import com.mo.fang.springcloudsystem.system.serviceI.PoztsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/30 0030
 */

@Service
public class PoztsServiceImpl implements PoztsService {
    @Autowired
    private PoztsMapper poztsMapper;
    @Override
    public List<Pozts> getPoztses(Pozts pozts) {
        return poztsMapper.getPoztses(pozts);
    }
}
