package com.mo.fang.springcloudsystem.system.entity;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/25 0025
 */

public class LayTree {
    private String title;
    private Integer value;
    private boolean checked;
    private boolean disabled;
    private List<LayTree> data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public List<LayTree> getData() {
        return data;
    }

    public void setData(List<LayTree> data) {
        this.data = data;
    }
}
