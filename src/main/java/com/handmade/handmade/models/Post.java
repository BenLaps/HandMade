package com.handmade.handmade.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String sub_title;
    private String text;
    private String short_description;
    private String img_url;
    private String author;
    private String date_public;
    private int likes, views;

    public Post() {
    }

    public Post(String title, String sud_title, String img_url, String short_description, String text) {
        this.title = title;
        this.sub_title = sud_title;
        this.text = text;
        this.short_description = short_description;
        this.img_url = img_url;
    }


}
