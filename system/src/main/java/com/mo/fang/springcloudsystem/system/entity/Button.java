package com.mo.fang.springcloudsystem.system.entity;

/**
 * 描述:oa_sys_button表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-09-18
 */
public class Button {
    /**
     * 代码
     */
    private Integer id;

    /**
     * 按钮名称
     */
    private String buttonName;

    /**
     * 按钮备注
     */
    private String mark;

    /**
     * 按钮简称
     */
    private String shortname;

    /**
     * 按钮icon
     */
    private String icon;

    /**
     * 代码
     * @return ID 代码
     */
    public Integer getId() {
        return id;
    }

    /**
     * 代码
     * @param id 代码
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 按钮名称
     * @return BUTTON_NAME 按钮名称
     */
    public String getButtonName() {
        return buttonName;
    }

    /**
     * 按钮名称
     * @param buttonName 按钮名称
     */
    public void setButtonName(String buttonName) {
        this.buttonName = buttonName == null ? null : buttonName.trim();
    }

    /**
     * 按钮备注
     * @return MARK 按钮备注
     */
    public String getMark() {
        return mark;
    }

    /**
     * 按钮备注
     * @param mark 按钮备注
     */
    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    /**
     * 按钮简称
     * @return SHORTNAME 按钮简称
     */
    public String getShortname() {
        return shortname;
    }

    /**
     * 按钮简称
     * @param shortname 按钮简称
     */
    public void setShortname(String shortname) {
        this.shortname = shortname == null ? null : shortname.trim();
    }

    /**
     * 按钮icon
     * @return ICON 按钮icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 按钮icon
     * @param icon 按钮icon
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }
}