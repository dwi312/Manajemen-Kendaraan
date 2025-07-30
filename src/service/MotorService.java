package service;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Motor;

public class MotorService {
    private ArrayList<Motor> motor = new ArrayList<>();
    private final String FILE_PATH = "data/motor.txt";

    public Motor cariData(String keyword) {
        for (int i = 0; i < motor.size(); i++) {
            if (!motor.isEmpty() && (motor.get(i).getMerk().contains(keyword) || motor.get(i).getId().contains(keyword))) {
                return motor.get(i);
            }
        }
        return null;
    }

    public void getMotor() {
        for (int i = 0; i < motor.size(); i++) {
            if (!motor.isEmpty()) {
                motor.get(i).info();
            }
        }
    }

    public void tambahMotor(String id, String merk, String tahun, double hargaSewa, String kapasitasMesin) {
        id = "R2" + String.format("%03d", motor.size() + 1);
        motor.add(new Motor(id, merk, tahun, hargaSewa, kapasitasMesin));
        saveData();
    }

    public void updateData(String keyword) {

    }

    public void initData() {
        bacaData(FILE_PATH);
    }

    public void saveData() {
        saveData(FILE_PATH);
    }

    private void bacaData(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 0) {
                    String id = parts[0];
                    String merk = parts[1];
                    String tahun = parts[2];
                    double hargaSewa = Double.parseDouble(parts[3]);
                    String kapasitasMesin = parts[4];
                    Motor motor = new Motor(id, merk, tahun, hargaSewa, kapasitasMesin);
                    this.motor.add(motor);
                } else {
                    System.out.println("Warning: Invalid line, skipped: " + line);
                }
            }
            System.out.println("Data Motor selesai dimuat.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private void saveData(String path) {
         try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (int i = 0; i < motor.size(); i++) {
                if (motor.get(i) != null) {
                    writer.write(motor.get(i).getId() + "|" + 
                                 motor.get(i).getMerk() + "|" + 
                                 motor.get(i).getTahun() + "|" + 
                                 motor.get(i).getHargaSewa() + "|" +
                                 motor.get(i).getKapasitasMesin());
                    writer.newLine();
                }
            }
            System.out.println("Data Motor berhasil disimpan ke file.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data user: " + e.getMessage());
        }
    }


}
