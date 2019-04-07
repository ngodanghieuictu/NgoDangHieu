package com.example.macbookpro.ttcn_ngodanghieu.Model;

public class OderMonAn {
    private String tenMonAn,donvi;
    private int id,sl,gia,thanhtien,id_usser;

    public OderMonAn(String tenMonAn, int id, int sl, int gia, int thanhtien, int id_usser) {
        this.tenMonAn = tenMonAn;
        this.id = id;
        this.sl = sl;
        this.gia = gia;
        this.thanhtien = thanhtien;
        this.id_usser = id_usser;
    }

    public void setId_usser(int id_usser) {
        this.id_usser = id_usser;
    }

    public int getId_usser() {

        return id_usser;
    }

    public String getDonvi() {
        return donvi;

    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public OderMonAn(String tenMonAn, String donvi, int id, int sl, int gia, int thanhtien) {
        this.tenMonAn = tenMonAn;
        this.donvi = donvi;
        this.id = id;
        this.sl = sl;
        this.gia = gia;
        this.thanhtien = thanhtien;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }
}
