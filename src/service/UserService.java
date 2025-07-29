package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.User;

public class UserService {
    private ArrayList<User> user = new ArrayList<>();

    public User cariData(String keyword) {
        for (int i = 0; i < user.size(); i++) {
            if (!user.isEmpty() && (user.get(i).getNama().contains(keyword) || user.get(i).getIdUser().contains(keyword))) {
                return user.get(i);
            }
        }
        return null;
    }

    public void tambahUser(String idUser, String nama, String kontak) {
        idUser = "U" + String.format("%03d", user.size() + 1);

        User newUser = new User(idUser, nama, kontak);
        user.add(newUser);
        System.out.println("User berhasil ditambahkan");
    }

    public void initData() {
        dataUser("data/user.txt");
    }

    public void saveData() {
        saveDataUser("data/user.txt");
    }

    public void dataUser(String in) {
        user.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(in))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if(parts.length != 0) {
                    String idUser = parts[0];
                    String nama = parts[1];
                    String kontak = parts[2];
                    User user = new User(idUser, nama, kontak);
                    this.user.add(user);
                } else {
                    System.out.println("Peringatan: Baris tidak valid, dilewati: " + line);
                }
            }
            System.out.println("Data User selesai dimuat.");
        } catch (IOException e) {
            System.out.println("Gagal memuat data: " + e.getMessage());
        }
    }

    public void saveDataUser(String in) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(in))) {
            for (int i = 0; i < user.size(); i++) {
                if (user.get(i) != null) {
                    writer.write(user.get(i).getIdUser() + "|" + 
                                 user.get(i).getNama() + "|" + 
                                 user.get(i).getKontak());
                    writer.newLine();
                }
            }
            System.out.println("Data User berhasil disimpan ke file.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data user: " + e.getMessage());
        }
    }

    public ArrayList<User> getDaftarUser() {
        return user;
    }






}
