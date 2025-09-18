# 📱 Aplikasi Pendaftaran — Jetpack Compose + ViewModel + Navigation
[Min SDK 24] • [Kotlin DSL • Gradle] • [Jetpack Compose • Material 3] • [StateFlow + collectAsStateWithLifecycle] • [Navigation Compose]

---

## 👤 Identitas
- **Nama**   : Hilmy Raihan Alkindy  
- **NIM**    : 235150707111042  
- **Prodi**  : Teknologi Informasi  
- **Mata Kuliah** : Pengembangan Aplikasi Mobile Terapan (A)

---

## ✨ Ringkasan
Aplikasi Android sederhana untuk **pendaftaran** dengan input **NIM**, **Nama**, **Email**, dan tombol **DAFTAR**.  
Dibangun menggunakan **Jetpack Compose**, arsitektur **MVVM**, **StateFlow** di ViewModel, serta **Navigation Compose** untuk berpindah layar.

---

## ✅ Fitur Utama
- 🧾 Form Pendaftaran: NIM, Nama, Email  
- 🔐 Validasi:
  - NIM hanya angka (min. 5 digit)
  - Nama min. 3 karakter
  - Email format standar
- 🧠 **ViewModel + StateFlow** (reactive state)
- 🧭 **Navigation Compose** (Registration ➜ Summary)
- 🎨 **Material 3** (Compose)
- 🧱 **Kotlin DSL + Gradle**

---

## 🧩 Teknologi
- Android Studio (AGP **8.0.2**), **JDK 17**
- **Kotlin 1.9.10**, Compose Compiler **1.5.3**
- Jetpack Compose (Material 3, Navigation, Foundation, Activity Compose)
- Lifecycle: **lifecycle-viewmodel-compose**, **lifecycle-runtime-compose**

---

## 🗂️ Struktur Proyek
```
app/
└─ src/main/
   ├─ AndroidManifest.xml
   ├─ java/com/example/tugas3pamterapan/
   │  ├─ MainActivity.kt
   │  ├─ data/
   │  │  └─ RegistrationData.kt
   │  ├─ navigation/
   │  │  └─ NavGraph.kt
   │  ├─ ui/
   │  │  ├─ RegistrationScreen.kt
   │  │  └─ theme/
   │  │     ├─ Color.kt
   │  │     ├─ Theme.kt
   │  │     └─ Type.kt
   │  └─ viewmodel/
   │     └─ RegistrationViewModel.kt
   └─ res/values/
      ├─ strings.xml
      └─ themes.xml
```

---

## ⚙️ Persyaratan
- **Android Studio** (Giraffe/lebih baru)
- **JDK 17** (Gradle JDK → 17)
- **Android SDK 34** (compileSdk 34)
- Koneksi internet untuk mengunduh dependencies

---

## 🚀 Menjalankan Proyek

### Opsi A — Android Studio (disarankan)
1. **File → Open…** ➜ pilih folder proyek.  
2. Pastikan **Gradle JDK = 17** (Preferences → Build, Execution, Deployment → Gradle).  
3. **Sync Project** ➜ **Run ‘app’**.

### Opsi B — CLI
```bash
# dari root proyek
./gradlew clean assembleDebug
# kemudian install ke device/emulator
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

---

## 🧭 Alur Navigasi
- **registration** → form input
- Submit sukses → pindah ke **summary?nim=...&nama=...&email=...**
- Layar **Summary** menampilkan data yang telah diisi (siap untuk screenshot)

---

## 🧪 Cara Uji Cepat
1. Buka aplikasi → isi:
   - **NIM**: 2351507 (contoh)
   - **Nama**: Hilmy Raihan Alkindy
   - **Email**: hilmy@example.com
2. Pastikan tombol **DAFTAR** aktif (validasi lolos).
3. Tekan **DAFTAR** → pastikan layar **Summary** menampilkan data.
4. **Ambil Screenshot** layar Summary (bukti pendaftaran 🎉).

---

## 🖼️ Kumpulkan Screenshot & URL Proyek
- **Screenshot**: ambil di layar **Summary** (setelah pendaftaran berhasil).
- **URL Project**:
  - Buat repo GitHub bernama `tugas3pamterapan`
  - Push kode:
    ```bash
    git init
    git add .
    git commit -m "feat: form pendaftaran - compose + viewmodel + navigation"
    git branch -M main
    git remote add origin https://github.com/<username>/tugas3pamterapan.git
    git push -u origin main
    ```
  - Sertakan tautan: `https://github.com/<username>/tugas3pamterapan`
  - Tambahkan screenshot ke folder `art/` (mis. `art/screenshot-summary.png`) dan referensikan di README.

---

## 🧱 Detail Implementasi

### MVVM + State
- `RegistrationViewModel` memegang **StateFlow<RegistrationUiState>**
- UI mengamati state via **collectAsStateWithLifecycle()**
- Action:
  - `onNimChange`, `onNamaChange`, `onEmailChange`
  - `submit { RegistrationData -> ... }`

### Validasi
- NIM: numeric & min. 5 digit
- Nama: min. 3 karakter
- Email: regex sederhana

### Navigasi
- `NavHost` start di `registration`
- Pindah ke `summary` dengan **query params** (di-encode): `nim`, `nama`, `email`

---

## 🛠️ Build Setup (Gradle Snippet Penting)
- **Kotlin/Compose kompatibel**:
  - Kotlin **1.9.10**
  - Compose compiler **1.5.3**
- Dependencies utama:
  - `androidx.activity:activity-compose`
  - `androidx.compose.material3:material3`
  - `androidx.navigation:navigation-compose`
  - `androidx.lifecycle:lifecycle-viewmodel-compose`
  - `androidx.lifecycle:lifecycle-runtime-compose`
  - `androidx.compose.foundation:foundation`

---

## 🧯 Troubleshooting
- **Input tidak berubah saat diketik**  
  ➜ Pastikan UI **collect** `uiState` dengan `collectAsStateWithLifecycle()` dan bukan passing value statis.

- **Compose Compiler mismatch**  
  ➜ Samakan: Kotlin **1.9.10** ↔︎ Compose Compiler **1.5.3** (lihat `composeOptions.kotlinCompilerExtensionVersion`).

- **KAPT / JDK 21 error**  
  ➜ Gunakan **Gradle JDK 17** (Android Studio settings), atau tambahkan `kapt.jvmargs` `--add-exports/...` (bila memakai KAPT).

- **Theme Material3 tidak ditemukan**  
  ➜ Tambahkan dependency `com.google.android.material:material:1.12.0` dan style `Theme.Tugas3PAMTerapan` yang mewarisi `Theme.Material3.DayNight.NoActionBar`.

---

