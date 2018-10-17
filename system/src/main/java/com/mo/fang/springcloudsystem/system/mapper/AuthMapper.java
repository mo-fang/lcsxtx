package com.mo.fang.springcloudsystem.system.mapper;

import com.mo.fang.springcloudsystem.system.entity.Auth;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    Auth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    int deleteByTypeAndOwnerId(@Param("type") String type, @Param("ownerid")Integer ownerid);

    List<Integer> getMenuAndButtonIdByAuthTypeAndOwnerId(@Param("type") String type,@Param("ownerid")Integer ownerid);

    int  insertBatch(@Param("auths")List<Auth> list);
}