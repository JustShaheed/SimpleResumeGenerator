# SimpleResumeGenerator

A lightweight Java Spring Boot app that generates a Word (`.docx`) resume based on user input. It uses a web form with Tailwind CSS and Apache POI for document generation.

---

## 💡 Features

- Fillable resume form (personal details, skills, education, projects, certifications)
- Exports `.docx` resume styled like a real CV
- Clean, responsive front-end (Tailwind)
- REST API: `POST /api/resume/docx`
- No database required

---

## 🚀 Tech Stack

- Java 17+
- Spring Boot 3.x
- Apache POI
- Tailwind CSS (CDN)
- Maven

---

## ⚙️ Server Configuration

Set the server port in `application.properties`:

```properties
server.port=8080
```

---

## 🛠 How to Run

```bash
# Clone the repo
git clone https://github.com/YOUR_USERNAME/SimpleResumeGenerator.git
cd SimpleResumeGenerator

# Build the app
./mvnw clean install

# Run the app
./mvnw spring-boot:run

# Access it in your browser
http://localhost:8080/index.html
```

---

## 📦 API Usage

### POST `/api/resume/docx`

- **Request Body**: JSON matching the `ResumeRequest` structure
- **Response**: `.docx` file for download

---

## 🤝 Contribute

Pull requests are welcome! Fork, branch, commit, and PR.

---

## ✍️ Author

**Shaheed Abdillah**

---

## 🪪 License

MIT