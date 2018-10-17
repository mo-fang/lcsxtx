package com.mo.fang.springcloudsystem.system.serviceImpl;

import com.mo.fang.springcloudsystem.system.entity.Qualification;
import com.mo.fang.springcloudsystem.system.mapper.QualificationMapper;
import com.mo.fang.springcloudsystem.system.serviceI.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/30 0030
 */

@Service
public class QualificationServiceImpl implements QualificationService {
    @Autowired
    private QualificationMapper qualificationMapper;
    @Override
    public List<Qualification> getQualifications(Qualification qualification) {
        return qualificationMapper.getQualifications(qualification);
    }
}
