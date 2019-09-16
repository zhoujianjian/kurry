package com.cancer.star.db.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "appointment")
public class Appointment {
    @Id
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private String mobile;

    /**
     * 1男2女
     */
    private Boolean gender;

    /**
     * 0 待开始  1 进行中 2 已结束 
     */
    private Boolean status;

    /**
     * 预约日期
     */
    @Column(name = "appointment_date")
    private Date appointmentDate;

    /**
     * 预约开始时间
     */
    @Column(name = "appointment_start_dt")
    private Date appointmentStartDt;

    /**
     * 预约结束时间
     */
    @Column(name = "appointment_end_dt")
    private Date appointmentEndDt;

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
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取1男2女
     *
     * @return gender - 1男2女
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     * 设置1男2女
     *
     * @param gender 1男2女
     */
    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    /**
     * 获取0 待开始  1 进行中 2 已结束 
     *
     * @return status - 0 待开始  1 进行中 2 已结束 
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置0 待开始  1 进行中 2 已结束 
     *
     * @param status 0 待开始  1 进行中 2 已结束 
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 获取预约日期
     *
     * @return appointment_date - 预约日期
     */
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * 设置预约日期
     *
     * @param appointmentDate 预约日期
     */
    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    /**
     * 获取预约开始时间
     *
     * @return appointment_start_dt - 预约开始时间
     */
    public Date getAppointmentStartDt() {
        return appointmentStartDt;
    }

    /**
     * 设置预约开始时间
     *
     * @param appointmentStartDt 预约开始时间
     */
    public void setAppointmentStartDt(Date appointmentStartDt) {
        this.appointmentStartDt = appointmentStartDt;
    }

    /**
     * 获取预约结束时间
     *
     * @return appointment_end_dt - 预约结束时间
     */
    public Date getAppointmentEndDt() {
        return appointmentEndDt;
    }

    /**
     * 设置预约结束时间
     *
     * @param appointmentEndDt 预约结束时间
     */
    public void setAppointmentEndDt(Date appointmentEndDt) {
        this.appointmentEndDt = appointmentEndDt;
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