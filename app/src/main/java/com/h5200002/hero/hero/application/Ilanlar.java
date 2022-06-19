package com.h5200002.hero.hero.application;

public class Ilanlar {
    String kayipAdresi;
    String kayipIlce;
    String kayipNumara;
    String kayipGorsel;


    int kayipId;
    public Ilanlar(String kayipAdresi,String kayipIlce,String kayipNumara,String kayipGorsel,int kayipId) {
        this.kayipAdresi = kayipAdresi;
        this.kayipIlce = kayipIlce;
        this.kayipNumara = kayipNumara;
        this.kayipGorsel = kayipGorsel;
        this.kayipId = kayipId;

    }

    public Ilanlar() {

    }
    public String getKayipAdresi() {
        return kayipAdresi;
    }

    public void setKayipAdresi(String kayipAdresi) {
        this.kayipAdresi = kayipAdresi;
    }

    public String getKayipIlce() {
        return kayipIlce;
    }

    public void setKayipIlce(String kayipIlce) {
        this.kayipIlce = kayipIlce;
    }

    public String getKayipNumara() {
        return kayipNumara;
    }

    public void setKayipNumara(String kayipNumara) {
        this.kayipNumara = kayipNumara;
    }

    public String getKayipGorsel() {
        return kayipGorsel;
    }

    public void setKayipGorsel(String kayipGorsel) {
        this.kayipGorsel = kayipGorsel;
    }

    public int getKayipId() {
        return kayipId;
    }

    public void setKayipId(int kayipId) {
        this.kayipId = kayipId;
    }



}
