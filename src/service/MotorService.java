package service;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Motor;
import model.StatusSewa;

public class MotorService {
    private ArrayList<Motor> daftarMotor = new ArrayList<>();
    private final String FILE_PATH = "data/motor.txt";

    public Motor cariData(String keyword) {
        for (int i = 0; i < daftarMotor.size(); i++) {
            if (daftarMotor.get(i).getMerk().equalsIgnoreCase(keyword) || 
                daftarMotor.get(i).getId().equalsIgnoreCase(keyword)) {
                return daftarMotor.get(i);
            }
        }
        return null;
    }

    private String generateNewID() {
        int maxNum = 0;
        for (Motor m : daftarMotor) {
            if (m.getId().startsWith("R2")) { 
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
         return "R2" + String.format("%03d", maxNum + 1);
    }

    public List<Motor> getAllMotor() {
        return new ArrayList<>(daftarMotor);
    }

    public Motor getMotor(String idMotor) {
        for(int i = 0; i < daftarMotor.size(); i++) {
            if(daftarMotor.get(i).getId().equalsIgnoreCase(idMotor)) {
                return daftarMotor.get(i);
            }
        }
        return null;
    }

    public void tambahMotor(String merk, String tahun, double hargaSewa, String kapasitasMesin) {
        String id = generateNewID();
        daftarMotor.add(new Motor(id, merk, tahun, hargaSewa, kapasitasMesin, StatusSewa.TERSEDIA));
        saveData();
    }

    public String sewaMotor(int index) {
         int[] indexMap = new int[daftarMotor.size()];
        int count = 0;

        for (int i = 0; i < daftarMotor.size(); i++) {
            if(daftarMotor.get(i).getStatusSewa() == StatusSewa.TERSEDIA) {
                indexMap[count] = i;
                count++;
            }
        }

        while (true) {
            if(index >= 1 && index <= count) {
                int numIndex = indexMap[index - 1];
                Motor unitMotor = daftarMotor.get(numIndex);
                unitMotor.setStatusSewa(StatusSewa.DISEWA);        
                saveData();
                return unitMotor.getId();
            } else {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                return "";
            }
        }
    
    }

    public void updateData(String id, String idBaru, String merkBaru, String tahunBaru, double hargaSewaBaru, String kapasitasMesinBaru) {
        for (Motor motor : daftarMotor) {
            if(motor.getId().equals(id)) {
                motor.updateDetails(idBaru, merkBaru, tahunBaru, hargaSewaBaru, kapasitasMesinBaru);
            }
        }
        saveData();
    }

    public void hapusData(String id) {
        for (int i = 0; i < daftarMotor.size(); i++) {
            if(daftarMotor.get(i).getId().equalsIgnoreCase(id)) {
                daftarMotor.remove(i);
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

    private void bacaData(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    String id = parts[0];
                    String merk = parts[1];
                    String tahun = parts[2];
                    double hargaSewa = Double.parseDouble(parts[3]);
                    String kapasitasMesin = parts[4];
                    StatusSewa statusSewa = StatusSewa.valueOf(parts[5].toUpperCase());
                    Motor daftarMotor = new Motor(id, merk, tahun, hargaSewa, kapasitasMesin, statusSewa);
                    this.daftarMotor.add(daftarMotor);
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
            for (int i = 0; i < daftarMotor.size(); i++) {
                if (daftarMotor.get(i) != null) {
                    writer.write(daftarMotor.get(i).getId() + "|" + 
                                 daftarMotor.get(i).getMerk() + "|" + 
                                 daftarMotor.get(i).getTahun() + "|" + 
                                 daftarMotor.get(i).getHargaSewa() + "|" +
                                 daftarMotor.get(i).getKapasitasMesin() + "|" +
                                 daftarMotor.get(i).getStatusSewa());
                    writer.newLine();
                }
            }
            System.out.println("Data Motor berhasil disimpan ke file.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data user: " + e.getMessage());
        }
    }


}
