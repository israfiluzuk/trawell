package com.example.trawell.Haber;

public class HaberDB {

    public String baslik;
    public String haber;
    public String kaynak;
    public String tarih;
    public String yazar;

    public HaberDB(){

    }

    public HaberDB(String baslik, String haber, String kaynak, String tarih, String yazar){
        this.baslik = baslik;
        this.haber = haber;
        this.kaynak= kaynak;
        this.tarih = tarih;
        this.yazar = yazar;
    }

}
