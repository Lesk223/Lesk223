package com.example.basedaya2;

import java.net.URI;

public class ImageList {
   private String name;
    private String uri;
    private String owner;
    private Integer price;

    public ImageList() {
    }

    public ImageList(String name, String uri, Integer price,String owner) {
        this.name = name;
        this.uri = uri;
        this.owner=owner;
        this.price = price;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
