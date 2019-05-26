package com.example.pd4;

public class Genres {
    private String name;
    private int image;

    public Genres(String Name, int Image) {
        name = Name;
        image = Image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int Image) {
        image = Image;
    }
}
