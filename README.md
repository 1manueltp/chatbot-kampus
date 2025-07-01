# 🤖 Aplikasi Chatbot Kampus Pintar

Proyek ini adalah aplikasi **Chatbot Desktop** berbasis Java yang dirancang untuk membantu mahasiswa dalam mencari informasi kampus seperti jadwal kuliah, lokasi ruangan, informasi akademik, dan lainnya. Aplikasi ini dikembangkan menggunakan **Apache NetBeans**, dengan pendekatan **GUI interaktif** dan strategi chatbot berbasis **rule-based** dan **API GPT**.

---

## 🛠 Teknologi yang Digunakan

- **Java SE** (NetBeans IDE)
- **Swing** untuk tampilan GUI
- **Rule-Based Engine** (custom logic)
- **OpenAI GPT API** (opsional)
- **OOP (Object-Oriented Programming)**
- **Maven Project Structure**

---

## ✨ Fitur Utama

✅ Tampilan antarmuka yang sederhana dan interaktif  
✅ Menjawab pertanyaan seputar kampus (ruangan, jadwal, dosen, dll.)  
✅ Sistem riwayat pertanyaan dengan sidebar berbentuk TreeView (per tanggal)  
✅ Dukungan mode chatbot **offline (rule-based)** dan **online (GPT API)**  
✅ Arsitektur modular dan mudah dikembangkan

---

## 🧠 Contoh Interaksi Chatbot
👤 Anda: Dimana ruang kuliah A301?
🤖 Chatbot: Ruang A301 berada di Gedung A lantai 3, sebelah timur lift.

👤 Anda: Siapa dosen pengampu PBO?
🤖 Chatbot: Dosen pengampu mata kuliah PBO adalah Bapak Dr. Dimas Aji, S.Kom., M.Kom.

## 🚀 Cara Menjalankan

1. Buka folder proyek ini di Apache NetBeans.
2. Jalankan file utama (`Main.java` atau `ChatbotGUI.java`).
3. (Opsional) Tambahkan API Key OpenAI di file konfigurasi jika menggunakan mode GPT.
4. Jalankan dan mulai tanya jawab dengan chatbot kampus!

---

## 📦 Struktur Folder

```bash
├── src/
│   ├── chatbot/
│   │   ├── ChatbotGUI.java
│   │   ├── RuleEngine.java
│   │   └── GPTApiHandler.java
├── nbproject/
├── .gitignore
└── README.md
```

---

📚 Lisensi
Proyek ini bersifat open-source dan dapat dikembangkan lebih lanjut untuk kebutuhan akademik atau pribadi.

---

🙋‍♂️ Tentang Pengembang
👤 Nama: Imanuel Tegar Prakosa & Aditya Reyhan Aji Pratama (Mahasiswa Jurusan Teknik Informatika)
🎓 Fokus: Java Desktop, AI, dan Software Development
📌 Tujuan Proyek: Sebagai media pembelajaran dan portofolio pengembangan aplikasi Java

---

⭐ Silakan beri bintang (⭐) pada repo ini jika kamu suka, dan fork jika ingin berkontribusi!

---

## 🔧 Cara Menambahkan ke Proyek Kamu

1. Buka Git Bash di folder proyek.
2. Jalankan:
```bash
touch README.md
nano README.md
```
3. Tempel isi dari markdown di atas.
4. Simpan dan keluar (Ctrl + O, lalu Enter, lalu Ctrl + X).
4. Commit dan push:
```bash
git add README.md
git commit -m "Menambahkan README.md untuk dokumentasi proyek"
git push origin main
