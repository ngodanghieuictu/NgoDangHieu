package com.example.macbookpro.ttcn_ngodanghieu.Model;

public class ChucNang {
    private int id;
    private int imgChucNang;
    private String tenChucNang;

    public int getImgChucNang() {
        return imgChucNang;
    }

    public void setImgChucNang(int imgChucNang) {
        this.imgChucNang = imgChucNang;
    }

    public ChucNang(int id, int imgChucNang, String tenChucNang) {

        this.id = id;
        this.imgChucNang = imgChucNang;
        this.tenChucNang = tenChucNang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenChucNang() {
        return tenChucNang;
    }

    public void setTenChucNang(String tenChucNang) {
        this.tenChucNang = tenChucNang;
    }
}
