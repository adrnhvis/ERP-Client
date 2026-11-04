# ERP-Client
project UTS mobile - java

# 📱 ERP Campaigns - Mobile Client

Aplikasi mobile berbasis Android (Java) untuk mengelola data kampanye (Campaign) pada ekosistem ERP-Neosantara. Aplikasi ini terintegrasi langsung dengan REST API Laravel 11 menggunakan autentikasi **Sanctum** dan dibangun di atas arsitektur **MVVM (Model-View-ViewModel)** yang bersih dan skalabel.

---

## 📸 Screenshots

| Login Screen | Main Dashboard (List) | Create Campaign |
| :---: | :---: | :---: |
| <img width="210" height="439" alt="Screenshot 2026-11-05 003455" src="https://github.com/user-attachments/assets/a407a51d-eb65-4fb4-a540-7ee65f40683a" /> <br> | <br> <img width="210" height="439" alt="Screenshot 2026-11-05 003631" src="https://github.com/user-attachments/assets/d2aedf0b-15e4-41e0-ad05-9d91a8a53628" /> | <br> <img width="205" height="452" alt="Screenshot 2026-11-05 003136" src="https://github.com/user-attachments/assets/38541140-41c2-46ce-918a-8b7b53a2cf70" /> |

---

## ✨ Fitur Utama

- **Autentikasi Aman:** Login terproteksi menggunakan token Bearer (Laravel Sanctum) yang disimpan persisten melalui `SharedPreferences`.
- **Manajemen Campaign (CRUD):**
  - Melihat daftar campaign dengan dukungan arsitektur reaktif (*Observer*).
  - Menambah data campaign baru dengan form input Material Design (termasuk Dropdown).
  - Sinkronisasi otomatis (*auto-refresh*) data terbaru setiap kali layar daftar dibuka.
- **Arsitektur Modern:** Memanfaatkan **Java 17 Record** untuk pembuatan *Data Transfer Object* (DTO) yang sangat ringkas dan bebas *boilerplate*.
- **Manajemen Sesi:** Fitur *Logout* yang membersihkan token akses dan mengembalikan pengguna ke halaman login.

---

## 🛠️ Tech Stack & Library

- **Bahasa:** Java 17
- **Arsitektur:** MVVM (Model-View-ViewModel) + Repository Pattern
- **UI & Komponen:** 
  - AndroidX AppCompat & ConstraintLayout
  - Material Design Components (MDC) untuk UI modern (Card, InputText, Dropdown, FAB)
  - RecyclerView & Adapter
- **Networking:**
  - [Retrofit2](https://square.github.io/retrofit/) (v2.9.0) - HTTP Client
  - [Gson](https://github.com/google/gson) (v2.10.1) - JSON Parser (dengan dukungan penuh Java `record`)
  - OkHttp3 Interceptor - Injeksi header Otorisasi (Bearer Token) otomatis
- **State Management:**
  - AndroidX Lifecycle (ViewModel & LiveData)

---

## 🏗️ Struktur Arsitektur (MVVM)

Proyek ini dipisahkan menjadi beberapa lapisan (*layer*) untuk memastikan *Separation of Concerns*:

1. **View (`/app/src/main/java/com/adrnhvis/erp_campaigns/`):** Berisi Activity (`MainActivity`, `LoginActivity`, `CreateCampaignActivity`) dan Adapter. Hanya bertugas mengatur UI dan mendengarkan data dari ViewModel.
2. **ViewModel (`/ViewModel/`):** Bertindak sebagai penghubung antara View dan Repository. Mempertahankan state data (`LiveData`) saat terjadi perubahan konfigurasi (seperti rotasi layar).
3. **Repository (`/repository/`):** Mengeksekusi logika asinkron pemanggilan API (melalui Retrofit) dan membungkus responsnya sebelum dikembalikan ke ViewModel.
4. **Network (`/network/`):** Konfigurasi Retrofit (`ApiClient`) dengan Interceptor untuk menyuntikkan token Sanctum secara otomatis, serta antarmuka `ApiService` untuk pemetaan endpoint REST.
5. **Model (`/model/`):** *Data Transfer Object* (DTO) yang dibangun menggunakan sintaks Java `record` untuk *parsing* JSON yang efisien.

---

## 🚀 Cara Instalasi & Setup

### Prasyarat
- Android Studio Ladybug (atau versi terbaru)
- JDK 17
- Backend API Laravel ERP-Neosantara sudah berjalan di lokal atau server.

🚀 Cara Instalasi & Setup

Langkah-langkah

Clone repositori ini:

git clone https://github.com/adrnhvis/ERP-Client.git


Buka proyek hasil clone tersebut di Android Studio.

Konfigurasi Base URL:
Buka file app/src/main/java/com/adrnhvis/erp_campaigns/network/ApiClient.java dan ubah BASE_URL sesuai dengan alamat server/API Laravel Anda.

Jika menggunakan emulator Android Studio dan server lokal php artisan serve, biarkan: http://10.0.2.2:8000/api/

Jika menggunakan perangkat fisik (HP) di jaringan WiFi yang sama, ganti dengan IP lokal komputer Anda: http://192.168.x.x:8000/api/

Lakukan Sync Project with Gradle Files.

Klik Run (Shift + F10) untuk menjalankan aplikasi di emulator atau perangkat fisik Anda.

---

## 👤 Author

**Adrian Havis**  
Mahasiswa D3 Manajemen Informatika  
*Repository GitHub:* [@adrnhvis](https://github.com/adrnhvis)
