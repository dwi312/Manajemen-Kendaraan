package model;

public class Motor extends Kendaraan {
    private String kapasitasMesin;
    private StatusSewa statusSewa;

    public Motor(String id, String merk, String tahun, double hargaSewa, String kapasitasMesin, StatusSewa statusSewa) {
        super(id, merk, tahun, hargaSewa);
        this.kapasitasMesin = kapasitasMesin;
        this.statusSewa = statusSewa;
    }

    public String getKapasitasMesin() {
        return kapasitasMesin;
    }

    public void setKapasitasMesin(String kapasitasMesin) {
        this.kapasitasMesin = kapasitasMesin;
    }

    public StatusSewa getStatusSewa() {
        return statusSewa;
    }

    public void setStatusSewa(StatusSewa statusSewa) {
        this.statusSewa = statusSewa;
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

    public void updateDetails(String idBaru, String merkBaru, String tahunBaru, double hargaSewaBaru, String kapasitasMesinBaru) {
        // Menggunakan setter dari kelas Kendaraan karena propertinya private
        setId(updateIfNotEmpty(getId(), idBaru));
        setMerk(updateIfNotEmpty(getMerk(), merkBaru));
        setTahun(updateIfNotEmpty(getTahun(), tahunBaru));
        
        // Menggunakan setter untuk hargaSewa dan metode updateIfNotZero yang sudah disesuaikan untuk double
        setHargaSewa(updateIfNotZero(getHargaSewa(), hargaSewaBaru));
        
        // Untuk properti kelas Motor sendiri
        setKapasitasMesin(updateIfNotEmpty(getKapasitasMesin(), kapasitasMesinBaru));
    }

    @Override
    public void info() {
        System.out.println(getId() + " | " +
                           getMerk() + " | " + 
                           getTahun() + " | " + 
                           getHargaSewa() + " | " + 
                           getKapasitasMesin());
    }


}
