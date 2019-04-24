package com.rentokil.pci.rauditor;

/**
 * Created by Belal on 10/18/2017.
 */


public class recyclercons {
    private String title;
    private String shortdesc;

    private int image;

    public recyclercons( String title, String shortdesc, int image) {
        this.title = title;
        this.shortdesc = shortdesc;

        this.image = image;
    }


    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }


    public int getImage() {
        return image;
    }
}

