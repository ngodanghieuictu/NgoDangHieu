package com.example.macbookpro.ttcn_ngodanghieu.Model;

import java.io.Serializable;

public class User_Login{
    private int id;
    private String namelogin_user,pass_user;
    private int level;
    private String name_usser;

    public User_Login(int id, String namelogin_user, String pass_user, int level, String name_usser) {
        this.id = id;
        this.namelogin_user = namelogin_user;
        this.pass_user = pass_user;
        this.level = level;
        this.name_usser = name_usser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamelogin_user() {
        return namelogin_user;
    }

    public void setNamelogin_user(String namelogin_user) {
        this.namelogin_user = namelogin_user;
    }

    public String getPass_user() {
        return pass_user;
    }

    public void setPass_user(String pass_user) {
        this.pass_user = pass_user;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName_usser() {
        return name_usser;
    }

    public void setName_usser(String name_usser) {
        this.name_usser = name_usser;
    }
}
