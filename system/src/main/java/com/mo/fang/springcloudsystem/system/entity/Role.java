package com.mo.fang.springcloudsystem.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述:oa_sys_role表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-09-22
 */
public class Role  implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 角色代码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 插入时间
     */
    private Date inserttime;

    /**
     * 插入人
     */
    private String insertusername;

    /**
     * 
     * @return ID 
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
     * 角色代码
     * @return CODE 角色代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 角色代码
     * @param code 角色代码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 角色名称
     * @return NAME 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 角色名称
     * @param name 角色名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 插入时间
     * @return INSERTTIME 插入时间
     */
    public Date getInserttime() {
        return inserttime;
    }

    /**
     * 插入时间
     * @param inserttime 插入时间
     */
    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    /**
     * 插入人
     * @return INSERTUSERNAME 插入人
     */
    public String getInsertusername() {
        return insertusername;
    }

    /**
     * 插入人
     * @param insertusername 插入人
     */
    public void setInsertusername(String insertusername) {
        this.insertusername = insertusername == null ? null : insertusername.trim();
    }
}