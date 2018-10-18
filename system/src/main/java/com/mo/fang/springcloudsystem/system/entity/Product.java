package com.mo.fang.springcloudsystem.system.entity;

/**
 * 描述:product表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-10-18
 */
public class Product {
    /**
     * 
     */
    private Integer id;

    /**
     * 产品码
     */
    private String code;

    /**
     * 产品名称
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
     * 产品码
     * @return code 产品码
     */
    public String getCode() {
        return code;
    }

    /**
     * 产品码
     * @param code 产品码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 产品名称
     * @return name 产品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 产品名称
     * @param name 产品名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}