package com.demo.hibernate.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "COMMENT", catalog = "sign")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "RECORD_ID", nullable = false)
    private Integer recordId;

    @Column(name = "OPEN_ID", nullable = false)
    private String openId;

    @Column(name = "COMMENT_INFO", nullable = false)
    private String commentInfo;

    public Comment() {
    }

    public Comment(Integer recordId, String openId, String commentInfo) {
        this.recordId = recordId;
        this.openId = openId;
        this.commentInfo = commentInfo;
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

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", recordId=" + recordId +
                ", openId='" + openId + '\'' +
                ", commentInfo='" + commentInfo + '\'' +
                '}';
    }
}
