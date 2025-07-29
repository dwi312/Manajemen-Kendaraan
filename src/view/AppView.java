package view;

import controller.App;
import helper.AppHelper;

public class AppView {
    private App app;

    public AppView(App app) {
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

    public void menuAdmin() {
        System.out.println("\n=== SISTEM MANAJEMEN KENDARAAN ===");
        System.out.println("--------------------------------");
        System.out.println();
        System.out.println("1. Lihat Daftar Kendaraan");
        System.out.println("2. Cari Kendaraan");
        System.out.println("3. Daftar Kendaraan Sedang Disewa");
        System.out.println("4. Pengembalian Kendaraan");
        System.out.println("5. Riwayat Penyewaaan");
        System.out.println("6. Tambah Kendaraan");
        System.out.println("7. Ubah Data Kendaraan");
        System.out.println("8. Hapus Kendaraan");
        System.out.println("0. Keluar");
        System.out.println("--------------------------------");
        System.out.print("Pilih menu: ");
    }


    public void menuUser() {
        System.out.println("1. Pilih Kendaraan");
        System.out.println("2. Riwayat");
        System.out.println("0. Keluar");
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

    public void menuPilihanAdmin(int pilihan) {
        switch (pilihan) {
            case 1:
                
                break;
            case 2:
                
                break;
            case 3:
                
                break;
            case 4:
                
                break;
            case 5:
                
                break;
            case 6:
                
                break;
            case 7:
                
                break;
            case 0:
                app.isExit(pilihan);
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi");
                break;
        } 
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

}
