package com.demo.hibernate.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SIGN_RECORD", catalog = "sign")
public class SignRecord implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "OPEN_ID", nullable = false)
    private String openId;

    @Column(name = "SING_INFO", nullable = false)
    private String singInfo;

    @Column(name = "SIGN_TIME", nullable = false)
    private Date signTime;


    public SignRecord() {
    }

    public SignRecord(String openId, String singInfo, Date signTime) {
        this.openId = openId;
        this.singInfo = singInfo;
        this.signTime = signTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSingInfo() {
        return singInfo;
    }

    public void setSingInfo(String singInfo) {
        this.singInfo = singInfo;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    @Override
    public String toString() {
        return "SignRecord{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", singInfo='" + singInfo + '\'' +
                ", signTime=" + signTime +
                '}';
    }
}
