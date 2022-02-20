package com.example.frontend;

public class ResepData {
    int id;
    String userid;
    String daftarresep;
    String penyakit;
    String tanggal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) { this.userid = userid;
    }

    public String getDaftarresep() {
        return daftarresep;
    }

    public void setDaftarresep(String daftarresep) {
        this.daftarresep = daftarresep;
    }

    public String getPenyakit() {
        return penyakit;
    }

    public void setPenyakit(String penyakit) {
        this.penyakit = penyakit;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
