package com.example.porjarapp.Model;

public class Cabor_model {

   String logo;
   String nama;
   String status;

    public Cabor_model() {
    }

    public Cabor_model(String logo, String nama, String status) {
        this.logo = logo;
        this.nama = nama;
        this.status = status;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
