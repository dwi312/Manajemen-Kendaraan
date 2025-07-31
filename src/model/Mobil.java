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

    // Metode untuk memperbarui string jika tidak kosong
    private String updateIfNotEmpty(String existingValue, String newValue) {
        // Memeriksa apakah newValue tidak null dan tidak kosong
        return (newValue != null && !newValue.isEmpty()) ? newValue : existingValue;
    }

    // Metode ini juga bisa diletakkan di kelas Kendaraan
    private double updateIfNotZero(double existingValue, double newValue) {
        // Memeriksa apakah newValue bukan 0.0
        return newValue != 0.0 ? newValue : existingValue;
    }

    // Metode ini juga bisa diletakkan di kelas Kendaraan
    private int updateIfNotInt(int existingValue, int newValue) {
        // Memeriksa apakah newValue bukan 0
        return newValue != 0 ? newValue : existingValue;
    }

    public void updateDetails(String idBaru, String merkBaru, String tahunBaru, double hargaSewaBaru,int jumlahKursiBaru, String tipeTransmisiBaru) {

        setId(updateIfNotEmpty(getId(), idBaru));
        setMerk(updateIfNotEmpty(getMerk(), merkBaru));
        setTahun(updateIfNotEmpty(getTahun(), tahunBaru));
        
        setHargaSewa(updateIfNotZero(getHargaSewa(), hargaSewaBaru));
        
        setJumlahKursi(updateIfNotInt(getJumlahKursi(), jumlahKursi));
        setTipeTransmisi(updateIfNotEmpty(getTipeTransmisi(), tipeTransmisiBaru));
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
