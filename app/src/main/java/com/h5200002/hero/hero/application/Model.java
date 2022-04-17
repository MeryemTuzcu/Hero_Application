package com.h5200002.hero.hero.application;

public class Model {
    String uyenamesurname,uyenum,uyemail,uyesifre;
    int uyeid;
    boolean uyetip, uyedurum;
    public Model(String uyenamesurname,String uyenum,String uyemail,String uyesifre,int uyeid, boolean uyetip,boolean uyedurum) {
        this.uyenamesurname = uyenamesurname;
        this.uyenum = uyenum;
        this.uyemail = uyemail;
        this.uyesifre = uyesifre;
        this.uyeid = uyeid;
        this.uyetip = uyetip;
        this.uyedurum = uyedurum;
    }

    public Model() {

    }


    public String getUyenamesurname() {
        return uyenamesurname;
    }

    public void setUyenamesurname(String uyenamesurname) {
        this.uyenamesurname = uyenamesurname;
    }

    public String getUyenum() {
        return uyenum;
    }

    public void setUyenum(String uyenum) {
        this.uyenum = uyenum;
    }

    public String getUyemail() {
        return uyemail;
    }

    public void setUyemail(String uyemail) {
        this.uyemail = uyemail;
    }

    public String getUyesifre() {
        return uyesifre;
    }

    public void setUyesifre(String uyesifre) {
        this.uyesifre = uyesifre;
    }

    public int getUyeid() {
        return uyeid;
    }

    public void setUyeid(int uyeid) {
        this.uyeid = uyeid;
    }

    public boolean isUyetip() {
        return uyetip;
    }

    public void setUyetip(boolean uyetip) {
        this.uyetip = uyetip;
    }

    public boolean isUyedurum() {
        return uyedurum;
    }

    public void setUyedurum(boolean uyedurum) {
        this.uyedurum = uyedurum;
    }
}