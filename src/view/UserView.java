package view;

import controller.App;
import helper.AppHelper;

public class UserView {
    private App app;

    public UserView(App app) {
        this.app = app;
    }

    public void menuUser() {
        System.out.println("\n=== APLIKASI PENYEWAAAN KENDARAAN ===");
        System.out.println("--------------------------------");
        System.out.println("ID   : " + app.getIdUser());
        System.out.println("Nama : " + app.getNamaUser());
        System.out.println();
        System.out.println("1. Sewa Kendaraan");
        System.out.println("2. Riwayat");
        System.out.println("0. Keluar");
        System.out.println("--------------------------------");
        System.out.print("Pilih menu: ");
    }

    public void menuPilihanUser(int pilihan) {
        switch (pilihan) {
            case 1:
               app.listKendaraan();
                break;
            case 2:
                riwayatSewa();
                break;
            case 0:
                app.isExit(pilihan);
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi");
                break;
        } 
    }

    public void listDataUser() {
        AppHelper.clearScreen();
        System.out.println("====================      LIST DATA PENYEWA      ===================");
        System.out.println();
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("%-2s | %-5s | %-30s | %-20s |\n",
                         "No", "ID", "Nama", "Kontak");
        System.out.println("--------------------------------------------------------------------");
    }

    public void formUpdateUser() {
        System.out.println("\n=====     UPDATE DATA PENYEWA     ======");
        System.out.println("----------------------------------------");
        System.out.println();
        System.out.print("Masukan ID: ");
        
    }

    public void formHapusUser() {
        System.out.println("\n=====     HAPUS DATA PENYEWA     ======");
        System.out.println("---------------------------------------");
        System.out.println();
        System.out.print("Masukan ID: ");
        String id = AppHelper.inputStr(app.getScanner());
        app.hapusUser(id);
    }

    public void riwayatSewa() {
        AppHelper.clearScreen();
        System.out.println("\n=============================          RIWAYAT PENYEWAAN          ============================");
        System.out.println();
        System.out.println("ID   : " + app.getIdUser());
        System.out.println("Nama : " + app.getNamaUser());
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.printf("| %-2s | %-5s | %-10s | %-10s | %-10s | %-10s | %-18s |\n",
                         "No", "ID", "Merk", "Jenis Unit", "Tanggal Sewa", "Tanggal Kembali", "Total Harga");
        System.out.println("----------------------------------------------------------------------------------------------");
        app.riwayatSewaUser();
    }

    

}
