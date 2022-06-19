package com.h5200002.hero.hero.application;

public class Mama {
    String Id;



    String mamaAdresi;
    Boolean mamaDurumu;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getMamaAdresi() {
        return mamaAdresi;
    }

    public void setMamaAdresi(String mamaAdresi) {
        this.mamaAdresi = mamaAdresi;
    }

    public Boolean getMamaDurumu() {
        return mamaDurumu;
    }

    public void setMamaDurumu(Boolean mamaDurumu) {
        this.mamaDurumu = mamaDurumu;
    }

    public Mama(String mamaAdresi, Boolean mamaDurumu, String Id) {
        this.mamaAdresi = mamaAdresi;
        this.mamaDurumu = mamaDurumu;
        this.Id = Id;
    }


    public Mama() {

    }


}
