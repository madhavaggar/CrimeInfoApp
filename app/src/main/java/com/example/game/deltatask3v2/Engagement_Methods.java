package com.example.game.deltatask3v2;

import com.google.gson.annotations.SerializedName;

public class Engagement_Methods {
    @SerializedName("url")
    String url;
    @SerializedName("description")
    String description;
    @SerializedName("title")
    String title;

    public Engagement_Methods(String url, String description, String title) {
        this.url = url;
        this.description = description;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}