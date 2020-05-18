package com.example.myapplication.model;

/**
 * Modèle représentant un Makeup
 * Chaque makeup est représenté par un nom, une marque, un prix, une couleur et une URL d'image
 * Chaque attribut a son getter
 */
public class Makeup {
    private String name;
    private String brand;
    private String price;
    private String color;
    private String imgURL;

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String getImgURL() {
        return imgURL;
    }
}
