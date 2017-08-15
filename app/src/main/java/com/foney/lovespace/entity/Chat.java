package com.foney.lovespace.entity;

import java.util.Date;

/**
 * Created by foney on 2017/8/15.
 */

public class Chat {

    private Integer id;
    private String content;
    private String photoUrl;
    private String radioUrl;
    private Integer chatType;
    private Integer fromCustomerId;
    private Integer toCustomerId;
    private Integer coupleId;
    private Date createTime;
    private Integer createCustomerId;
    private Integer createType;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getRadioUrl() {
        return radioUrl;
    }

    public void setRadioUrl(String radioUrl) {
        this.radioUrl = radioUrl;
    }

    public Integer getChatType() {
        return chatType;
    }

    public void setChatType(Integer chatType) {
        this.chatType = chatType;
    }

    public Integer getFromCustomerId() {
        return fromCustomerId;
    }

    public void setFromCustomerId(Integer fromCustomerId) {
        this.fromCustomerId = fromCustomerId;
    }

    public Integer getToCustomerId() {
        return toCustomerId;
    }

    public void setToCustomerId(Integer toCustomerId) {
        this.toCustomerId = toCustomerId;
    }

    public Integer getCoupleId() {
        return coupleId;
    }

    public void setCoupleId(Integer coupleId) {
        this.coupleId = coupleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateCustomerId() {
        return createCustomerId;
    }

    public void setCreateCustomerId(Integer createCustomerId) {
        this.createCustomerId = createCustomerId;
    }

    public Integer getCreateType() {
        return createType;
    }

    public void setCreateType(Integer createType) {
        this.createType = createType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
