package com.example.basedaya2;

import java.net.URI;

public class ImageList {
   private String name;
    private String uri;
    private Integer price;

    public ImageList() {
    }

    public ImageList(String name, String uri, Integer price) {
        this.name = name;
        this.uri = uri;
        this.price = price;
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
