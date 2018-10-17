package entity;

import java.util.Date;

public class SysRole {
    private Integer id;

    private String code;

    private String name;

    private Date inserttime;

    private String insertusername;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public String getInsertusername() {
        return insertusername;
    }

    public void setInsertusername(String insertusername) {
        this.insertusername = insertusername == null ? null : insertusername.trim();
    }
}