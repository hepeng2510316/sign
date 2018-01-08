package com.demo.hibernate.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "LIKE", catalog = "sign")
public class Like implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "RECORD_ID", nullable = false)
    private Integer recordId;

    @Column(name = "OPEN_ID", nullable = false)
    private String openId;

    public Like() {
    }

    public Like( String openId,Integer recordId) {
        this.recordId = recordId;
        this.openId = openId;
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

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", recordId=" + recordId +
                ", openId='" + openId + '\'' +
                '}';
    }
}
