package com.ha_store.dto;

public class Option {
    private String url_img;
    private String title;


    public Option() {
    }

    public Option(String url_img, String title) {
        this.url_img = url_img;
        this.title = title;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
