package com.example.ginni.mynotesapp;

import java.io.Serializable;

public class Notes implements Serializable{

    private String title,time,note;

    Notes() {

    }

    public void addNote(String title,String note) {
        this.title = title;
        this.note = note;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String  getNote() {
        return note;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setNote(String note) {
        this.note = note;
    }



}
