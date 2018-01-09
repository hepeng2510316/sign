package com.demo.hibernate.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SIGN_USER", catalog = "sign")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NIKE_NAME", nullable = false)
    private String nickName;

    @Column(name = "OPEN_ID", nullable = false, unique = true)
    private String openId;

    @Column(name = "AVATAR_URL", nullable = false)
    private String avatarUrl;

    @Column(name = "CONTINUE_SIGN_COUNT")
    private int continueSignCount;

    @Column(name = "TOTAL_SIGN_COUNT")
    private int totalSignCount;

    public Account() {
    }

    public Account(String nickName, String openId, String avatarUrl, int continueSignCount, int totalSignCount) {
        this.nickName = nickName;
        this.openId = openId;
        this.avatarUrl = avatarUrl;
        this.continueSignCount = continueSignCount;
        this.totalSignCount = totalSignCount;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getContinueSignCount() {
        return continueSignCount;
    }

    public void setContinueSignCount(int continueSignCount) {
        this.continueSignCount = continueSignCount;
    }

    public int getTotalSignCount() {
        return totalSignCount;
    }

    public void setTotalSignCount(int totalSignCount) {
        this.totalSignCount = totalSignCount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", openId='" + openId + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", continueSignCount=" + continueSignCount +
                ", totalSignCount=" + totalSignCount +
                '}';
    }
}
