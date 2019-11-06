package com.example.game.deltatask3v2;

import com.google.gson.annotations.SerializedName;

public class Spec_Forces {
    @SerializedName("engagement_methods")
    private Engagement_Methods eng_meth[]= new Engagement_Methods[4];
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private  String name;
    @SerializedName("description")
    private String description;
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    @SerializedName("url")
    private String url;
    @SerializedName("telephone")
    private int telephone;

    public void setEngagement_methods(Engagement_Methods[] eng_meth) {
        this.eng_meth = eng_meth;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public Spec_Forces(Engagement_Methods[] eng_meth, String id, String name, String description, String url, int telephone) {
        this.eng_meth=eng_meth;
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.telephone = telephone;
    }


    public Engagement_Methods[] getEng_Meth() {
        return eng_meth;
    }

    public Engagement_Methods getEng_Meth(int i) {
        return eng_meth[i];
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public int getTelephone() {
        return telephone;
    }
}
