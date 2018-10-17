package com.mo.fang.springcloudsystem.system.entity;

import java.util.Date;

/**
 * 描述:oa_mall_category表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-09-18
 */
public class Category {
    /**
     * 
     */
    private Integer id;

    /**
     * 父类id 当id为0的时候说明为根节点顶级
     */
    private Integer parentid;

    /**
     * 类别名称 
     */
    private String name;

    /**
     * 类别的状态 0的时候废弃  1的时候使用
     */
    private String status;

    /**
     * 排序编号 展示顺序 当数字相同的时候 听天由命
     */
    private String sortorder;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 最后一次操作时间
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
     * 父类id 当id为0的时候说明为根节点顶级
     * @return parentid 父类id 当id为0的时候说明为根节点顶级
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * 父类id 当id为0的时候说明为根节点顶级
     * @param parentid 父类id 当id为0的时候说明为根节点顶级
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    /**
     * 类别名称 
     * @return name 类别名称 
     */
    public String getName() {
        return name;
    }

    /**
     * 类别名称 
     * @param name 类别名称 
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 类别的状态 0的时候废弃  1的时候使用
     * @return status 类别的状态 0的时候废弃  1的时候使用
     */
    public String getStatus() {
        return status;
    }

    /**
     * 类别的状态 0的时候废弃  1的时候使用
     * @param status 类别的状态 0的时候废弃  1的时候使用
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 排序编号 展示顺序 当数字相同的时候 听天由命
     * @return sortorder 排序编号 展示顺序 当数字相同的时候 听天由命
     */
    public String getSortorder() {
        return sortorder;
    }

    /**
     * 排序编号 展示顺序 当数字相同的时候 听天由命
     * @param sortorder 排序编号 展示顺序 当数字相同的时候 听天由命
     */
    public void setSortorder(String sortorder) {
        this.sortorder = sortorder == null ? null : sortorder.trim();
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
     * 最后一次操作时间
     * @return updatetime 最后一次操作时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 最后一次操作时间
     * @param updatetime 最后一次操作时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}