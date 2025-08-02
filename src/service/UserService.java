package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserService {

    private ArrayList<User> daftarUser = new ArrayList<>();
    private final String FILE_PATH = "data/user.txt";

    public User cariData(String keyword) {
        // pengecekan daftar user
        // System.out.println("Daftar user: " + daftarUser.size());
        for (int i = 0; i < daftarUser.size(); i++) {
            // pengecekan data yg ditemukan dengan pencarian
            // System.out.println("Data user: " + daftarUser.get(i).getId()+ " pencarian: " + keyword);
            if (daftarUser.get(i).getId().equalsIgnoreCase(keyword) || daftarUser.get(i).getNama().equalsIgnoreCase(keyword)) {
                // Cetak data bila ditemukan
                // System.out.println("Ditemukan data user: " + daftarUser.get(i).getId());
                return daftarUser.get(i);
            }
        }
        return null;
    }

    private String generateNewID() {
        int maxNum = 0;
        for (User u : daftarUser) {
            if (u.getId().startsWith("U")) {
                try {
                    int num = Integer.parseInt(u.getId().substring(2));
                    if (num > maxNum) {
                        maxNum = num;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing ID: " + u.getId());
                }
            }
        }
        return "U" + String.format("%03d", maxNum + 1);

    }

    public List<User> getAllUser() {
        return new ArrayList<>(daftarUser);
    }

    public void tambahUser(String id, String nama, String kontak) {
        id = generateNewID();
        daftarUser.add(new User(id, nama, kontak));
        saveData();
    }

    public void hapusData(String id) {
        for (int i = 0; i < daftarUser.size(); i++) {
            if (daftarUser.get(i).getId().equalsIgnoreCase(id)) {
                daftarUser.remove(i);
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
        daftarUser.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(in))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 0) {
                    String idUser = parts[0];
                    String nama = parts[1];
                    String kontak = parts[2];
                    User user = new User(idUser, nama, kontak);
                    daftarUser.add(user);
                } else {
                    System.out.println("Peringatan: Baris tidak valid, dilewati: " + line);
                }
            }
            System.out.println("Data User selesai dimuat.");
        } catch (IOException e) {
            System.out.println("Gagal memuat data: " + e.getMessage());
        }
    }

    private void saveData(String in) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(in))) {
            for (int i = 0; i < daftarUser.size(); i++) {
                if (daftarUser.get(i) != null) {
                    writer.write(daftarUser.get(i).getId() + "|"
                            + daftarUser.get(i).getNama() + "|"
                            + daftarUser.get(i).getKontak());
                    writer.newLine();
                }
            }
            System.out.println("Data User berhasil disimpan ke file.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data user: " + e.getMessage());
        }
    }

}
