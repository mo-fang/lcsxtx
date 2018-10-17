package com.mo.fang.springcloudsystem.system.entity;

/**
 * 描述:oa_sys_qualification表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-09-30
 */
public class Qualification {
    /**
     * 
     */
    private Integer id;

    /**
     * 学历名称
     */
    private String name;

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
     * 学历名称
     * @return name 学历名称
     */
    public String getName() {
        return name;
    }

    /**
     * 学历名称
     * @param name 学历名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}