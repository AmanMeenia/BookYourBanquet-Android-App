package com.miet.eventsandbeyonds.user;

/**
 * Created by Aman on 1/21/2018.
 */

public class Banquet {
    private  String a_nameBanuet,a_address,a_hall,a_parking,imageUrl,id,rating,rate,squareaFet;
    private boolean favorite= false;

    public  Banquet(String a_nameBanuet,String a_address,String a_hall,String a_parking,String imageUrl,String id,String rating,String rate, String squareaFet)
    {
        this.a_nameBanuet=a_nameBanuet;
        this.a_address=a_address;
        this.a_hall=a_parking;
        this.imageUrl=imageUrl;
        this.id=id;
        this.rating=rating;
        this.rate=rate;
        this.squareaFet=squareaFet;
    }

    public String getA_nameBanuet() {
        return a_nameBanuet;
    }

    public void setA_nameBanuet(String a_nameBanuet) {
        this.a_nameBanuet = a_nameBanuet;
    }

    public String getA_address() {
        return a_address;
    }

    public void setA_address(String a_address) {
        this.a_address = a_address;
    }

    public String getA_hall() {
        return a_hall;
    }

    public void setA_hall(String a_hall) {
        this.a_hall = a_hall;
    }

    public String getA_parking() {
        return a_parking;
    }

    public void setA_parking(String a_parking) {
        this.a_parking = a_parking;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getSquareaFet() {
        return squareaFet;
    }

    public void setSquareaFet(String squareaFet) {
        this.squareaFet = squareaFet;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "Banquet{" +
                "a_nameBanuet='" + a_nameBanuet + '\'' +
                ", a_address='" + a_address + '\'' +
                ", a_hall='" + a_hall + '\'' +
                ", a_parking='" + a_parking + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", id='" + id + '\'' +
                ", rating='" + rating + '\'' +
                ", rate='" + rate + '\'' +
                ", squareaFet='" + squareaFet + '\'' +
                ", favorite=" + favorite +
                '}';
    }
}