# Sistem Manajemen Kendaraan di Rental Mobil
repositori ini adalah latihan studi kasus yang mengunakan untuk mengimplementasikan class, object, inheritance, polymorphism, encapsulation, dan abstraction.

---

## Deskripsi :
Program ini merupakan aplikasi berbasis console untuk **mengelola data kendaraan** pada sebuah usaha rental mobil dan motor. Sistem ini dirancang untuk mempraktikkan konsep **Object-Oriented Programming (OOP)** meliputi **class, object, inheritance (pewarisan), polymorphism (polimorfisme), encapsulation (enkapsulasi), dan abstraction (abstraksi)**.

Program akan memiliki beberapa fitur utama seperti menambah kendaraan baru (mobil/motor), melihat daftar kendaraan, mencari kendaraan berdasarkan ID, menghitung biaya sewa, serta menampilkan detail kendaraan dengan memanfaatkan polimorfisme.

Setiap **kendaraan** (baik mobil maupun motor) memiliki atribut dasar seperti **ID, merek, tahun produksi, dan harga sewa per hari**. Untuk **mobil**, ada atribut tambahan seperti **jumlah kursi** dan **tipe transmisi** (manual/automatic). Untuk **motor**, ada atribut tambahan **kapasitas mesin** (cc).

Data kendaraan akan disimpan dalam **ArrayList<Kendaraan>**, sehingga sistem bisa menampung data secara dinamis.

### Konsep OOP yang Digunakan:
- **Class & Object**
    - Setiap jenis kendaraan (Mobil, Motor) direpresentasikan sebagai object.
    - Semua kendaraan memiliki class dasar (`Kendaraan`).
- **Encapsulation (Enkapsulasi)**
    - Semua atribut bersifat `private`.
    - Data diakses melalui **getter dan setter** untuk melindungi data.
- **Inheritance (Pewarisan)**
    - `Mobil` dan `Motor` merupakan subclass yang mewarisi `Kendaraan`.
- **Polymorphism (Polimorfisme)**
    - Method `info()` di-override pada setiap subclass agar menampilkan detail spesifik sesuai jenis kendaraan.
    - Saat menampilkan daftar kendaraan, program tidak peduli apakah objeknya Mobil atau Motor (memanfaatkan referensi `Kendaraan`).
- **Abstraction (Abstraksi)**
    - `Kendaraan` dibuat sebagai class abstrak, karena hanya menjadi blueprint dan tidak bisa diinstansiasi langsung.
    - Method `info()` didefinisikan sebagai abstrak agar wajib diimplementasikan oleh subclass.

---

## Tujuan:
- Memberikan latihan nyata dalam penerapan konsep OOP di Java.
- Menyediakan program sederhana untuk **manajemen data kendaraan rental**.
- Menunjukkan penggunaan **ArrayList** sebagai struktur data dinamis.
- Memperlihatkan cara kerja **inheritance dan polymorphism** dalam program nyata.

---

## Spesifikasi Program :
- **Struktur Data**:
    - Data kendaraan akan disimpan dalam ArrayList (agar bisa dinamis).
    - Setiap kendaraan memiliki data umum:
        - **ID Kendaraan** (String, unik)
        - **Merek** (String)
        - **Tahun Produksi** (int)
        - **Harga Sewa per Hari** (double)

- **Fitur Program**:
    1. Tambah Kendaraan (Mobil atau Motor).
    2. Lihat Daftar Kendaraan (tampilkan semua kendaraan dengan detailnya).
    3. Cari Kendaraan berdasarkan ID.
    4. Hitung Biaya Sewa (biaya per hari × jumlah hari).
    5. Tampilkan Jenis Kendaraan dengan Polimorfisme (setiap kendaraan punya method `info()` yang menampilkan info spesifik).
    6. Keluar dari program.

- **Class yang Dibuat**:
    - **Abstraksi & Hierarki Dasar**
        - `abstract class Kendaraan`
            - Atribut: `id`, `merek`, `tahun`, `hargaSewa`
            - Method abstrak: `info()` (untuk ditampilkan berbeda oleh tiap turunan)
            - Method umum: getter & setter (enkapsulasi).
    - **Pewarisan (Inheritance)**
        - `class Mobil extends Kendaraan`
            - Atribut tambahan: `jumlahKursi`, `tipeTransmisi` (manual/automatic)
            - Override `info()` untuk menampilkan detail khusus Mobil.
        - `class Motor extends Kendaraan`
            - Atribut tambahan: `kapasitasMesin` (cc)
            - Override `info()` untuk menampilkan detail khusus Motor.
    - **Polimorfisme**
        - ArrayList akan menyimpan objek `Kendaraan` (bisa Mobil atau Motor).
        - Saat menampilkan info, dipanggil `info()` → hasilnya berbeda tergantung jenis objeknya.
    - **Class Utilitas**
        - `class RentalService`
            - Menyimpan ArrayList<Kendaraan>.
            - Method: tambahKendaraan(), tampilkanSemua(), cariKendaraan(), hitungBiayaSewa().
    - **Main Class**
        - `class MainApp`
            - Berisi menu utama (looping dengan Scanner).
            - Memanggil fungsi-fungsi di `RentalService`.

---

## Ketentuan:
- Gunakan Enkapsulasi: semua atribut private, akses via getter/setter.
- Gunakan Abstraksi: class Kendaraan bersifat abstract.
- Gunakan Polimorfisme: method info() berbeda tiap subclass.
- Gunakan Inheritance: Mobil dan Motor turunan dari Kendaraan.
- Gunakan ArrayList<Kendaraan> untuk menyimpan data.
- Program harus berjalan via menu berbasis console.

---

## Output yang Diharapkan (Contoh) :
---

## Tantangan Tambahan (Opsional) :
- membuat program dapat menyimpan dan menulis data.

---

#### Selamat mencoba dan Semangat Mengerjakan!