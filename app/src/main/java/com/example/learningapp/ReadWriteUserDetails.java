package com.example.learningapp;

import java.io.Serializable;

public class ReadWriteUserDetails implements Serializable {

    public  String fullname , dob , mobile;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public  ReadWriteUserDetails(String textFullName, String textDoB, String textMobile)
    {
        this.fullname = textFullName;
        this.dob = textDoB;
        this.mobile = textMobile;

    }
}
