# SimpleResumeGenerator

A lightweight Spring Boot application that generates a Word (`.docx`) resume based on user input via a REST API and a responsive Tailwind-styled front-end.

## Features

- **Fill-in-the-blanks form** for easy data entry (personal details, education, skills, projects, hobbies)
- **.docx generation** using Apache POI
- **REST endpoint**: `POST /api/resume/docx` returns the generated resume as a downloadable file
- **Static front-end** served at `/index.html` with Tailwind CSS styling
- **Stateless**: no persistence or database required for the MVP

## Tech Stack

- **Language & Framework**: Java 24, Spring Boot 3.x
- **Document Generation**: Apache POI (`poi-ooxml`)
- **Front-End**: Static HTML + Tailwind CSS (via CDN)
- **Build Tool**: Maven with Wrapper (`mvnw`)
- **CI (optional)**: GitHub Actions for automated builds and tests

## Getting Started

### Prerequisites

- JDK 24 installed and `JAVA_HOME` configured
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
   "phone": "+65 0000 0000",
   "email": "shaheedabdillah@outlook.com",
   "city": "Singapore",
   "targetJob": "IT Support and Automation Specialist with 2.5 years of experience at Infosys, supporting backend operations for Mercedes-Benz. Proficient in Python scripting for automation, incident management via ServiceNow, and collaborating across international markets. Currently upskilling in AWS and DevOps tools with a focus on CI/CD and infrastructure automation.",
   "school": [
      {
         "name": "Kaplan Higher Education Academy",
         "degree": "Diploma in Information Technology",
         "graduation": "Apr 2020"
      },
      {
         "name": "Institute of Technical Education",
         "degree": "NITEC in Microelectronics",
         "graduation": "Dec 2018"
      }
   ],
   "skills": [
      "Python",
      "Java",
      "Bash (basic)",
      "JavaScript (basic)",
      "Flask",
      "Spring Boot",
      "OpenPyXL",
      "Selenium",
      "Tkinter",
      "PyQt",
      "AWS (EC2, S3, IAM – basic)",
      "Docker",
      "Git",
      "GitHub Actions",
      "Terraform (basic)",
      "Linux",
      "MySQL",
      "MariaDB",
      "SQL Server",
      "PostgreSQL (Basic)",
      "ServiceNow",
      "SolarWinds",
      "Server Monitoring"
   ],
   "certifications": [
      "AWS Cloud Technical Essentials, Amazon Web Services (via Coursera), 2025 — ID: 7O2C2EMIENL4",
      "Infosys Quality Certified Engineer, Nov 2023",
      "Java 11 Essentials, Infosys, Dec 2023",
      "Agility and Scrum, Infosys, Jan 2024",
      "CSCU (Certified Secure Computer User), EC-Council — ID: ECC6291084375",
      "ECSS (Certified Security Specialist), EC-Council — ID: ECC5214679830",
      "Top 3 – iFIT Innovation Project, STMicroelectronics, 2018",
      "National Service Completion Certificate, SAF, May 2022"
   ],
   "projects": [
      {
         "title": "Simple Resume Generator",
         "description": "Java, Spring Boot, Apache POI, Tailwind CSS, Maven\nDeveloped a Spring Boot microservice (POST /api/resume/docx) to generate .docx resumes from structured JSON input using Apache POI\nBuilt a responsive web interface with Tailwind CSS to collect user inputs including multiple schools, skills, and projects\nAdded dynamic form features (add/remove sections) and integrated frontend with backend to enable instant resume generation and download"
      },
      {
         "title": "Social Media Automation System",
         "description": "Python, Flask, Docker, Ubuntu, Telegram API, Social Media APIs\nDeveloped a Flask-based automation tool that posts Telegram channel content directly to Facebook and LinkedIn using respective APIs\nDeployed as a Docker container on Ubuntu for 24/7 headless operation\nImplemented automated media cleanup after posting to conserve disk space and optimize system performance"
      },
      {
         "title": "Gold Price Tracker Bot",
         "description": "Python, GoldAPI.io, Telegram Bot, Requests, Dotenv\nBuilt a Python bot to fetch real-time gold prices from GoldAPI.io and send hourly updates to Telegram users\nIntegrated .env for secure credential management and implemented error handling and logging for reliability"
      },
      {
         "title": "Mass Job Application Automation",
         "description": "Python, SMTP, Gmail/Outlook API, Pandas, Dotenv\nAutomated personalized job application emails in bulk using Gmail and Outlook SMTP with environment-secured credentials\nUtilized CSV parsing for recipient data and implemented status tracking via logging"
      },
      {
         "title": "Full-Stack Web Application",
         "description": "Java (Spring Boot), Angular, MySQL\nDesigned and developed a RESTful full-stack application with login, registration, and role-based access control features\nDemonstrated clean separation of backend logic and frontend UI with proper MVC design patterns"
      },
      {
         "title": "Secure Asset Management System",
         "description": "Java (Swing), Apache Derby, STM32 Nucleo, Socket Programming\nCreated a desktop-based RFID login system with an admin panel to track physical assets in real time\nIntegrated embedded hardware (STM32 Nucleo) with custom socket programming; awarded Top 3 in iFIT program with 1,000+ registered users"
      }
   ],
   "experience": [
      {
         "company": "Infosys (Mercedes-Benz x Daimler Project)",
         "title": "Associate",
         "duration": "Sep 2022 – Feb 2025",
         "description": "Developed Python scripts to automate disk utilization analysis, improving data clarity across 100K+ records\nBuilt a GUI-based reporting tool (Tkinter/PyQt) that reduced manual filtering and sped up exports by 70%\nSupported Linux-based server environments and managed hostname inventory for data center ops\nUsed ServiceNow for daily incident resolution and change management across APAC markets\nCollaborated with teams in Germany, India, and Singapore to enhance infrastructure performance"
      },
      {
         "company": "Singapore Armed Forces (SAF)",
         "title": "National Service",
         "duration": "May 2020 - May 2022",
         "description": "Managed logistics, inventory, and reporting, ensuring 100% equipment accountability\nCoordinated with units for seamless supply chain operations in high-pressure environments"
      },
      {
         "company": "STMicroelectronics",
         "title": "Trainee",
         "duration": "Jan 2018 - Dec 2018",
         "description": "Developed Java-based apps integrated with MySQL and Apache Derby\nSupported embedded system design and RFID-based login features\nContributed to secure asset management systems used by 1,000+ users"
      }
   ],
   "hobbies": [
      "Coding personal projects",
      "Learning DevOps tools"
   ]
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
