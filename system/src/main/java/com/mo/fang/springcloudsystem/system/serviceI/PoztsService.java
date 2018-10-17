package com.mo.fang.springcloudsystem.system.serviceI;

import com.mo.fang.springcloudsystem.system.entity.Pozts;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/30 0030
 */

public interface PoztsService {
    List<Pozts> getPoztses(Pozts pozts);

}
