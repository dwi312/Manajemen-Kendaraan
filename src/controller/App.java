package controller;

import helper.AppHelper;
import java.util.Scanner;
import service.MobilService;
import service.MotorService;
import service.UserService;
import view.console;


public class App {
    private int pilihan;
    private boolean exit;
    private Scanner input = new Scanner(System.in);

    private console view;
    private UserService user = new UserService();
    private MobilService mobil = new MobilService();
    private MotorService motor = new MotorService();


    public void run() {
        loadData();
        view = new console(this);

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
                System.out.println("ID   : " + user.cariData(data).getIdUser());
                System.out.println("NAMA : " + user.cariData(data).getNama());
                System.out.println();
                System.out.println();
                view.menuUser();
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
        mobil.getMobil();
    }

    public void listMotor() {
        AppHelper.clearScreen();
        motor.getMotor();
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

    public void kendaraanDiSewa() {

    }

    public void pengembalianKendaraan() {

    }

    public void riwayatSewwa() {

    }

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
        String keyword = AppHelper.inputStr(input);
        
        if(mobil.cariData(keyword) != null) {
            updateMobil(keyword);
        } else if(motor.cariData(keyword) != null) {
            updateMotor(keyword);
        } else {
            System.out.println("Data tidak ditemukan.");
        }
        
    }
    
    
    
    public void updateMobil(String keyword) {
        System.out.println("**Kosongkan data bila tidak inggin diubah**");
        System.out.println("ID: " + mobil.cariData(keyword).getId());
        String id = mobil.cariData(keyword).getId();
        System.out.print("ID Baru: ");
        String idBaru = input.nextLine();

        if(!idBaru.isEmpty()) {
            id = idBaru;
        }

        System.out.println("Merk: " + mobil.cariData(keyword).getMerk());
        String merk = mobil.cariData(keyword).getMerk();
        System.out.print("Merk Baru: ");
        String merkBaru = input.nextLine();

        if(!merkBaru.isEmpty()) {
            merk = merkBaru;
        }

        System.out.println("Tahun: " + mobil.cariData(keyword).getTahun());
        String tahun = mobil.cariData(keyword).getTahun();
        System.out.print("Tahun Baru: ");
        String tahunBaru = input.nextLine();

        if(!tahunBaru.isEmpty()) {
            tahun = tahunBaru;
        }

        System.out.println("Harga: " + mobil.cariData(keyword).getHargaSewa());
        double hargaSewa = mobil.cariData(keyword).getHargaSewa();
        System.out.println("**Masukan 0 bila tidak ingin di ubah**");
        System.out.print("Harga Sewa Baru: ");
        double hargaSewaBaru = AppHelper.inputDouble(input);
        
        if(hargaSewaBaru != 0) {
            hargaSewa = hargaSewaBaru;
        }
        
        System.out.println("Jumlah Kursi: " + mobil.cariData(keyword).getJumlahKursi());
        int jumlahKursi = mobil.cariData(keyword).getJumlahKursi();
        System.out.println("**Masukan 0 bila tidak ingin di ubah**");
        System.out.print("Jumlah Kursi Baru: ");
        int jumlahKursiBaru = AppHelper.inputInt(input);

        if(jumlahKursiBaru != 0) {
            jumlahKursi = jumlahKursiBaru;
        }

        System.out.println("Tipe Transmisi: " + mobil.cariData(keyword).getTipeTransmisi());
        String tipeTransmisi = mobil.cariData(keyword).getTipeTransmisi();
        System.out.print("Tipe Transmisi Baru: ");
        String tipeTransmisiBaru = input.nextLine();
        
        if(!tipeTransmisiBaru.isEmpty()) {
            tipeTransmisi = tipeTransmisiBaru;
        }

        mobil.updateData(id, merk, tahun, hargaSewa, jumlahKursi, tipeTransmisi);
        System.out.println("Data berhasil diupdate.");
        AppHelper.enterToContinue(input);
    }
    
    public void updateMotor(String keyword) {
        System.out.println("**Kosongkan data bila tidak inggin diubah**");
        System.out.println("ID Baru: ");

    }

    public void hapusKendaraan() {

    }

    public void loadData() {
        user.initData();
        mobil.initData();
        motor.initData();

    }



}
