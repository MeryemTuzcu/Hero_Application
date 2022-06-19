package com.h5200002.hero.hero.application;

public class Para {
    String  paraAd;
    String paraTC;
    String paraNum;
    String paraEmail;
    int ParaTutar;

    public int getParaTutar() {
        return ParaTutar;
    }

    public void setParaTutar(int paraTutar) {
        ParaTutar = paraTutar;
    }

    String ParaKIsim;
    String ParaKNum;
    String ParaYıl;
    String ParaAy;
    String ParaCvv;

    public Para(String paraAd, String paraTC, String paraNum, String paraEmail, int ParaTutar, String ParaKIsim,String ParaKNum,String ParaYıl,String ParaAy,String ParaCvv) {
        this.paraAd = paraAd;
        this.paraTC = paraTC;
        this.paraNum = paraNum;
        this.paraEmail = paraEmail;
        this.ParaTutar = ParaTutar;
        this.ParaKIsim = ParaKIsim;
        this.ParaKNum = ParaKNum;
        this.ParaYıl = ParaYıl;
        this.ParaAy = ParaAy;
        this.ParaCvv = ParaCvv;

    }
    public Para() {

    }

    public String getParaAd() {
        return paraAd;
    }

    public void setParaAd(String paraAd) {
        this.paraAd = paraAd;
    }

    public String getParaTC() {
        return paraTC;
    }

    public void setParaTC(String paraTC) {
        this.paraTC = paraTC;
    }

    public String getParaNum() {
        return paraNum;
    }

    public void setParaNum(String paraNum) {
        this.paraNum = paraNum;
    }

    public String getParaEmail() {
        return paraEmail;
    }

    public void setParaEmail(String paraEmail) {
        this.paraEmail = paraEmail;
    }



    public String getParaKIsim() {
        return ParaKIsim;
    }

    public void setParaKIsim(String paraKIsim) {
        ParaKIsim = paraKIsim;
    }

    public String getParaKNum() {
        return ParaKNum;
    }

    public void setParaKNum(String paraKNum) {
        ParaKNum = paraKNum;
    }

    public String getParaYıl() {
        return ParaYıl;
    }

    public void setParaYıl(String paraYıl) {
        ParaYıl = paraYıl;
    }

    public String getParaAy() {
        return ParaAy;
    }

    public void setParaAy(String paraAy) {
        ParaAy = paraAy;
    }

    public String getParaCvv() {
        return ParaCvv;
    }

    public void setParaCvv(String paraCvv) {
        ParaCvv = paraCvv;
    }


}
