package com.example.cau2_sellcake;

public class Cake {
    private int img;
    private String name;
    private int coin;

    public Cake(int img, String name, int coin) {
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

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}
