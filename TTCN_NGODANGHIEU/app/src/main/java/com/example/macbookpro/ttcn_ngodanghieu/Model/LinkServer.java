package com.example.macbookpro.ttcn_ngodanghieu.Model;

public class LinkServer {

    private String location="http://192.168.43.184/";
    private String urlLogin=location+"TTCN_NGODANGHIEU/Model/get_user.php";
    private String urlsp=location+"TTCN_NGODANGHIEU/Model/get_sp.php";
    private String urlqlnv =location+"TTCN_NGODANGHIEU/Model/get_list_nhan_vien.php";
    private String urlupdatepassnhansu=location+"TTCN_NGODANGHIEU/Model/update_user.php";
    private String urlinsertUser =location+"TTCN_NGODANGHIEU/Model/insert_user.php";
    private String urlinserthang =location+"TTCN_NGODANGHIEU/Model/insert_hang.php";
    private String urldeletenhansu=location+"TTCN_NGODANGHIEU/Model/delete_ns.php";
    private String urlupdatexuathang = location+"TTCN_NGODANGHIEU/Model/update_sp_saukhixuat.php";
    private String urlimg =location+"TTCN_NGODANGHIEU/img/monan/";
    private String urlimgqlk =location;

    public String getUrlimgqlk() {
        return location+"TTCN_NGODANGHIEU/img/hang/";
    }

    public String getUrlimg() {
        return urlimg;
    }

    public String getUrlinserthang() {
        return urlinserthang;
    }
    public String getUrlupdatexuathang() {
        return urlupdatexuathang;
    }
    public String getUrldeletenhansu() {
        return urldeletenhansu;
    }
    public String getUrlinsertUser() {
        return urlinsertUser;
    }
    public String getUrlupdatepassnhansu() {
        return urlupdatepassnhansu;
    }
    public String getUrlsp() {
        return urlsp;
    }
    public String getUrlqlnv() {
        return urlqlnv;
    }
    public String getLocation() {
        return location;
    }
    public String getUrlLogin() {
        return urlLogin;
    }
}
