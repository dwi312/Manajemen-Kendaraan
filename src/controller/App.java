package controller;

import helper.AppHelper;
import java.util.Scanner;
import service.UserService;
import view.AppView;


public class App {
    private int pilihan;
    private boolean exit;
    private Scanner input = new Scanner(System.in);

    private AppView view;
    private UserService user = new UserService();


    public void run() {
        user.initData();
        view = new AppView(this);

        while (!exit) {
            view.login();
            pilihan = AppHelper.inputInt(input);
            view.auth(pilihan);
            exit = isExit(pilihan);
            
        }
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
            AppHelper.clearScreen();
            view.menuAdmin();
            pilihan = AppHelper.inputInt(input);
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


}
