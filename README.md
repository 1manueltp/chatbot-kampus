# 🤖 Aplikasi Chatbot Kampus Pintar

Proyek ini adalah aplikasi **Chatbot Desktop** berbasis Java yang dirancang untuk membantu mahasiswa dalam mencari informasi kampus seperti jadwal kuliah, lokasi ruangan, informasi akademik, dan lainnya. Aplikasi ini dikembangkan menggunakan **Apache NetBeans**, dengan pendekatan **GUI interaktif** dan strategi chatbot berbasis **rule-based**.

---

## 🛠 Teknologi yang Digunakan

- **Java SE** (NetBeans IDE)
- **Swing** untuk tampilan GUI
- **Rule-Based Engine** (custom logic)
- **OOP (Object-Oriented Programming)**
- **Maven Project Structure**

---

## ✨ Fitur Utama

✅ Tampilan antarmuka yang sederhana dan interaktif  
✅ Menjawab pertanyaan seputar kampus (ruangan, jadwal, dosen, dll.)  
✅ Sistem riwayat pertanyaan dengan sidebar berbentuk TreeView (per tanggal)  
✅ Dukungan mode chatbot **offline (rule-based)**.
✅ Arsitektur modular dan mudah dikembangkan

---

## 🧠 Contoh Interaksi Chatbot
👤 Anda: Bagaimana cara isi KRS?
🤖 Chatbot: Login ke SIADIN, pilih menu KRS, lalu pilih mata kuliah yang tersedia.

👤 Anda: Apa itu SIA?
🤖 Chatbot: Sistem Informasi Akademik, untuk mengelola data kuliah mahasiswa.

## 🚀 Cara Menjalankan

1. Buka folder proyek ini di Apache NetBeans.
2. Jalankan file utama (`Main.java`).
3. Jalankan dan mulai tanya jawab dengan chatbot kampus!

---

## 📦 Struktur Folder

```bash
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── chatbot/
│   │   │   │   ├── controller/  
│   │   │   │   │   └── ChatController.java
│   │   │   │   ├── model/
│   │   │   │   │   ├── ChatMessage.java
│   │   │   │   │   ├── KnowledgeBase.java
│   │   │   │   │   └── QuestionProcess.java
│   │   │   │   ├── strategy/
│   │   │   │   │   ├── AnsweringStrategy.java
│   │   │   │   │   └── RuleBasedStrategy.java
│   │   │   │   ├──view/
│   │   │   │   │   ├── FAQEditorDialog.java
│   │   │   │   │   └── MainView.java
│   │   │   │   └── ChatbotApp.java
└── resources/
    └── faq.json
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
