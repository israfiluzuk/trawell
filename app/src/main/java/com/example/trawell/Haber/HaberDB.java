package com.example.trawell.Haber;

public class HaberDB {

    public String author;
    public String title;
    public String desc;
    public String publishedAt;
    public String source;
    public String time;
    public String img;

    public HaberDB() {
    }

    public HaberDB(String author, String title, String desc, String publishedAt, String source, String time, String img) {
        this.author = author;
        this.title = title;
        this.desc = desc;
        this.publishedAt = publishedAt;
        this.source = source;
        this.time = time;
        //this.img = img;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
