package com.example.porjarapp.Model;

public class Order_model {



    String cabor;
    String jml_tiket;
    String id_tiket;

    public Order_model() {
    }

    public Order_model(String cabor, String jml_tiket, String id_tiket) {

        this.cabor = cabor;
        this.jml_tiket = jml_tiket;
        this.id_tiket = id_tiket;
    }

    public String getId_tiket() {
        return id_tiket;
    }

    public void setId_tiket(String id_tiket) {
        this.id_tiket = id_tiket;
    }


    public String getCabor() {
        return cabor;
    }

    public void setCabor(String cabor) {
        this.cabor = cabor;
    }

    public String getJml_tiket() {
        return jml_tiket;
    }

    public void setJml_tiket(String jml_tiket) {
        this.jml_tiket = jml_tiket;
    }
}
