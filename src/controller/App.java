package controller;

import helper.AppHelper;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import model.Mobil;
import model.Motor;
import model.Penyewaan;
import model.StatusSewa;
import model.User;
import service.MobilService;
import service.MotorService;
import service.PenyewaanService;
import service.UserService;
import view.Console;
import view.UserView;

public class App {

    private Scanner input;
    private String getNama;
    private String getId;
    private int pilihan;
    private boolean exit;

    private Console view;
    private UserView viewUser;
    private UserService user = new UserService();
    private MobilService mobil = new MobilService();
    private MotorService motor = new MotorService();
    private PenyewaanService penyewaan = new PenyewaanService(mobil, motor, user);


    public void run() {
        loadData();
        view = new Console(this);
        viewUser = new UserView(this);
        input = new Scanner(System.in);

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
        auth();
    }

    public String getNamaUser() {
        return getNama;
    }

    public String getIdUser() {
        return getId;
    }

    public void isAdmin() {
        while (!exit) {
            AppHelper.clearScreen();
            view.menuAdmin();
            pilihan = AppHelper.inputInt(input);
            view.menuPilihanAdmin(pilihan);
            exit = isExit(pilihan);
        }
    }

    public void isUser() {
        while (!exit) {
            AppHelper.clearScreen();
            viewUser.menuUser();
            pilihan = AppHelper.inputInt(input);
            viewUser.menuPilihanUser(pilihan);
            exit = isExit(pilihan);
        }
    }
    
    private void auth() {
        String data;
        
        System.out.print("Masukan ID / Nama: ");
        data = AppHelper.inputStr(input);

        if (data.equalsIgnoreCase("admin")) {
            isAdmin();
        } else if (user.cariData(data) != null) {
            getNama = user.cariData(data).getNama();
            getId = user.cariData(data).getId();
            isUser();
        } else {
            System.out.println("Data tidak ditemukan.");
            AppHelper.enterToContinue(input);
        }

    }

    public boolean isExit(int num) {
        if (num == 0) {
            return true;
        }

        return false;
    }

    public void listUser() {
        viewUser.listDataUser();
        List<User> getDataUser = user.getAllUser();
        int no = 1;

        for (int i = 0; i < getDataUser.size(); i++) {
            System.out.printf("%-2s | %-5s | %-30s | %-20s |\n",
                    no++,
                    getDataUser.get(i).getId(),
                    getDataUser.get(i).getNama(),
                    getDataUser.get(i).getKontak());
        }
        System.out.println("--------------------------------------------------------------------");
        System.out.println();
        view.pilhanData();
        pilihan = AppHelper.inputInt(input);

        switch (pilihan) {
            case 1:
                newUser();
                break;
            case 2:
                AppHelper.clearScreen();
                viewUser.formUpdateUser();
                break;
            case 3:
                AppHelper.clearScreen();
                viewUser.formHapusUser();
                break;
            case 4:
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi");
                break;

        }
    }

    public void riwayatPenyewaaan() {
        List<Penyewaan> getDataSewa = penyewaan.getAllPenyewaan();
        int no = 1;
        AppHelper.clearScreen();
        view.riwayatSewa();

        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        double totalHarga;

        for (int i = 0; i < getDataSewa.size(); i++) {
            totalHarga = getDataSewa.get(i).getTotalHarga();
            String hargaRupiah = formatRupiah.format(totalHarga);

            System.out.printf("| %-2s | %-7s | %-7s | %-7s | %-12s | %-12s | %-15s | %-8s |\n",
                    no++,
                    getDataSewa.get(i).getIdSewa(),
                    getDataSewa.get(i).getIdKendaraan(),
                    getDataSewa.get(i).getIdUser(),
                    getDataSewa.get(i).getTglSewa(),
                    getDataSewa.get(i).getTglKembali(),
                    hargaRupiah,
                    getDataSewa.get(i).getStatusSewa());
            
        }
        System.out.println("-----------------------------------------------------------------------------------------------");

        AppHelper.enterToContinue(input);
    }

    public void listKendaraanDisewa() {
        List<Penyewaan> getDataSewa = penyewaan.getAllPenyewaan();
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
       
        int no = 1;

        view.listUnitDisewa();
        
        for (int i = 0; i < getDataSewa.size(); i++) {
            if(getDataSewa.get(i).getStatusSewa() == StatusSewa.AKTIF) {

                String idSewa = getDataSewa.get(i).getIdSewa();
                String idUnit = getDataSewa.get(i).getIdKendaraan();
                String merkUnit;
                String idUser = getDataSewa.get(i).getIdUser();
                LocalDate tglSewa = getDataSewa.get(i).getTglSewa();
                LocalDate tglKembali = getDataSewa.get(i).getTglKembali();
                double harga;
                double totalHarga = getDataSewa.get(i).getTotalHarga();
    
                
                if(idUnit.startsWith("R4")) {
                    merkUnit = mobil.getMobil(idUnit).getMerk();
                    harga = mobil.getMobil(idUnit).getHargaSewa();
                } else {
                    merkUnit = motor.getMotor(idUnit).getMerk();
                    harga = motor.getMotor(idUnit).getHargaSewa();
                }
    
                long selisihHari = ChronoUnit.DAYS.between(tglSewa, tglKembali);
                totalHarga = harga * selisihHari;
        
                String HargaRupiah = formatRupiah.format(harga);
                String totalHargaRupiah = formatRupiah.format(totalHarga);
    
                System.out.printf("| %-2s | %-5s | %-5s | %-10s | %-7s | %-13s | %-13s |\n",
                         no++,
                         idSewa,
                         idUnit,
                         merkUnit,
                         idUser,
                         tglSewa,
                         tglKembali);
            }
        }
        System.out.println("-----------------------------------------------------------------------------");
        AppHelper.enterToContinue(input);
    }

    public void pengembalianUnit() {
        List<Penyewaan> getDataSewa = penyewaan.getAllPenyewaan();
        int no = 1;
        
        String merkUnit;
        String jenisUnit;
        double harga;

        if(getDataSewa.isEmpty()) {
            System.out.println("\nSemua kendaraan masih tersedia.");
            AppHelper.enterToContinue(input);
            return;
        }

        view.pengembalian();

        for (int i = 0; i < getDataSewa.size(); i++) {
            if(getDataSewa.get(i).getStatusSewa() == StatusSewa.AKTIF) {

                String idSewa = getDataSewa.get(i).getIdSewa();
                String idUnit = getDataSewa.get(i).getIdKendaraan();
                String idUser = getDataSewa.get(i).getIdUser();
                String namaUser = user.cariData(idUser).getNama();
            
                if(idUnit.startsWith("R4")) {
                    merkUnit = mobil.getMobil(idUnit).getMerk();
                    harga = mobil.getMobil(idUnit).getHargaSewa();
                    jenisUnit = "Mobil";
                } else {
                    merkUnit = motor.getMotor(idUnit).getMerk();
                    harga = motor.getMotor(idUnit).getHargaSewa();
                    jenisUnit = "Motor";
                }
                
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
                String HargaSewa = formatRupiah.format(harga);
                
                System.out.printf("| %-2s | %-7s | %-15s | %-10s | %-10s | %-17s |\n",
                        no++,
                        idSewa,
                        namaUser,
                        merkUnit,
                        jenisUnit,
                        HargaSewa);
                        
            }
        }
        System.out.println("--------------------------------------------------------------------------------");
        
        System.out.println("\n**Pilih nomor 0 untuk kembali...");
        System.out.println("\nPilih Nomor Kendaraan yang akan dikembalikan: ");
        System.out.print("Pilih No: ");
        int nm = AppHelper.inputInt(input);
        Penyewaan pengembalian = penyewaan.kembalikanKendaraan(nm);

        System.out.println(pengembalian.getIdKendaraan());

        view.invoice();

        if(nm == 0) {
            return;
        }
        
        if(pengembalian.getIdKendaraan().startsWith("R4")) {
            harga = mobil.getMobil(pengembalian.getIdKendaraan()).getHargaSewa();
            merkUnit = mobil.getMobil(pengembalian.getIdKendaraan()).getMerk();
        } else {
            harga = motor.getMotor(pengembalian.getIdKendaraan()).getHargaSewa();
            merkUnit = motor.getMotor(pengembalian.getIdKendaraan()).getMerk();
        }
        
        System.out.printf("| %-7s | %-10s | %-10s | %-10s | %-10s | %-17s |\n",
                    pengembalian.getIdSewa(),
                    user.cariData(pengembalian.getIdUser()).getNama(),
                    merkUnit,
                    pengembalian.getTglSewa(),
                    pengembalian.getTglKembali(),
                    LocalDate.of(2025, 8, 8));
        System.out.println("-----------------------------------------------------------------------------------");
        
        long telat = (LocalDate.of(2025, 8, 8).getDayOfMonth() - pengembalian.getTglKembali().getDayOfMonth());
        double denda = harga * telat;

        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        
        System.out.println();
        System.out.println("Harga sewa/hari              : " + formatRupiah.format(harga));
        System.out.println("Total Harga Sewa             : " + formatRupiah.format(pengembalian.getTotalHarga()));
        System.out.println("Keterlambatan pengembalian   : " + telat + " hari");
        System.out.println("Denda                        : " + formatRupiah.format(denda));
        System.out.println("\nJumlah yang harus Dibayarkan : " + formatRupiah.format(pengembalian.getTotalHarga() + denda) );
        System.out.println("\nLanjut ke Pembayaran.. ");
        input.nextLine();
        AppHelper.clearScreen();
        System.out.println("\n      Pembayaran          ");
        System.out.println("1. Cash ");
        System.out.println("2. Transfer");
        System.out.println("3. Batalkan");
        System.out.println("\nPilih Pembayaran: ");

        int bayar = AppHelper.inputInt(input);
        switch (bayar) {
            case 1:
                System.out.println("Pembayaran berhasil.");
                break;
            case 2:
                System.out.println("Pembayaran berhasil.");
                break;
            case 3:
                System.out.println("Pembayaran dibatalkan.");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                break;
        }

        if(bayar != 3 ) {
            pengembalian.setTotalHarga(pengembalian.getTotalHarga() + denda);
            pengembalian.setTglKembali(LocalDate.of(2025, 8, 8));
            penyewaan.sts(pengembalian.getIdSewa());
        }
        AppHelper.enterToContinue(input);
                
    }

    public void listKendaraan() {
        AppHelper.clearScreen();
        view.menuPilihKendaraan();
        pilihan = AppHelper.inputInt(input);
        view.pilihKendaraan(pilihan);
    }

    public void mobilTersedia() {
        List<Mobil> getDataMobil = mobil.getAllMobil();
        int no = 1;

        for (int i = 0; i < getDataMobil.size(); i++) {
        if (getDataMobil.get(i).getStatusSewa() == StatusSewa.TERSEDIA) {
            System.out.printf("%-2d | %-5s | %-10s | %-10s | %-10s | %-13s | %-13s |\n",
                no++,
                getDataMobil.get(i).getId(),
                getDataMobil.get(i).getMerk(),
                getDataMobil.get(i).getTahun(),
                getDataMobil.get(i).getHargaSewa(),
                getDataMobil.get(i).getJumlahKursi(),
                getDataMobil.get(i).getTipeTransmisi());
        }
        }
        System.out.println("-----------------------------------------------------------------------------------");
        
        System.out.println("**Pilih nomor 0 untuk membatalkan.");
        System.out.print("Pilih nomor kendaran yang akan disewa: ");
        int nm = AppHelper.inputInt(input);
        
        if (nm != 0) {
            System.out.print("Sewa untuk berapa hari ? ");
            long hari = AppHelper.inputInt(input);
            AppHelper.clearScreen();

            LocalDate tglKembali = LocalDate.now().plusDays(hari);
            String idUser = getIdUser();
            String idKendaraan = mobil.sewaMobil(nm);
            String merkKendaraan = mobil.getMobil(idKendaraan).getMerk();
            double hargaSewa = mobil.getMobil(idKendaraan).getHargaSewa();
            double totalHarga = hargaSewa * hari;

            System.out.println("\n[ Sewa: atas nama " + getNamaUser() + " nama unit "+ merkKendaraan + " untuk " + hari + " hari. ]");
            System.out.println("\nTotal Harga Sewa: " + totalHarga);

            penyewaan.sewaKendaraan(idUser, idKendaraan, tglKembali,totalHarga);
            AppHelper.enterToContinue(input);
        }

    }

    public void listMobil() {
        List<Mobil> getDataMobil = mobil.getAllMobil();
        int no = 1;

        for (int i = 0; i < getDataMobil.size(); i++) {
            System.out.printf("%-2d | %-5s | %-10s | %-10s | %-10s | %-13s | %-13s |\n",
                    no++,
                    getDataMobil.get(i).getId(),
                    getDataMobil.get(i).getMerk(),
                    getDataMobil.get(i).getTahun(),
                    getDataMobil.get(i).getHargaSewa(),
                    getDataMobil.get(i).getJumlahKursi(),
                    getDataMobil.get(i).getTipeTransmisi());
        }
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println();
        view.pilhanData();
        pilihan = AppHelper.inputInt(input);

        switch (pilihan) {
            case 1:
                formTambahMobil();
                break;
            case 2:
                AppHelper.clearScreen();
                view.formUpdateUnit();
                break;
            case 3:
                AppHelper.clearScreen();
                view.formHapusUnit();
                break;
            case 4:
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi");
                break;

        }

    }

    public void listMotor() {
        List<Motor> getDataMotor = motor.getAllMotor();
        int no = 1;

        for (int i = 0; i < getDataMotor.size(); i++) {
            System.out.printf("%-2d | %-5s | %-10s | %-10s | %-10s | %-15s |\n",
                    no++,
                    getDataMotor.get(i).getId(),
                    getDataMotor.get(i).getMerk(),
                    getDataMotor.get(i).getTahun(),
                    getDataMotor.get(i).getHargaSewa(),
                    getDataMotor.get(i).getKapasitasMesin());
        }
        System.out.println("---------------------------------------------------------------------");
        System.out.println();
        view.pilhanData();
        pilihan = AppHelper.inputInt(input);

        switch (pilihan) {
            case 1:
                formTambahMotor();
                break;
            case 2:
                AppHelper.clearScreen();
                view.formUpdateUnit();
                break;
            case 3:
                AppHelper.clearScreen();
                view.formHapusUnit();
                break;
            case 4:
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi");
                break;
        }
    }

    public void motorTersedia() {
        List<Motor> getDataMotor = motor.getAllMotor();
        int no = 1;

        for (int i = 0; i < getDataMotor.size(); i++) {
            if (getDataMotor.get(i).getStatusSewa() == StatusSewa.TERSEDIA) {
                System.out.printf("%-2d | %-5s | %-10s | %-10s | %-10s | %-15s |\n",
                    no++,
                    getDataMotor.get(i).getId(),
                    getDataMotor.get(i).getMerk(),
                    getDataMotor.get(i).getTahun(),
                    getDataMotor.get(i).getHargaSewa(),
                    getDataMotor.get(i).getKapasitasMesin());
            }
        }
        System.out.println("---------------------------------------------------------------------");
        System.out.println("**Pilih nomor 0 untuk membatalkan.");
        System.out.print("Pilih nomor kendaran yang akan disewa: ");
        int nm = AppHelper.inputInt(input);
        
        if (nm != 0) {
            System.out.print("Sewa untuk berapa hari ? ");
            long hari = AppHelper.inputInt(input);
            AppHelper.clearScreen();

            LocalDate tglKembali = LocalDate.now().plusDays(hari);
            String idUser = getIdUser();
            String idKendaraan = motor.sewaMotor(nm);
            String merkKendaraan = motor.getMotor(idKendaraan).getMerk();
            double hargaSewa = motor.getMotor(idKendaraan).getHargaSewa();
            double totalHarga = hargaSewa * hari;

            System.out.println("\n[ Sewa: atas nama " + getNamaUser() + " nama unit "+ merkKendaraan + " untuk " + hari + " hari. ]");
            System.out.println("\nTotal Harga Sewa: " + totalHarga);

            penyewaan.sewaKendaraan(idUser, idKendaraan, tglKembali,totalHarga);
            AppHelper.enterToContinue(input);
        }

    }

    public void cariKendaraan() {
        AppHelper.clearScreen();
        System.out.println("**Masukan Id/Merk kendaraan");
        System.out.print("Cari: ");
        String keyword = AppHelper.inputStr(input);
        System.out.println();
        if (mobil.cariData(keyword) != null) {
            mobil.cariData(keyword).info();
        } else if (motor.cariData(keyword) != null) {
            motor.cariData(keyword).info();
        } else {
            System.out.println("Data tidak ditemukan.");
        }
        AppHelper.enterToContinue(input);

    }

    public void newUser() {
        AppHelper.clearScreen();
        System.out.println("\n======== DATFAR USER BARU ========");
        System.out.println("---------------------------------");
        System.out.println();

        System.out.print("Masukan Nama:");
        String nama = AppHelper.inputStr(input);

        System.out.print("Masukan Kontak:");
        String kontak = AppHelper.inputStr(input);

        String idUser = "";

        user.tambahUser(idUser, nama, kontak);
        System.out.println("User Baru berhasil ditambahkan.");
        AppHelper.enterToContinue(input);
    }

    public void tambahKendaraan() {
        AppHelper.clearScreen();
        view.formTambahUnit();
        pilihan = AppHelper.inputInt(input);
        view.formPilihan(pilihan);
        AppHelper.enterToContinue(input);
    }

    public void formTambahMobil() {
        AppHelper.clearScreen();
        System.out.println("\n=== TAMBAH UNIT MOBIL ===");
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
        AppHelper.clearScreen();
        System.out.println("\n=== TAMBAH UNIT MOTOR ===");
        System.out.println("--------------------------------");
        System.out.println();

        System.out.print("Merk Motor: ");
        String merk = AppHelper.inputStr(input);

        System.out.print("Tahun Motor: ");
        String tahun = AppHelper.inputStr(input);

        System.out.print("Harga Sewa: ");
        double hargaSewa = AppHelper.inputDouble(input);

        System.out.print("Kapasitas Mesin: ");
        String kapasitasMesin = AppHelper.inputStr(input);

        motor.tambahMotor(merk, tahun, hargaSewa, kapasitasMesin);
        System.out.println("Motor berhasil ditambahkan.");

    }

    public void updateKendaraan() {
        String id = AppHelper.inputStr(input);

        if (mobil.cariData(id) != null) {
            updateMobil(id);
        } else if (motor.cariData(id) != null) {
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

        if (mobil.cariData(idKendaraan) != null) {
            exMobil = mobil.cariData(idKendaraan);
            System.out.print("Data: ");
            exMobil.info();
            System.out.println();
            System.out.println(exMobil.getId() + " - " + exMobil.getMerk() + " akan dihapus? (y/n)");
            konfirmasi = AppHelper.inputStr(input);

            if (konfirmasi.equalsIgnoreCase("y")) {
                mobil.hapusData(exMobil.getId());
                System.out.println("Data berhasil dihapus.");
            } else {
                System.out.println("Data tidak dihapus.");
                return;
            }

        } else if (motor.cariData(idKendaraan) != null) {
            exMotor = motor.cariData(idKendaraan);
            System.out.print("Data: ");
            exMotor.info();

            System.out.println(exMotor.getId() + " - " + exMotor.getMerk() + " akan dihapus? (y/n)");
            konfirmasi = AppHelper.inputStr(input);

            if (konfirmasi.equalsIgnoreCase("y")) {
                motor.hapusData(exMotor.getId());
                System.out.println("Data berhasil dihapus.");
            } else {
                System.out.println("Data tidak dihapus.");
                return;
            }

        } else {
            System.out.println("Data tidak ditemukan.");
        }

        AppHelper.enterToContinue(input);
    }

    public void hapusUser(String idUser) {
        User exUser;

        String konfirmasi;

        if (user.cariData(idUser) != null) {
            exUser = user.cariData(idUser);
            System.out.print("Data: ");
            System.out.print(exUser.toString());

            System.out.println();
            System.out.println(exUser.getId() + " - " + exUser.getNama() + " akan dihapus? (y/n)");
            konfirmasi = AppHelper.inputStr(input);

            if (konfirmasi.equalsIgnoreCase("y")) {
                user.hapusData(exUser.getId());
                System.out.println("Data berhasil dihapus.");
            } else {
                System.out.println("Data tidak dihapus.");
                return;
            }
        } else {
            System.out.println("Data tidak ditemukan.");
        }

        AppHelper.enterToContinue(input);

    }

    public void riwayatSewaUser() {
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        List<Penyewaan> getDataSewa = penyewaan.getAllPenyewaan();
        String jenisUnit;
        String merkUnit;
        int no = 1;

        for (int i = 0; i < getDataSewa.size(); i++) {
            String idSewa = getDataSewa.get(i).getIdSewa();
            String idUnit = getDataSewa.get(i).getIdKendaraan();
            
            if(idUnit.startsWith("R4")) {
                merkUnit = mobil.getMobil(idUnit).getMerk();
                jenisUnit = "Mobil";
            } else {
                merkUnit = motor.getMotor(idUnit).getMerk();
                jenisUnit = "Motor";

            }

            double totalHarga = getDataSewa.get(i).getTotalHarga();

            String hargaRupiah = formatRupiah.format(totalHarga);

            if (getDataSewa.get(i).getIdUser().equalsIgnoreCase(getIdUser())) {
                System.out.printf("| %-2s | %-5s | %-10s | %-10s | %-12s | %-15s | %-18s |\n",
                         no++,
                         idSewa,
                         merkUnit,
                         jenisUnit,
                         getDataSewa.get(i).getTglSewa(),
                         getDataSewa.get(i).getTglKembali(),
                         hargaRupiah);
            }
        }
        System.out.println("----------------------------------------------------------------------------------------------");
        AppHelper.enterToContinue(input);
    }

    public void loadData() {
        user.initData();
        mobil.initData();
        motor.initData();
        penyewaan.initData();

    }

}
