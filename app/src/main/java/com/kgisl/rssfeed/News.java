package com.kgisl.rssfeed;

public class News {
    private String date;
    private String title;
    private String content;

    public News(){}

    public News(String date, String title, String content) {
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public String toString() {
        return "{" +
                " date='" + getDate() + "'" +
                ", title='" + getTitle() + "'" +
                ", content='" + getContent() + "'" +
                "}";
    }

}