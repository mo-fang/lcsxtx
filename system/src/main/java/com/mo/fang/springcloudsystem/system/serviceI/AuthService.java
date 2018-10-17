package com.mo.fang.springcloudsystem.system.serviceI;

import com.mo.fang.springcloudsystem.system.entity.Auth;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/25 0025
 */

public interface AuthService {
    boolean  insertBatch(@Param("mbList")List<Auth> list);
}
