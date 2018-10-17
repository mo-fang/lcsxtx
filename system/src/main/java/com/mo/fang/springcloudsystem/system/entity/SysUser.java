package com.mo.fang.springcloudsystem.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述:oa_sys_user表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-09-30
 */
public class SysUser implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 部门id
     */
    private Integer departmentid;

    /**
     * 岗位id
     */
    private Integer postid;

    /**
     * 身份证号码
     */
    private String sfzh;

    /**
     * 入职时间
     */
    private String hiredate;

    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 学历
     */
    private Integer qualificationid;

    /**
     * 婚否 1-婚0-否
     */
    private String havewife;

    /**
     * 电话
     */
    private String phonum;

    /**
     * 性别编码
     */
    private String sexcode;

    /**
     * 家庭住址
     */
    private String addr;

    /**
     * 紧急联系人
     */
    private String sosname;

    /**
     * 紧急人联系方式
     */
    private String sosphonnum;

    /**
     * 操作时间
     */
    private Date updatetime;

    /**
     * 最后操作人
     */
    private String updateuser;

    /**
     *
     */
    private String enable;

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
     * 用户名
     * @return username 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 用户名
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 姓名
     * @return name 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 部门id
     * @return departmentid 部门id
     */
    public Integer getDepartmentid() {
        return departmentid;
    }

    /**
     * 部门id
     * @param departmentid 部门id
     */
    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * 岗位id
     * @return postid 岗位id
     */
    public Integer getPostid() {
        return postid;
    }

    /**
     * 岗位id
     * @param postid 岗位id
     */
    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    /**
     * 身份证号码
     * @return sfzh 身份证号码
     */
    public String getSfzh() {
        return sfzh;
    }

    /**
     * 身份证号码
     * @param sfzh 身份证号码
     */
    public void setSfzh(String sfzh) {
        this.sfzh = sfzh == null ? null : sfzh.trim();
    }

    /**
     * 入职时间
     * @return hiredate 入职时间
     */
    public String getHiredate() {
        return hiredate;
    }

    /**
     * 入职时间
     * @param hiredate 入职时间
     */
    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    /**
     * 出生日期
     * @return birthday 出生日期
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 出生日期
     * @param birthday 出生日期
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 学历
     * @return qualificationid 学历
     */
    public Integer getQualificationid() {
        return qualificationid;
    }

    /**
     * 学历
     * @param qualificationid 学历
     */
    public void setQualificationid(Integer qualificationid) {
        this.qualificationid = qualificationid;
    }

    /**
     * 婚否 1-婚0-否
     * @return havewife 婚否 1-婚0-否
     */
    public String getHavewife() {
        return havewife;
    }

    /**
     * 婚否 1-婚0-否
     * @param havewife 婚否 1-婚0-否
     */
    public void setHavewife(String havewife) {
        this.havewife = havewife == null ? null : havewife.trim();
    }

    /**
     * 电话
     * @return phonum 电话
     */
    public String getPhonum() {
        return phonum;
    }

    /**
     * 电话
     * @param phonum 电话
     */
    public void setPhonum(String phonum) {
        this.phonum = phonum == null ? null : phonum.trim();
    }

    /**
     * 性别编码
     * @return sexcode 性别编码
     */
    public String getSexcode() {
        return sexcode;
    }

    /**
     * 性别编码
     * @param sexcode 性别编码
     */
    public void setSexcode(String sexcode) {
        this.sexcode = sexcode == null ? null : sexcode.trim();
    }

    /**
     * 家庭住址
     * @return addr 家庭住址
     */
    public String getAddr() {
        return addr;
    }

    /**
     * 家庭住址
     * @param addr 家庭住址
     */
    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    /**
     * 紧急联系人
     * @return sosname 紧急联系人
     */
    public String getSosname() {
        return sosname;
    }

    /**
     * 紧急联系人
     * @param sosname 紧急联系人
     */
    public void setSosname(String sosname) {
        this.sosname = sosname == null ? null : sosname.trim();
    }

    /**
     * 紧急人联系方式
     * @return sosphonnum 紧急人联系方式
     */
    public String getSosphonnum() {
        return sosphonnum;
    }

    /**
     * 紧急人联系方式
     * @param sosphonnum 紧急人联系方式
     */
    public void setSosphonnum(String sosphonnum) {
        this.sosphonnum = sosphonnum == null ? null : sosphonnum.trim();
    }

    /**
     * 操作时间
     * @return updatetime 操作时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 操作时间
     * @param updatetime 操作时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 最后操作人
     * @return updateuser 最后操作人
     */
    public String getUpdateuser() {
        return updateuser;
    }

    /**
     * 最后操作人
     * @param updateuser 最后操作人
     */
    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }

    /**
     *
     * @return enable
     */
    public String getEnable() {
        return enable;
    }

    /**
     *
     * @param enable
     */
    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }
}