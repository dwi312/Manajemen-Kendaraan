package view;

import controller.App;
import helper.AppHelper;

public class Console {
    private App app;

    public Console(App app) {
        this.app = app;
    }

    public void login() {
        AppHelper.clearScreen();
        System.out.println("\n=== APLIKASI PENYEWAAAN KENDARAAN ===");
        System.out.println("--------------------------------");
        System.out.println();
        System.out.println("1. MASUK");
        System.out.println("2. DAFTAR");
        System.out.println("0. KELUAR");
        System.out.println("--------------------------------");
        System.out.print("Pilih menu: ");
    }

    public void auth(int pilihan) {
        switch (pilihan) {
            case 1:
                app.masuk();
                break;
            case 2:
                app.newUser();
                break;
            case 0:
                app.isExit(pilihan);
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi");
                break;
        }
    }

    public void menuPilihKendaraan() {
        System.out.println("\n=== DAFTAR KENDARAAN ===");
        System.out.println("--------------------------------");
        System.out.println();
        System.out.println("1. Mobil");
        System.out.println("2. Motor");
        System.out.println("--------------------------------");
        System.out.print("Pilih menu: ");

    }

    public void pilihKendaraan(int pilihan) {
        switch (pilihan) {
            case 1:
                listDataMobil();
                if (app.getIdUser() != null) {
                    app.mobilTersedia();
                } else {
                    app.listMobil();
                }
                break;
            case 2:
                listDataMotor();
                if (app.getIdUser() != null) {
                    app.motorTersedia();
                } else {
                    app.listMotor();
                }
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi");
                break;
        }
    }

    public void pilihUnitTersedia(int pilihan) {
        switch (pilihan) {
            case 1:
            
             
                break;
            case 2:
             
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi");
                break;
        }
    }

    public void formTambahUnit() {
        System.out.println("\n=== REGISTRASI UNIT BARU ===");
        System.out.println("--------------------------------");
        System.out.println();
        System.out.println("1. MOBIL");
        System.out.println("2. MOTOR");
        System.out.println("--------------------------------");
        System.out.print("Pilih menu: ");
    }

    public void formPilihan(int pilihan) {
        switch (pilihan) {
            case 1:
               app.formTambahMobil();
                break;
            case 2:
               app.formTambahMotor();
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi");
                break;
        } 
    }

    public void formUpdateUnit() {
        System.out.println("\n=====     UPDATE UNIT     ======");
        System.out.println("--------------------------------");
        System.out.println();
        System.out.print("Masukan ID Kendaraan: ");
        app.updateKendaraan();
    }

    public void formHapusUnit() {
        System.out.println("\n=====     HAPUS UNIT     ======");
        System.out.println("--------------------------------");
        System.out.println();
        System.out.print("Masukan ID Kendaraan: ");
        String id = AppHelper.inputStr(app.getScanner());
        app.hapusKendaraan(id);
    }

    public void menuAdmin() {
        System.out.println("\n=== SISTEM MANAJEMEN KENDARAAN ===");
        System.out.println("--------------------------------");
        System.out.println();
        System.out.println("1. Data Kendaraan");
        System.out.println("2. Data Penyewa");
        System.out.println("3. Kendaraan Disewa");
        System.out.println("4. Pengembalian Kendaraan");
        System.out.println("5. Riwayat Penyewaaan");
        System.out.println("0. Keluar");
        System.out.println("--------------------------------");
        System.out.print("Pilih menu: ");
    }

    public void menuPilihanAdmin(int pilihan) {
        switch (pilihan) {
            case 1:
                app.listKendaraan();
                break;
            case 2:
                app.listUser();
                break;
            case 3:
                app.cekUnitDisewa();
                break;
            case 4:
                pengembalian();
                break;
            case 5:
                
                break;
            case 0:
                app.isExit(pilihan);
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi");
                break;
        } 
    }

    public void menuDataKendaraan() {
        System.out.println("\n=== DATA KENDARAAN ===");
        System.out.println("--------------------------------");
        System.out.println();
        System.out.println("1. Mobil");
        System.out.println("2. Motor");
        System.out.println("--------------------------------");
        System.out.print("Pilih menu: ");
    }

    public void listDataMobil() {
        AppHelper.clearScreen();
        System.out.println("\n====================             LIST DATA MOBIL               ====================");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("%-2s | %-5s | %-10s | %-10s | %-10s | %-13s | %-13s |\n",
                         "No", "ID", "Merk", "Tahun", "Harga Sewa", "Jumlah Kursi", "Transmisi");
        System.out.println("-----------------------------------------------------------------------------------");
    }

    public void listDataMotor() {
        AppHelper.clearScreen();
        System.out.println("\n====================       LIST DATA MOTOR       ====================");
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("%-2s | %-5s | %-10s | %-10s | %-10s | %-15s |\n",
                         "No", "ID", "Merk", "Tahun", "Harga Sewa", "Kapasitas Mesin");
        System.out.println("---------------------------------------------------------------------");
    }

    public void listUnitDisewa() {
        AppHelper.clearScreen();
        System.out.println("\n================         LIST UNIT YANG SEDANG DISEWA        ================");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("| %-2s | %-5s | %-5s | %-10s | %-5s | %-13s | %-13s |\n",
        "No", "TRX", "UNIT", "MERK", "ID USER", "TGL SEWA", "TGL KEMBALI");
        System.out.println("-----------------------------------------------------------------------------");
        app.listKendaraanDisewa();
    }

    public void pilhanData() {
        System.out.println("1. Menambahkan Data");
        System.out.println("2. Upadate Data");
        System.out.println("3. Hapus Data");
        System.out.println("4. Kembali ke menu");
        System.out.print("Pilih: ");

    }

    public void pengembalian() {
        AppHelper.clearScreen();
        System.out.println("\n=================            PENGEMBALIAN UNIT SEWA            =================");
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("| %-2s | %-2s | %-15s | %-10s | %-10s | %-17s |\n",
        "NO", "NO SEWA", "NAMA", "MERK", "JENIS", "HARGA SEWA");
        System.out.println("--------------------------------------------------------------------------------");
        app.pengembalianUnit();
    }

}

