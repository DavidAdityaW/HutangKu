package com.example.hutangku;

public class Hutang {

    String nama;
    String deskripsi;
    String tanggal;
    String jumlah;
    String keyhutang;

    // Konstruktor tanpa parameter
    public Hutang() {
    }

    // Konstruktor dengan parameter
    public Hutang(String nama, String deskripsi, String tanggal, String jumlah, String keyhutang) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.tanggal = tanggal;
        this.jumlah = jumlah;
        this.keyhutang = keyhutang;
    }

    // Getter and setter
    public String getKeyhutang() {
        return keyhutang;
    }

    public void setKeyhutang(String keyhutang) {
        this.keyhutang = keyhutang;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
}
