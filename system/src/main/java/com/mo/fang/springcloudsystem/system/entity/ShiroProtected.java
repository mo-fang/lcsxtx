package com.mo.fang.springcloudsystem.system.entity;

/**
 * 描述:oa_sys_shiro表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-09-17
 */
public class ShiroProtected {
    /**
     * id
     */
    private Integer id;

    /**
     * 资源名称--路径
     */
    private String name;

    /**
     * 保护的类型
     */
    private String type;

    /**
     *  加载的顺序
     */
    private Integer sequence;

    /**
     * 备注
     */
    private String mark;

    /**
     * id
     * @return id id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 资源名称--路径
     * @return name 资源名称--路径
     */
    public String getName() {
        return name;
    }

    /**
     * 资源名称--路径
     * @param name 资源名称--路径
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 保护的类型
     * @return type 保护的类型
     */
    public String getType() {
        return type;
    }

    /**
     * 保护的类型
     * @param type 保护的类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     *  加载的顺序
     * @return sequence  加载的顺序
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     *  加载的顺序
     * @param sequence  加载的顺序
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * 备注
     * @return mark 备注
     */
    public String getMark() {
        return mark;
    }

    /**
     * 备注
     * @param mark 备注
     */
    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }
}