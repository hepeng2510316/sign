package com.demo.hibernate.beans;



import java.util.Date;
import java.util.List;

public class SignRecordResult {

    private Integer id;

    private String openId;

    private String singInfo;

    private Date signTime;

    //附加
    private String nickName;

    private String avatarUrl;

    private int totalSignCount;

    private boolean isMineLike;

    private int likeCount;

    private List<Comment> list;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getTotalSignCount() {
        return totalSignCount;
    }

    public void setTotalSignCount(int totalSignCount) {
        this.totalSignCount = totalSignCount;
    }

    public boolean isMineLike() {
        return isMineLike;
    }

    public void setMineLike(boolean mineLike) {
        isMineLike = mineLike;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public List<Comment> getList() {
        return list;
    }

    public void setList(List<Comment> list) {
        this.list = list;
    }


    public static class Comment{


        private Integer id;

        private Integer recordId;

        private String openId;

        private String commentInfo;

        private String nickName;

        public Comment() {
        }

        public Comment(Integer id, Integer recordId, String openId, String commentInfo, String nickName) {
            this.id = id;
            this.recordId = recordId;
            this.openId = openId;
            this.commentInfo = commentInfo;
            this.nickName = nickName;
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

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }

    @Override
    public String toString() {
        return "SignRecordResult{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", singInfo='" + singInfo + '\'' +
                ", signTime=" + signTime +
                ", nickName='" + nickName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", totalSignCount=" + totalSignCount +
                ", isMineLike=" + isMineLike +
                ", likeCount=" + likeCount +
                ", list=" + list +
                '}';
    }
}
