package service;

import java.util.ArrayList;
import java.util.List;

import model.Penyewaan;

public class PenyewaanService {
    private ArrayList<Penyewaan> daftarPenyewaan = new ArrayList<>();
    private final String FILE_PATH = "data/penyewaan.txt";

    public Penyewaan cariData(String keyword) {
        for (int i = 0; i < daftarPenyewaan.size(); i++) {
            if (daftarPenyewaan.get(i).getIdSewa().contains(keyword)) {
                return daftarPenyewaan.get(i);
            }
        }
        return null;
    }

    private String generateNewID() {
        int maxNum = 0;
        for (Penyewaan p : daftarPenyewaan) {
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
        return new ArrayList<>(daftarPenyewaan);
    }


}
