package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Mobil;

public class MobilService {
    private ArrayList<Mobil> mobil = new ArrayList<>();
    private final String FILE_PATH = "data/mobil.txt";

    public Mobil cariData(String keyword) {
        for (int i = 0; i < mobil.size(); i++) {
            if (!mobil.isEmpty() && (mobil.get(i).getMerk().contains(keyword) || mobil.get(i).getId().contains(keyword))) {
                return mobil.get(i);
            }
        }
        return null;
    }

    public void getMobil() {
        for (int i = 0; i < mobil.size(); i++) {
            if (!mobil.isEmpty()) {
                mobil.get(i).info();
            }
        }
    }

    public void tambahMobil(String id, String merk, String tahun, double hargaSewa, int jumlahKursi, String tipeTransmisi) {
        id = "R4" + String.format("%03d", mobil.size() + 1);
        mobil.add(new Mobil(id, merk, tahun, hargaSewa, jumlahKursi, tipeTransmisi));
        saveData();
    }

    public void updateData(String id, String merk, String tahun, double hargaSewa, int jumlahKursi, String tipeTransmisi) {
        mobil.add(new Mobil(id, merk, tahun, hargaSewa, jumlahKursi, tipeTransmisi));
        saveData();
    }

    public void initData() {
        bacaData(FILE_PATH);
    }

    public void saveData() {
        saveData(FILE_PATH);
    }

    public void bacaData(String in) {
        mobil.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(in))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if(parts.length != 0) {
                    String id = parts[0];
                    String merk = parts[1];
                    String tahun = parts[2];
                    double hargaSewa = Double.parseDouble(parts[3]);
                    int jumlahKursi = Integer.parseInt(parts[4]);
                    String tipeTransmisi = parts[5];
                    Mobil mobil = new Mobil(id, merk, tahun, hargaSewa, jumlahKursi, tipeTransmisi);
                    this.mobil.add(mobil);
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
            for (int i = 0; i < mobil.size(); i++) {
                if (mobil.get(i) != null) {
                    writer.write(mobil.get(i).getId() + "|" + 
                                 mobil.get(i).getMerk() + "|" + 
                                 mobil.get(i).getTahun() + "|" + 
                                 mobil.get(i).getHargaSewa() + "|" +
                                 mobil.get(i).getJumlahKursi() + "|" +
                                 mobil.get(i).getTipeTransmisi());
                    writer.newLine();
                }
            }
            System.out.println("Data Mobil berhasil disimpan ke file.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data user: " + e.getMessage());
        }
    }

}
