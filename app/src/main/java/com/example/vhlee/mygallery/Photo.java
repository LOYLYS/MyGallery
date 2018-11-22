package com.example.vhlee.mygallery;

public class Photo {
    private String mUrl;
    private String mTitle;

    public Photo(String mUrl, String mTitle) {
        this.mUrl = mUrl;
        this.mTitle = mTitle;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

}
