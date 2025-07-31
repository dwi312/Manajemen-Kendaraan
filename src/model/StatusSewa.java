package model;

public enum  StatusSewa {
    MAINTENCE("Maintence"),
    TERSEDIA("Tersedia"),
    DISEWA("Disewa");

    private final String deskripsi;

    StatusSewa(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    @Override
    public String toString() {
        return deskripsi;
    }
}
