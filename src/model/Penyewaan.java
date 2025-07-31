package model;

import java.time.LocalDate;

public class Penyewaan {
    private String idSewa;
    private String idKendaraan;
    private String idUser;
    private LocalDate tglSewa;
    private LocalDate tglKembali;
    private double totalHarga;
    private StatusSewa statusSewa;

    public Penyewaan(String idSewa, String idKendaraan, String idUser, LocalDate tglSewa, LocalDate tglKembali,
                     double totalHarga, StatusSewa statusSewa) {
        this.idSewa = idSewa;
        this.idKendaraan = idKendaraan;
        this.idUser = idUser;
        this.tglSewa = tglSewa;
        this.tglKembali = tglKembali;
        this.totalHarga = totalHarga;
        this.statusSewa = statusSewa;
    }

    public String getIdSewa() { return idSewa; }
    public String getIdKendaraan() { return idKendaraan; }
    public String getIdUser() { return idUser; }
    public LocalDate getTglSewa() { return tglSewa; }
    public LocalDate getTglKembali() { return tglKembali; }
    public double getTotalHarga() { return totalHarga; }
    public StatusSewa getStatusSewa() { return statusSewa; }

    public void setIdSewa(String idSewa) { this.idSewa = idSewa; }
    public void setIdKendaraan(String idKendaraan) { this.idKendaraan = idKendaraan; }
    public void setIdUser(String idUser) { this.idUser = idUser; }
    public void setTglSewa(LocalDate tglSewa) { this.tglSewa = tglSewa; }
    public void setTglKembali(LocalDate tglKembali) { this.tglKembali = tglKembali; }
    public void setTotalHarga(double totalHarga) { this.totalHarga = totalHarga; }
    public void setStatusSewa(StatusSewa statusSewa) { this.statusSewa = statusSewa; }

    @Override
    public String toString() {
        return idSewa + " | " + 
               idKendaraan + " | " + 
               idUser + " | " + 
               tglSewa + " | " + 
               tglKembali + " | " + 
               totalHarga + " | " + 
               statusSewa;
    }
    
}
