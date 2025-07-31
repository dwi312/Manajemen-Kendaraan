package controller;

import helper.AppHelper;
import java.util.Scanner;
import model.Mobil;
import model.Motor;
import service.MobilService;
import service.MotorService;
import service.UserService;
import view.Console;
import view.UserView;


public class App {
    private int pilihan;
    private boolean exit;
    private Scanner input = new Scanner(System.in);

    private Console view;
    private UserView viewUser;
    private UserService user = new UserService();
    private MobilService mobil = new MobilService();
    private MotorService motor = new MotorService();


    public void run() {
        loadData();
        view = new Console(this);
        viewUser = new UserView();

        while (!exit) {
            view.login();
            pilihan = AppHelper.inputInt(input);
            view.auth(pilihan);
            exit = isExit(pilihan);
            
        }
        AppHelper.clearScreen();
        System.out.println("Program Berhenti.");
        input.close();
    }

     public Scanner getScanner() {
        return input;
    }

    public void masuk() {
        isAdmin();
    }
    
    public void daftar() {
        AppHelper.clearScreen();
        view.daftarBaru();
        System.out.print("Masukan Nama:");
        String nama = AppHelper.inputStr(input);

        System.out.print("Masukan Kontak:");
        String kontak = AppHelper.inputStr(input);
        
        String idUser = "";
        
        user.tambahUser(idUser, nama, kontak);
        user.saveData();
        AppHelper.enterToContinue(input);

    }
    
    private void isAdmin() {
        System.out.print("Masukan ID / Nama: ");
        String data = AppHelper.inputStr(input);

        if(data.equalsIgnoreCase("admin")) {
            while (!exit) {
                AppHelper.clearScreen();
                view.menuAdmin();
                pilihan = AppHelper.inputInt(input);
                view.menuPilihanAdmin(pilihan);
                exit = isExit(pilihan);
            }
        } else if(user.cariData(data) != null) {
            AppHelper.clearScreen();
                System.out.println("\n=== APLIKASI PENYEWAAAN KENDARAAN ===");
                System.out.println("--------------------------------");
                System.out.println();
                System.out.println("ID   : " + user.cariData(data).getId());
                System.out.println("NAMA : " + user.cariData(data).getNama());
                System.out.println();
                System.out.println();
                viewUser.menuUser();
                pilihan = AppHelper.inputInt(input);
        } else {
            System.out.println("Data tidak ditemukan.");
            AppHelper.enterToContinue(input);
        }

    }

    public boolean isExit(int num) {
         if(num == 0) {
            return true;
        }
        
        return false;
    }

    public void listKendaraan() {
        AppHelper.clearScreen();
        view.menuPilihKendaraan();
        pilihan = AppHelper.inputInt(input);
        view.pilihKendaraan(pilihan);
        AppHelper.enterToContinue(input);
    }


    public void listMobil() {
        AppHelper.clearScreen();
        mobil.getAllMobil();
    }

    public void listMotor() {
        AppHelper.clearScreen();
        motor.getAllMotor();
    }

    public void cariKendaraan() {
        AppHelper.clearScreen();
        System.out.println("**Masukan Id/Merk kendaraan");
        System.out.print("Cari: ");
        String keyword = AppHelper.inputStr(input);
        System.out.println();
        if(mobil.cariData(keyword) != null) {
            mobil.cariData(keyword).info();
        } else if(motor.cariData(keyword) != null) {
            motor.cariData(keyword).info();
        } else {
            System.out.println("Data tidak ditemukan.");
        }
        AppHelper.enterToContinue(input);

    }

    public void kendaraanDiSewa() {}

    public void pengembalianKendaraan() {}

    public void riwayatSewwa() {}

    public void tambahKendaraan() {
        AppHelper.clearScreen();
        view.formTambahUnit();
        pilihan = AppHelper.inputInt(input);
        view.formPilihan(pilihan);
        AppHelper.enterToContinue(input);
    }

    public void formTambahMobil() {
        System.out.println("\n=== REGISTRASI UNIT MOBIL ===");
        System.out.println("--------------------------------");
        System.out.println();
        String id = null;
        System.out.print("Merk Mobil: ");
        String merk = AppHelper.inputStr(input);
        System.out.print("Tahun Mobil: ");
        String tahun = AppHelper.inputStr(input);
        System.out.print("Harga Sewa: ");
        double hargaSewa = AppHelper.inputDouble(input);
        System.out.print("Jumlah Kursi: ");
        int jumlahKursi = AppHelper.inputInt(input);
        System.out.print("Tipe Transmisi: ");
        String tipeTransmisi = AppHelper.inputStr(input);
        mobil.tambahMobil("", merk, tahun, hargaSewa, jumlahKursi, tipeTransmisi);
        System.out.println("Mobil berhasil ditambahkan.");
        AppHelper.enterToContinue(input);
    }
    
    public void formTambahMotor() {
        System.out.println("\n=== REGISTRASI UNIT MOTOR ===");
        System.out.println("--------------------------------");
        System.out.println();
        String id = null;
        System.out.print("Merk Motor: ");
        String merk = AppHelper.inputStr(input);
        System.out.print("Tahun Motor: ");
        String tahun = AppHelper.inputStr(input);
        System.out.print("Harga Sewa: ");
        double hargaSewa = AppHelper.inputDouble(input);
        System.out.print("Kapasitas Mesin: ");
        String kapasitasMesin = AppHelper.inputStr(input);
        motor.tambahMotor("", merk, tahun, hargaSewa, kapasitasMesin);
        System.out.println("Motor berhasil ditambahkan.");

    }

    public void updateKendaraan() {
        String id = AppHelper.inputStr(input);
        
        if(mobil.cariData(id) != null) {
           updateMobil(id);
        } else if(motor.cariData(id) != null) {
            updateMotor(id);
        } else {
            System.out.println("Data tidak ditemukan.");
        }
        AppHelper.enterToContinue(input);
        
    }
    

    public void updateMotor(String idMotor) {
        AppHelper.clearScreen();

        System.out.println("\n=====     UPDATE UNIT     ======");
        System.out.println("--------------------------------");
        System.out.println();
        System.out.println("Ditemukan: ");

        Motor exMotor = motor.cariData(idMotor);
        String id = exMotor.getId();
        String merk = exMotor.getMerk();
        String tahun = exMotor.getTahun();
        double hargaSewa = exMotor.getHargaSewa();
        String kapasitasMesin = exMotor.getKapasitasMesin();
        
        exMotor.info();

        System.out.println("");
        System.out.println("**Biarkan kosong jika tidak ingin mengubah.");
        System.out.println("");
        System.out.print("Ganti ID: ");
        String idBaru = input.nextLine();
        
        System.out.print("Ganti Merk: ");
        String merkBaru = input.nextLine();
        
        System.out.print("Ganti Tahun: ");
        String tahunBaru = input.nextLine();
        
        System.out.println("**Masukan 0 jika tidak ingin mengubah.");
        System.out.print("Ganti Harga Sewa: ");
        double hargaSewaBaru = AppHelper.inputDouble(input);
        
        System.out.print("Ganti Kapasitas Mesin: ");
        String kapasitasMesinBaru = input.nextLine();
        
        motor.updateData(id, idBaru, merkBaru, tahunBaru, hargaSewaBaru, kapasitasMesinBaru);
        System.out.println("Data motor berhasil diperbarui.");
    }

    public void updateMobil(String idMobil) {
        AppHelper.clearScreen();

        System.out.println("\n=====     UPDATE UNIT     ======");
        System.out.println("--------------------------------");
        System.out.println();
        System.out.println("Ditemukan: ");

        Mobil exMobil = mobil.cariData(idMobil);
        String id = exMobil.getId();
        String merk = exMobil.getMerk();
        String tahun = exMobil.getTahun();
        double hargaSewa = exMobil.getHargaSewa();
        int jumlahKursi = exMobil.getJumlahKursi();
        String tipeTransmisi = exMobil.getTipeTransmisi();

        exMobil.info();

        System.out.println("");
        System.out.println("**Biarkan kosong jika tidak ingin mengubah.");
        System.out.println("");
        System.out.print("Ganti ID: ");
        String idBaru = input.nextLine();
        
        System.out.print("Ganti Merk: ");
        String merkBaru = input.nextLine();
        
        System.out.print("Ganti Tahun: ");
        String tahunBaru = input.nextLine();
        
        System.out.println("**Masukan 0 jika tidak ingin mengubah.");
        System.out.print("Ganti Harga Sewa: ");
        double hargaSewaBaru = AppHelper.inputDouble(input);
        
        System.out.print("Ganti Jumlah Kursi: ");
        int jumlahKursiBaru = input.nextInt();

        System.out.print("Ganti Tipe Transmisi: ");
        String tipeTransmisiBaru = input.nextLine();

        mobil.updateData(id, idBaru, merkBaru, tahunBaru, hargaSewaBaru, jumlahKursiBaru, tipeTransmisiBaru);
        System.out.println("Data motor berhasil diperbarui.");
    }

    public void hapusKendaraan(String idKendaraan) {
        Mobil exMobil;
        Motor exMotor;

        String konfirmasi;

        if(mobil.cariData(idKendaraan) != null) {
           exMobil = mobil.cariData(idKendaraan);
           System.out.print("Data: ");
           exMobil.info();
           System.out.println();
           System.out.println(exMobil.getId() + " - " + exMobil.getMerk() + " akan dihapus? (y/n)" );
           konfirmasi = AppHelper.inputStr(input);

           if(konfirmasi.equalsIgnoreCase("y")) {
               mobil.hapusData(exMobil.getId());
           } else {
               System.out.println("Data tidak dihapus.");
               return;
           }
           
        } else if(motor.cariData(idKendaraan) != null) {
            exMotor = motor.cariData(idKendaraan);
            System.out.print("Data: ");
            exMotor.info();

            System.out.println(exMotor.getId() + " - " + exMotor.getMerk() + " akan dihapus? (y/n)" );
            konfirmasi = AppHelper.inputStr(input);

            if(konfirmasi.equalsIgnoreCase("y")) {
               motor.hapusData(exMotor.getId());
           } else {
               System.out.println("Data tidak dihapus.");
               return;
           }

        } else {
            System.out.println("Data tidak ditemukan.");
        }

        System.out.println("Data berhasil dihapus.");
        AppHelper.enterToContinue(input);
    }

    public void loadData() {
        user.initData();
        mobil.initData();
        motor.initData();

    }



}
