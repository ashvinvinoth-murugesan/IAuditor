package com.rentokil.pci.rauditor.MSOT;

/**
 * Created by Lincoln on 15/01/16.
 */
public class Movie {
    private String title, genre;
    private int image;

    public Movie() {
    }

    public Movie(String title, String genre,int image) {
        this.title = title;
        this.genre = genre;
        this.image = image;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getImage() {
        return image;
    }
}
