package com.example.pd4;

public class PopularItem {
    private String Name;
    private String Artist;
    private String Image;
    private String Price;
    private String Track_Listing;

    public PopularItem(String name, String artist, String image, String price, String track_Listing) {
        Name = name;
        Artist = artist;
        Image = image;
        Price = price;
        Track_Listing = track_Listing;
    }

    public PopularItem() {}

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getTrack_Listing() {
        return Track_Listing;
    }

    public void setTrack_Listing(String track_Listing) {
        Track_Listing = track_Listing;
    }
}
