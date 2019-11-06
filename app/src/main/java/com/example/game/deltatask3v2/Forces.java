package com.example.game.deltatask3v2;

import com.google.gson.annotations.SerializedName;

public class Forces {
    @SerializedName("id")
    private String idno;
    @SerializedName("name")
    private String name;

    public Forces(String a, String b){
        idno=a;
        name=b;
    }
    public void setID(String a){
        idno=a;
    }
    public String getID(){
        return idno;
    }
    public void setName(String a){
        name=a;
    }
    public String getName(){
        return name;
    }
}

