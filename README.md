# SimpleResumeGenerator

A lightweight Spring Boot application that generates a Word (`.docx`) resume based on user input via a REST API and a responsive Tailwind-styled front-end.

## Features

- **Fill-in-the-blanks form** for easy data entry (personal details, education, skills, projects, hobbies)
- **.docx generation** using Apache POI
- **REST endpoint**: `POST /api/resume/docx` returns the generated resume as a downloadable file
- **Static front-end** served at `/index.html` with Tailwind CSS styling
- **Stateless**: no persistence or database required for the MVP

## Tech Stack

- **Language & Framework**: Java 17, Spring Boot 3.x
- **Document Generation**: Apache POI (`poi-ooxml`)
- **Front-End**: Static HTML + Tailwind CSS (via CDN)
- **Build Tool**: Maven with Wrapper (`mvnw`)
- **CI (optional)**: GitHub Actions for automated builds and tests

## Getting Started

### Prerequisites

- JDK 17 installed and `JAVA_HOME` configured
- Internet access for loading Tailwind CSS CDN

### Build & Run

1. **Clone the repo**
   ```bash
   git clone https://github.com/YOUR_USERNAME/SimpleResumeGenerator.git
   cd SimpleResumeGenerator
   ```
2. **Build the project** (uses Maven Wrapper):
   ```bash
   ./mvnw clean install
   ```
3. **Run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```
4. **Access the app** in your browser:
   ```
   http://localhost:8080/index.html
   ```
5. **Push to GitHub**
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git remote add origin git@github.com:YOUR_USERNAME/SimpleResumeGenerator.git
   git branch -M main
   git push -u origin main
   ```

## API Usage

### Generate Resume

- **Endpoint**: `POST /api/resume/docx`
- **Headers**: `Content-Type: application/json`
- **Body**: JSON payload matching the `ResumeRequest` model, for example:
```json
  {
    "name": "Shaheed Abdillah",
    "phone": "123-456-7890",
    "email": "shaheed@example.com",
    "city": "Singapore",
    "targetJob": "Software Developer",
    "school": {
      "name": "Kaplan",
      "degree": "Diploma in IT",
      "graduation": "May 2025"
    },
    "computerSkills": ["Word", "Excel"],
    "languages": ["English", "Tamil"],
    "otherSkills": ["Customer service", "Teamwork"],
    "projects": [
      {
        "title": "Simple Resume Generator",
        "description": "Made a web application which exports a generated resume based on user input"
      }
    ],
    "hobbies": ["Boxing", "Volunteering"]
  }
```
- **Response**: A `.docx` file (`Content-Disposition: attachment; filename=resume.docx`)

## Front-End

- **File**: `src/main/resources/static/index.html`
- **Features**: Responsive form, builds JSON from user inputs, sends to backend, and triggers `.docx` download

## Contributing

Contributions are welcome! Please:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m "Add YourFeature"`)
4. Push to your branch (`git push origin feature/YourFeature`)
5. Open a Pull Request

## License

This project is released under the **MIT License**.
