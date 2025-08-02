package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Mobil;
import model.StatusSewa;

public class MobilService {
    private ArrayList<Mobil> daftarMobil = new ArrayList<>();
    private final String FILE_PATH = "data/mobil.txt";

    public Mobil cariData(String keyword) {
        for (int i = 0; i < daftarMobil.size(); i++) {
            if (daftarMobil.get(i).getId().contains(keyword)) {
                return daftarMobil.get(i);
            } else {
                return null;
            }
        }
        return null;
    }

    private String generateNewID() {
        int maxNum = 0;
        for (Mobil m : daftarMobil) {
            if (m.getId().startsWith("R4")) { 
                try {
                    int num = Integer.parseInt(m.getId().substring(2)); 
                    if (num > maxNum) {
                        maxNum = num;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing ID: " + m.getId());
                }
            }
        }
         return "R4" + String.format("%03d", maxNum + 1);
    }

    public List<Mobil> getAllMobil() {
        return new ArrayList<>(daftarMobil);
    }

    public Mobil getMobil(String idMobil) {
        for(int i = 0; i < daftarMobil.size(); i++) {
            if(daftarMobil.get(i).getId().equalsIgnoreCase(idMobil)) {
                return daftarMobil.get(i);
            }
        }
        return null;
    }

    public void tambahMobil(String id, String merk, String tahun, double hargaSewa, int jumlahKursi, String tipeTransmisi) {
        id = generateNewID();
        daftarMobil.add(new Mobil(id, merk, tahun, hargaSewa, jumlahKursi, tipeTransmisi, StatusSewa.TERSEDIA));
        saveData();
    }

    public String sewaMobil(int index) {
        int[] indexMap = new int[daftarMobil.size()];
        int count = 0;

        for (int i = 0; i < daftarMobil.size(); i++) {
            if(daftarMobil.get(i).getStatusSewa() == StatusSewa.TERSEDIA) {
                indexMap[count] = i;
                count++;
            }
        }

        while (true) {
            if(index >= 1 && index <= count) {
                int numIndex = indexMap[index - 1];
                Mobil unitMobil = daftarMobil.get(numIndex);
                unitMobil.setStatusSewa(StatusSewa.DISEWA);        
                saveData();
                return unitMobil.getId();
            } else {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                return "";
            }
        }
    }

    public void updateData(String id, String idBaru, String merkBaru, String tahunBaru, double hargaSewaBaru, int jumlahKursiBaru, String tipeTransmisiBaru) {
        for (Mobil mobil : daftarMobil) {
            if(mobil.getId().equals(id)) {
                mobil.updateDetails(idBaru, merkBaru, tahunBaru, hargaSewaBaru, jumlahKursiBaru, tipeTransmisiBaru);
                saveData();
                break;
            }
        }
    }

    public void hapusData(String id) {
        for (int i = 0; i < daftarMobil.size(); i++) {
            if(daftarMobil.get(i).getId().equalsIgnoreCase(id)) {
                daftarMobil.remove(i);
                break;
            }
         }
        saveData();
    }

    public void initData() {
        bacaData(FILE_PATH);
    }

    public void saveData() {
        saveData(FILE_PATH);
    }

    public void bacaData(String in) {
        daftarMobil.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(in))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if(parts.length == 7) {
                    String id = parts[0];
                    String merk = parts[1];
                    String tahun = parts[2];
                    double hargaSewa = Double.parseDouble(parts[3]);
                    int jumlahKursi = Integer.parseInt(parts[4]);
                    String tipeTransmisi = parts[5];
                    StatusSewa statusSewa = StatusSewa.valueOf(parts[6].toUpperCase());
                    Mobil mobil = new Mobil(id, merk, tahun, hargaSewa, jumlahKursi, tipeTransmisi, statusSewa);
                    this.daftarMobil.add(mobil);
                } else {
                    System.out.println("Peringatan: Baris tidak valid, dilewati: " + line);
                }
            }
            System.out.println("Data Mobil selesai dimuat.");
        } catch (IOException e) {
            System.out.println("Gagal memuat data: " + e.getMessage());
        }
    }

    public void saveData(String in) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(in))) {
            for (int i = 0; i < daftarMobil.size(); i++) {
                if (daftarMobil.get(i) != null) {
                    writer.write(daftarMobil.get(i).getId() + "|" + 
                                 daftarMobil.get(i).getMerk() + "|" + 
                                 daftarMobil.get(i).getTahun() + "|" + 
                                 daftarMobil.get(i).getHargaSewa() + "|" +
                                 daftarMobil.get(i).getJumlahKursi() + "|" +
                                 daftarMobil.get(i).getTipeTransmisi() + "|" +
                                 daftarMobil.get(i).getStatusSewa());
                    writer.newLine();
                }
            }
            System.out.println("Data Mobil berhasil disimpan ke file.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data user: " + e.getMessage());
        }
    }

}
