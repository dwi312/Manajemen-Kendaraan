package model;

public class User {
    private String idUser;
    private String nama;
    private String kontak;

    public User(String idUser, String nama, String kontak) {
        this.idUser = idUser;
        this.nama = nama;
        this.kontak = kontak;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    

    @Override
    public String toString() {
        return idUser + " | " + nama + " | " + " [" + kontak + "] ";
    }


}