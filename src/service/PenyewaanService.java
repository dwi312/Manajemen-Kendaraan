package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Penyewaan;
import model.StatusSewa;

public class PenyewaanService {
    private ArrayList<Penyewaan> daftarSewa = new ArrayList<>();
    private final String FILE_PATH = "data/penyewaan.txt";

    private MobilService mobilService;
    private MotorService motorService;
    private UserService userService;

    

    public PenyewaanService(MobilService mobilService, MotorService motorService, UserService userService) {
        this.mobilService = mobilService;
        this.motorService = motorService;
        this.userService = userService;
    }

    public void initData() {
        bacaData(FILE_PATH);
    }

    public void saveData() {
        writeData(FILE_PATH);
    }

    public Penyewaan cariData(String keyword) {
        for (int i = 0; i < daftarSewa.size(); i++) {
            if (daftarSewa.get(i).getIdSewa().contains(keyword)) {
                return daftarSewa.get(i);
            }
        }
        return null;
    }

    private String generateNewID() {
        int maxNum = 0;
        for (Penyewaan p : daftarSewa) {
            if (p.getIdSewa().startsWith("SW")) {
                try {
                    int num = Integer.parseInt(p.getIdSewa().substring(2));
                    if (num > maxNum) {
                        maxNum = num;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing ID: " + p.getIdSewa());
                }
                
            }
        }
        return "SW" + String.format("%03d", maxNum + 1);
    }

    public List<Penyewaan> getAllPenyewaan() {
        return new ArrayList<>(daftarSewa);
    }

    public Penyewaan getPenyewaan(String idPenyewaan) {
        for (int i = 0; i < daftarSewa.size(); i++) {
            if (daftarSewa.get(i).getIdSewa().equalsIgnoreCase(idPenyewaan)) {
                return daftarSewa.get(i);
            }
        }
        return null;
    }


    public void sewaKendaraan(String idUser, String idKendaraan, LocalDate tglKembali, double totalHarga) {
        String idSewa = generateNewID();
        LocalDate tglSewa = LocalDate.now();
        StatusSewa statusSewa = StatusSewa.DISEWA;
        daftarSewa.add(new Penyewaan(idSewa, idUser, idKendaraan, tglSewa, tglKembali, totalHarga, statusSewa));
        saveData();
    }

    public void bacaData(String in) {
        daftarSewa.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(in))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if(parts.length == 7) {
                    String idSewa = parts[0]; 
                    String idUser = parts[1];
                    String idKendaraan = parts[2];
                    LocalDate tglSewa = LocalDate.parse(parts[3]); 
                    LocalDate tglKembali = LocalDate.parse(parts[4]);
                    double totalHarga = Double.parseDouble(parts[5]); 
                    StatusSewa statusSewa = StatusSewa.valueOf(parts[6].toUpperCase());
                    Penyewaan sewa = new Penyewaan(idSewa, idUser, idKendaraan,  tglSewa,  tglKembali, totalHarga, statusSewa);
                    this.daftarSewa.add(sewa);
                } else {
                    System.out.println("Peringatan: Baris tidak valid, dilewati: " + line);
                }
            }
            System.out.println("Data penyewaan selesai dimuat.");
        } catch (IOException e) {
            System.out.println("Gagal memuat data: " + e.getMessage());
        }
    }

    public void writeData(String in) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(in))) {
            for (int i = 0; i < daftarSewa.size(); i++) {
                if (daftarSewa.get(i) != null) {
                    writer.write(daftarSewa.get(i).getIdSewa() + "|" +
                                 daftarSewa.get(i).getIdUser() + "|" +
                                 daftarSewa.get(i).getIdKendaraan() + "|" +
                                 daftarSewa.get(i).getTglSewa() + "|" +
                                 daftarSewa.get(i).getTglKembali() + "|" +
                                 daftarSewa.get(i).getTotalHarga() + "|" +
                                 daftarSewa.get(i).getStatusSewa());
                    writer.newLine();
                }
            }
            System.out.println("Data Penyewaan berhasil disimpan ke file.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data user: " + e.getMessage());
        }
    }


}
