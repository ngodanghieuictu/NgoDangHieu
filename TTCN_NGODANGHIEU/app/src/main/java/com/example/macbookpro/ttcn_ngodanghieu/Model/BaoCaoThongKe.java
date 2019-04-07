package com.example.macbookpro.ttcn_ngodanghieu.Model;

public class BaoCaoThongKe {
    private String tenhang,date;
    private int sl,gia,stt;

    public String getTenhang() {
        return tenhang;
    }

    public void setTenhang(String tenhang) {
        this.tenhang = tenhang;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public BaoCaoThongKe(String tenhang, String date, int sl, int gia, int stt) {
        this.tenhang = tenhang;
        this.date = date;
        this.sl = sl;
        this.gia = gia;
        this.stt = stt;
    }
}
