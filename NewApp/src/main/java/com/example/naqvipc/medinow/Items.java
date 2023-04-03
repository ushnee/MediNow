package com.example.naqvipc.medinow;

/**
 * Created by NaqviPC on 1/8/2020.
 */

public class Items {

    String name;
    Long amount;
    String image;

    public Items( Long amount,String name,String image) {
        this.name = name;
        this.amount = amount;
        this.image=image;

    }

    public Items() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getImage() {
        return image;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
