package com.mo.fang.springcloudsystem.system.entity;

/**
 * 描述:oa_sys_pozts表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-09-30
 */
public class Pozts {
    /**
     * 
     */
    private Integer id;

    /**
     * 职位名称
     */
    private String name;

    /**
     * 所属部门id
     */
    private Integer departmentid;

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
     * 职位名称
     * @return name 职位名称
     */
    public String getName() {
        return name;
    }

    /**
     * 职位名称
     * @param name 职位名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 所属部门id
     * @return departmentid 所属部门id
     */
    public Integer getDepartmentid() {
        return departmentid;
    }

    /**
     * 所属部门id
     * @param departmentid 所属部门id
     */
    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }
}