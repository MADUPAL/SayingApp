package com.back.say;

import java.util.Objects;

public class Say {

    private int id;
    private String author;
    private String content;

    public Say(String author, String content) {
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

    public void setId(int id) {
        this.id = id;
    }
}
