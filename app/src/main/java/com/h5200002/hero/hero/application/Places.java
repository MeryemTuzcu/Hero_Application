package com.h5200002.hero.hero.application;

public class Places {
    String konumAdresi;
    String konumIlce;
    String konumIl;
    String konumGorsel;
    int konumId;
    String konumTarih;



    public Places(String konumGosel, String konumIl, String konumIlce, String konumAdresi, int konumId, String konumTarih) {
        this.konumGorsel = konumGosel;
        this.konumId = konumId;
        this.konumIl = konumIl;
        this.konumIlce = konumIlce;
        this.konumAdresi = konumAdresi;
        this.konumTarih = konumTarih;

    }

    public Places() {

    }


    public String getKonumGorsel() {
        return konumGorsel;
    }

    public void setKonumGorsel(String konumGorsel) {

        this.konumGorsel = konumGorsel;
    }

    public String getKonumAdresi() {
        return konumAdresi;
    }

    public void setKonumAdresi(String konumAdresi) {
        this.konumAdresi = konumAdresi;
    }

    public String getKonumIlce() {
        return konumIlce;
    }

    public void setKonumIlce(String konumIlce) {
        this.konumIlce = konumIlce;
    }

    public String getKonumIl() {
        return konumIl;
    }

    public void setKonumIl(String konumIl) {
        this.konumIl = konumIl;
    }

    public int getKonumId() {
        return konumId;
    }

    public void setKonumId(int konumId) {
        this.konumId = konumId;
    }
    public String getKonumTarih() {
        return konumTarih;
    }

    public void setKonumTarih(String konumTarih) {
        this.konumTarih = konumTarih;
    }






}
