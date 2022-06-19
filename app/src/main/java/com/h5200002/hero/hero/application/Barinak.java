package com.h5200002.hero.hero.application;

public class Barinak {
    String Id;



    String barinakAdresi;
    Boolean barinakDurumu;

    public Barinak(String barinakAdresi, Boolean barinakDurumu, String Id) {
        this.barinakAdresi = barinakAdresi;
        this.barinakDurumu = barinakDurumu;
        this.Id = Id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Boolean getBarinakDurumu() {
        return barinakDurumu;
    }

    public void setBarinakDurumu(Boolean barinakDurumu) {
        this.barinakDurumu = barinakDurumu;
    }

    public String getBarinakAdresi() {
        return barinakAdresi;
    }

    public void setBarinakAdresi(String barinakAdresi) {
        this.barinakAdresi = barinakAdresi;
    }



    public Barinak() {

    }


}
