package com.android.project_androidapp.Domain;


//Class nay de tao ra Ten va Ten_anh_background de truyen sang Adapter, class nay khong can quan tam lam
public class categoryList {
    private String name;
    private String picture;

    public categoryList(String name, String picture) {
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
