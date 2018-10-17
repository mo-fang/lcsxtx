package com.mo.fang.springcloudsystem.system.serviceI;

import com.mo.fang.springcloudsystem.system.entity.Qualification;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/30 0030
 */

public interface QualificationService {
    List<Qualification> getQualifications(Qualification qualification);
}
