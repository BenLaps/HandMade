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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String text;
    private String short_description;
    private String img_url;
    private String author;
    private String date_public;
    private int likes, views;
    private boolean isInPrice;

    public Product (){}

    public Product(String title, String text, String short_description, String img_url, boolean isInPrice) {
        this.title = title;
        this.text = text;
        this.short_description = short_description;
        this.img_url = img_url;
        this.isInPrice = isInPrice;
    }

}
