package com.yunyi.activityqueue.bean;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: qizhou
 * Date: 7/19/14
 * Time: 12:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class Activity {

    private Integer id;

    private String text2;

    private String notes0;

    private String createdBy;

    private Date dateCreate;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getNotes0() {
        return notes0;
    }

    public void setNotes0(String notes0) {
        this.notes0 = notes0;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }


}
