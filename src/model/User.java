package model;

public class User {
    private String id;
    private String nama;
    private String kontak;

    public User(String id, String nama, String kontak) {
        this.id = id;
        this.nama = nama;
        this.kontak = kontak;
    }

    public String getId() {
        return id;
    }

    public void setIdUser(String id) {
        this.id = id;
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
        return id + " | " + nama + " | " + " [" + kontak + "] ";
    }


}