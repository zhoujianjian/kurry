package com.cancer.star.db.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user")
public class User {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 1 男 2 女
     */
    private Boolean gender;

    /**
     * 头像
     */
    @Column(name = "head_icon")
    private String headIcon;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 出生年月日
     */
    private Date birthday;

    /**
     * 登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 0 正常 1 删除
     */
    @Column(name = "is_delete")
    private Boolean isDelete;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建时间(注册时间)
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取名字
     *
     * @return name - 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字
     *
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取1 男 2 女
     *
     * @return gender - 1 男 2 女
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     * 设置1 男 2 女
     *
     * @param gender 1 男 2 女
     */
    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    /**
     * 获取头像
     *
     * @return head_icon - 头像
     */
    public String getHeadIcon() {
        return headIcon;
    }

    /**
     * 设置头像
     *
     * @param headIcon 头像
     */
    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon == null ? null : headIcon.trim();
    }

    /**
     * 获取密码
     *
     * @return pwd - 密码
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置密码
     *
     * @param pwd 密码
     */
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    /**
     * 获取出生年月日
     *
     * @return birthday - 出生年月日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生年月日
     *
     * @param birthday 出生年月日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取登录时间
     *
     * @return login_time - 登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登录时间
     *
     * @param loginTime 登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取0 正常 1 删除
     *
     * @return is_delete - 0 正常 1 删除
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 设置0 正常 1 删除
     *
     * @param isDelete 0 正常 1 删除
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取创建时间(注册时间)
     *
     * @return create_time - 创建时间(注册时间)
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间(注册时间)
     *
     * @param createTime 创建时间(注册时间)
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}