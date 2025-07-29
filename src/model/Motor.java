package model;

public class Motor extends Kendaraan {
    private String kapasitasMesin;

    public Motor(String id, String merk, String tahun, double hargaSewa, String kapasitasMesin) {
        super(id, merk, tahun, hargaSewa);
        this.kapasitasMesin = kapasitasMesin;
    }

    public String getKapasitasMesin() {
        return kapasitasMesin;
    }

    public void setKapasitasMesin(String kapasitasMesin) {
        this.kapasitasMesin = kapasitasMesin;
    }

    @Override
    public void info() {
        
    }


}
