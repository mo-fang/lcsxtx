package com.mo.fang.springcloudsystem.system.entity;

import java.util.Date;

/**
 * 描述:oa_sys_department表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-09-30
 */
public class Department {
    /**
     * 
     */
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父级部门的id
     */
    private Integer parentid;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 创建人
     */
    private String createname;

    /**
     * 更新时间
     */
    private Date updatetime;

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
     * 部门名称
     * @return name 部门名称
     */
    public String getName() {
        return name;
    }

    /**
     * 部门名称
     * @param name 部门名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 父级部门的id
     * @return parentid 父级部门的id
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * 父级部门的id
     * @param parentid 父级部门的id
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    /**
     * 创建时间
     * @return createtime 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 创建人
     * @return createname 创建人
     */
    public String getCreatename() {
        return createname;
    }

    /**
     * 创建人
     * @param createname 创建人
     */
    public void setCreatename(String createname) {
        this.createname = createname == null ? null : createname.trim();
    }

    /**
     * 更新时间
     * @return updatetime 更新时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 更新时间
     * @param updatetime 更新时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}