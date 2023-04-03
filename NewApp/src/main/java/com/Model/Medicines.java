package com.Model;

/**
 * Created by NaqviPC on 1/8/2020.
 */

public class Medicines {
    private String mname, description, price, image, mdid, date, time;

    public Medicines(){

    }

    public Medicines(String mname, String description, String price, String image, String mdid, String date, String time) {
        this.mname = mname;
        this.description = description;
        this.price = price;
        this.image = image;
        this.mdid = mdid;
        this.date = date;
        this.time = time;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMdid() {
        return mdid;
    }

    public void setMdid(String mdid) {
        this.mdid = mdid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
