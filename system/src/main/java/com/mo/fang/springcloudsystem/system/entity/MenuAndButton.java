package com.mo.fang.springcloudsystem.system.entity;

/**
 * 描述:oa_sys_mandb表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-09-21
 */
public class MenuAndButton {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer menuId;

    /**
     * button的id
     */
    private Integer buttonId;

    /**
     * 
     */
    private String menuCode;

    /**
     * button的简称
     */
    private String btnshortname;
    /**
     * button的名称
     */
    private String btnName;
    /**
     * menu的名称
     */
    private String menuName;

    public MenuAndButton(Integer menuId, Integer buttonId, String menuCode, String btnshortname, String btnName, String menuName) {
        this.menuId = menuId;
        this.buttonId = buttonId;
        this.menuCode = menuCode;
        this.btnshortname = btnshortname;
        this.btnName = btnName;
        this.menuName = menuName;
    }

    public MenuAndButton(Integer id, Integer menuId, Integer buttonId, String menuCode, String btnshortname, String btnName, String menuName) {
        this.id = id;
        this.menuId = menuId;
        this.buttonId = buttonId;
        this.menuCode = menuCode;
        this.btnshortname = btnshortname;
        this.btnName = btnName;
        this.menuName = menuName;
    }

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

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
     *
     * @return MENU_ID
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     *
     * @param menuId
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /**
     * button的id
     * @return BUTTON_ID button的id
     */
    public Integer getButtonId() {
        return buttonId;
    }

    /**
     * button的id
     * @param buttonId button的id
     */
    public void setButtonId(Integer buttonId) {
        this.buttonId = buttonId;
    }

    /**
     *
     * @return MENU_CODE
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     *
     * @param menuCode
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    /**
     * button的简称
     * @return BTNSHORTNAME button的简称
     */
    public String getBtnshortname() {
        return btnshortname;
    }

    /**
     * button的简称
     * @param btnshortname button的简称
     */
    public void setBtnshortname(String btnshortname) {
        this.btnshortname = btnshortname == null ? null : btnshortname.trim();
    }

    public MenuAndButton() {
    }

    public MenuAndButton(Integer id, Integer menuId, Integer buttonId, String menuCode, String btnshortname) {
        this.id = id;
        this.menuId = menuId;
        this.buttonId = buttonId;
        this.menuCode = menuCode;
        this.btnshortname = btnshortname;
    }

    public MenuAndButton(Integer menuId, Integer buttonId, String menuCode, String btnshortname) {
        this.menuId = menuId;
        this.buttonId = buttonId;
        this.menuCode = menuCode;
        this.btnshortname = btnshortname;
    }
}