package com.example.rentcamera.model;

public class User {
    private String id, nama, harga, kamera, lmsewa;

    public User() {

    }

    public User(String nama, String kamera, String lmsewa, String harga) {
        this.nama = nama;
        this.kamera = kamera;
        this.lmsewa = lmsewa;
        this.harga = harga;

    }

    public String getId(){
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama)
    {
        this.nama = nama;
    }

    public String getKamera()
    {
        return kamera;
    }

    public void setKamera(String kamera)
    {
        this.kamera = kamera;
    }

    public String getLmsewa()
    {
        return lmsewa;
    }

    public void setLmsewa(String lmsewa)
    {
        this.lmsewa = lmsewa;
    }

    public String getHarga()
    {
        return harga;
    }

    public void setHarga(String harga)
    {
        this.harga = harga;
    }

}
