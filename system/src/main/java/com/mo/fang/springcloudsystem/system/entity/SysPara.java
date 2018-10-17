package com.mo.fang.springcloudsystem.system.entity;

import java.util.Date;

/**
 * 描述:sys_para表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-09-28
 */
public class SysPara {
    /**
     * 
     */
    private Integer id;

    /**
     * 参数名
     */
    private String name;

    /**
     * 参数值
     */
    private String value;

    /**
     * 是否生效
     */
    private String enable;

    /**
     * 插入或者修改时间
     */
    private Date updatetime;

    /**
     * 操作人
     */
    private String insertuser;

    /**
     * 备注
     */
   private String mark;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

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
     * 参数名
     * @return name 参数名
     */
    public String getName() {
        return name;
    }

    /**
     * 参数名
     * @param name 参数名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 参数值
     * @return value 参数值
     */
    public String getValue() {
        return value;
    }

    /**
     * 参数值
     * @param value 参数值
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * 是否生效
     * @return enable 是否生效
     */
    public String getEnable() {
        return enable;
    }

    /**
     * 是否生效
     * @param enable 是否生效
     */
    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    /**
     * 插入或者修改时间
     * @return updatetime 插入或者修改时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 插入或者修改时间
     * @param updatetime 插入或者修改时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 操作人
     * @return insertuser 操作人
     */
    public String getInsertuser() {
        return insertuser;
    }

    /**
     * 操作人
     * @param insertuser 操作人
     */
    public void setInsertuser(String insertuser) {
        this.insertuser = insertuser == null ? null : insertuser.trim();
    }
}