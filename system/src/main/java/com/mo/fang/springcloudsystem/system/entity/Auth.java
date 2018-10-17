package com.mo.fang.springcloudsystem.system.entity;

import java.util.Date;

/**
 * 描述:oa_sys_auth表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-09-25
 */
public class Auth {
    /**
     * 
     */
    private Integer id;

    /**
     * 拥有权限的id(角色或者是用户)
     */
    private Integer ownerid;

    /**
     * 又有角色的名称（角色名称或者是用户的名称）
     */
    private String ownername;

    /**
     * 按钮和菜单资源的权限
     */
    private Integer mbid;

    /**
     * 类型1-角色 2-用户
     */
    private String type;

    /**
     * 最后修改者的时间
     */
    private Date updatetime;

    /**
     * 最后的修改者
     */
    private String updateuser;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 拥有权限的id(角色或者是用户)
     * @return ownerid 拥有权限的id(角色或者是用户)
     */
    public Integer getOwnerid() {
        return ownerid;
    }

    /**
     * 拥有权限的id(角色或者是用户)
     * @param ownerid 拥有权限的id(角色或者是用户)
     */
    public void setOwnerid(Integer ownerid) {
        this.ownerid = ownerid;
    }

    /**
     * 又有角色的名称（角色名称或者是用户的名称）
     * @return ownername 又有角色的名称（角色名称或者是用户的名称）
     */
    public String getOwnername() {
        return ownername;
    }

    /**
     * 又有角色的名称（角色名称或者是用户的名称）
     * @param ownername 又有角色的名称（角色名称或者是用户的名称）
     */
    public void setOwnername(String ownername) {
        this.ownername = ownername == null ? null : ownername.trim();
    }

    /**
     * 按钮和菜单资源的权限
     * @return mbid 按钮和菜单资源的权限
     */
    public Integer getMbid() {
        return mbid;
    }

    /**
     * 按钮和菜单资源的权限
     * @param mbid 按钮和菜单资源的权限
     */
    public void setMbid(Integer mbid) {
        this.mbid = mbid;
    }

    /**
     * 类型1-角色 2-用户
     * @return type 类型1-角色 2-用户
     */
    public String getType() {
        return type;
    }

    /**
     * 类型1-角色 2-用户
     * @param type 类型1-角色 2-用户
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 最后修改者的时间
     * @return updatetime 最后修改者的时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 最后修改者的时间
     * @param updatetime 最后修改者的时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 最后的修改者
     * @return updateuser 最后的修改者
     */
    public String getUpdateuser() {
        return updateuser;
    }

    /**
     * 最后的修改者
     * @param updateuser 最后的修改者
     */
    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }
}