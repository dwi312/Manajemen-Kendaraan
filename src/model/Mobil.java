package model;

public class Mobil extends Kendaraan {
    private int jumlahKursi;
    private String tipeTransmisi;

    public Mobil(String id, String merk, String tahun, double hargaSewa, int jumlahKursi, String tipeTransmisi) {
        super(id, merk, tahun, hargaSewa);
        this.jumlahKursi = jumlahKursi;
        this.tipeTransmisi = tipeTransmisi;
    }

    public int getJumlahKursi() {
        return jumlahKursi;
    }

    public void setJumlahKursi(int jumlahKursi) {
        this.jumlahKursi = jumlahKursi;
    }

    public String getTipeTransmisi() {
        return tipeTransmisi;
    }

    public void setTipeTransmisi(String tipeTransmisi) {
        this.tipeTransmisi = tipeTransmisi;
    }

    @Override
    public void info() {
        System.out.println(getId() + " | " +
                           getMerk() + " | " + 
                           getTahun() + " | " + 
                           getHargaSewa() + " | " + 
                           getJumlahKursi() + " | " + 
                           getTipeTransmisi());
    }



}
