package com.example.learningapp;

import android.widget.Toast;

import java.io.Serializable;

public class ReadWriteDeviceDetails implements Serializable {
    public String device_name;
    public String device_model;
    public String device_price;

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_model() {
        return device_model;
    }

    public void setDevice_model(String device_model) {
        this.device_model = device_model;
    }

    public String getDevice_price() {
        return device_price;
    }

    public void setDevice_price(String device_price) {
        this.device_price = device_price;
    }


    public ReadWriteDeviceDetails(String device,String model, String price)
    {
        this.device_name = device;
        this.device_model = model;
        this.device_price = price;

    }


}
