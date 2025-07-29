package model;

public abstract class Kendaraan {
    private String id;
    private String merk;
    private String tahun;
    private double hargaSewa;

    public Kendaraan(String id, String merk, String tahun, double hargaSewa) {
        this.id = id;
        this.merk = merk;
        this.tahun = tahun;
        this.hargaSewa = hargaSewa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public double getHargaSewa() {
        return hargaSewa;
    }

    public void setHargaSewa(double hargaSewa) {
        this.hargaSewa = hargaSewa;
    }

    public abstract void info();



}
