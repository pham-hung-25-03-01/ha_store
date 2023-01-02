package com.ha_store.dto;

import java.io.Serializable;

public class Photo implements Serializable {
    private String img_url;

    public Photo(String img_url) {
        this.img_url = img_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
