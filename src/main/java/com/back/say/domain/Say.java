package com.back.say.domain;

public class Say {

    private int id;
    private String author;
    private String content;

    public Say(int id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return id + " / " + author + " / " + content;
    }
}
