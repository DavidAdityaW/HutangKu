package com.example.hutangku;

public class Piutang {

    String namapiutang;
    String deskripsipiutang;
    String tanggalpiutang;
    String jumlahpiutang;

    // Konstruktor tanpa parameter
    public Piutang() {
    }

    // Konstruktor dengan parameter
    public Piutang(String namapiutang, String deskripsipiutang, String tanggalpiutang, String jumlahpiutang) {
        this.namapiutang = namapiutang;
        this.deskripsipiutang = deskripsipiutang;
        this.tanggalpiutang = tanggalpiutang;
        this.jumlahpiutang = jumlahpiutang;
    }

    // Getter and setter
    public String getNamapiutang() {
        return namapiutang;
    }

    public void setNamapiutang(String namapiutang) {
        this.namapiutang = namapiutang;
    }

    public String getDeskripsipiutang() {
        return deskripsipiutang;
    }

    public void setDeskripsipiutang(String deskripsipiutang) {
        this.deskripsipiutang = deskripsipiutang;
    }

    public String getTanggalpiutang() {
        return tanggalpiutang;
    }

    public void setTanggalpiutang(String tanggalpiutang) {
        this.tanggalpiutang = tanggalpiutang;
    }

    public String getJumlahpiutang() {
        return jumlahpiutang;
    }

    public void setJumlahpiutang(String jumlahpiutang) {
        this.jumlahpiutang = jumlahpiutang;
    }
}
