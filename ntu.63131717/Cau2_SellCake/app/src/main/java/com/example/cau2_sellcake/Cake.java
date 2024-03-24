package com.example.cau2_sellcake;

public class Cake {
    private int img;
    private String name;
    private String coin;

    public Cake(int img, String name, String coin) {
        this.img = img;
        this.name = name;
        this.coin = coin;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }
}
