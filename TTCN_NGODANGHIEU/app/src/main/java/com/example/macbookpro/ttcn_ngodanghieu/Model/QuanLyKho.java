package com.example.macbookpro.ttcn_ngodanghieu.Model;

import java.io.Serializable;

public class QuanLyKho implements Serializable {
    private int id;
    private String img;
    private String tensp;
    private int sl;
    private String ngaynhap;
    private int id_user;
    private String nsx;
    private int loaisp;

    public String getNsx() {
        return nsx;
    }

    public void setNsx(String nsx) {
        this.nsx = nsx;
    }

    public int getLoaisp() {
        return loaisp;
    }

    public void setLoaisp(int loaisp) {
        this.loaisp = loaisp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public String getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public QuanLyKho(int id, String img, String tensp, int sl, String ngaynhap, int id_user, String nsx, int loaisp) {
        this.id = id;
        this.img = img;
        this.tensp = tensp;
        this.sl = sl;
        this.ngaynhap = ngaynhap;
        this.id_user = id_user;
        this.nsx = nsx;
        this.loaisp = loaisp;
    }
}
