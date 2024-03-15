package com.example.customlistview;

public class Country {
    private int anhCountry;
    private String nameCountry;
    private String textCountry;

    public Country(int anhCountry, String nameCountry, String textCountry) {
        this.anhCountry = anhCountry;
        this.nameCountry = nameCountry;
        this.textCountry = textCountry;
    }

    public int getAnhCountry() {
        return anhCountry;
    }

    public void setAnhCountry(int anhCountry) {
        this.anhCountry = anhCountry;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public String getTextCountry() {
        return textCountry;
    }

    public void setTextCountry(String textCountry) {
        this.textCountry = textCountry;
    }
}
