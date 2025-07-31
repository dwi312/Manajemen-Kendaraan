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
                app.daftar();
                break;
            case 0:
                app.isExit(pilihan);
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi");
                break;
        }
    }

    public void menuAdmin() {
        System.out.println("\n=== SISTEM MANAJEMEN KENDARAAN ===");
        System.out.println("--------------------------------");
        System.out.println();
        System.out.println("1. Lihat Daftar Kendaraan");
        System.out.println("2. Cari Kendaraan");
        System.out.println("3. Daftar Kendaraan Disewa");
        System.out.println("4. Pengembalian Kendaraan");
        System.out.println("5. Riwayat Penyewaaan");
        System.out.println("6. Tambah Kendaraan");
        System.out.println("7. Ubah Data Kendaraan");
        System.out.println("8. Hapus Kendaraan");
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
                app.cariKendaraan();
                break;
            case 3:
                app.kendaraanDiSewa();;
                break;
            case 4:
                app.pengembalianKendaraan();
                break;
            case 5:
                app.riwayatSewwa();
                break;
            case 6:
                app.tambahKendaraan();
                break;
            case 7:
                AppHelper.clearScreen();
                formUpdateUnit();
                break;
            case 8:
                AppHelper.clearScreen();
                formHapusUnit();
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
        System.out.println("\n=== SISTEM MANAJEMEN KENDARAAN ===");
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
                app.listMobil();
                break;
            case 2:
                app.listMotor();
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi");
                break;
        }
    }

    public void menuUser() {
        System.out.println("\n=== APLIKASI PENYEWAAAN KENDARAAN ===");
        System.out.println("--------------------------------");
        System.out.println();
        System.out.println("1. Daftar Sewa Kendaraan");
        System.out.println("2. Riwayat");
        System.out.println("0. Keluar");
        System.out.println("--------------------------------");
        System.out.print("Pilih menu: ");
    }
    
    public void menuPilihanUser(int pilihan) {
        switch (pilihan) {
            case 1:
               
                break;
            case 2:
               
                break;
            case 3:
                
                break;
            case 0:
                app.isExit(pilihan);
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi");
                break;
        } 
    }

    public void daftarBaru() {
        System.out.println("\n=== PENYEWAAN KENDARAAN ===");
        System.out.println("--------------------------------");
        System.out.println();
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

}

