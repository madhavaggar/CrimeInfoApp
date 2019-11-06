package com.example.game.deltatask3v2;

import com.google.gson.annotations.SerializedName;

public class Outcome_Status{

    @SerializedName("category")
    private String category;
    @SerializedName("date")
    private String date;

    public Outcome_Status(String category, String date) {
        this.category = category;
        this.date = date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }
}