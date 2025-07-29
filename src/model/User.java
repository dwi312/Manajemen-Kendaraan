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

    public String getNama() {
        return nama;
    }
    
    public String getKontak() {
        return kontak;
    }

    @Override
    public String toString() {
        return idUser + " | " + nama + " | " + " [" + kontak + "] ";
    }


}