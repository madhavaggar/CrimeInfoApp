package com.example.game.deltatask3v2;

public class Crime_Favourite {
    private int id;
    private String category;


    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Crime_Favourite(int id, String category) {
        this.id = id;
        this.category = category;
    }
}
