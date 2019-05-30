package com.example.trawell.Posts;

public class PostDB {
    public String heritage_city;
    public String heritage_date;
    public String heritage_name;
    public String heritage_desc;
    public String heritage_img;

    public PostDB() {
    }

    public PostDB(String heritage_city, String heritage_date, String heritage_name, String heritage_desc, String heritage_img) {
        this.heritage_city = heritage_city;
        this.heritage_date = heritage_date;
        this.heritage_name = heritage_name;
        this.heritage_desc = heritage_desc;
        this.heritage_img = heritage_img;
    }

    public String getHeritage_city() {
        return heritage_city;
    }

    public void setHeritage_city(String heritage_city) {
        this.heritage_city = heritage_city;
    }

    public String getHeritage_date() {
        return heritage_date;
    }

    public void setHeritage_date(String heritage_date) {
        this.heritage_date = heritage_date;
    }

    public String getHeritage_name() {
        return heritage_name;
    }

    public void setHeritage_name(String heritage_name) {
        this.heritage_name = heritage_name;
    }

    public String getHeritage_desc() {
        return heritage_desc;
    }

    public void setHeritage_desc(String heritage_desc) {
        this.heritage_desc = heritage_desc;
    }

    public String getHeritage_img() {
        return heritage_img;
    }

    public void setHeritage_img(String heritage_img) {
        this.heritage_img = heritage_img;
    }
}

/*
TextView heritage_city, heritage_date, heritage_name, heritage_desc;
ImageView heritage_img;
 */