package com.simpleresumegenerator.service;

import com.simpleresumegenerator.dto.ResumeRequest;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ResumeService {

    public byte[] generateDocx(ResumeRequest req) throws IOException {
        XWPFDocument doc = new XWPFDocument();

        // 1. Header
        XWPFParagraph header = doc.createParagraph();
        header.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = header.createRun();
        run.setBold(true);
        run.setFontSize(18);
        run.setText(req.name);
        run.addBreak();
        run.setBold(false);
        run.setFontSize(11);
        run.setText(req.phone + " | " + req.email + " | " + req.city);

        // 2. Objective / Target Job
        XWPFParagraph obj = doc.createParagraph();
        obj.setSpacingBefore(200);
        XWPFRun objRun = obj.createRun();
        objRun.setItalic(true);
        objRun.setText("Objective: " + req.targetJob);

        // 3. Education section
        addSectionTitle(doc, "EDUCATION");
        addIndentedText(doc, String.format("%s, %s (%s)",
                req.school.name,
                req.school.degree,
                req.school.graduation
        ));

        // 4. Skills categories
        addSectionTitle(doc, "SKILLS");
        addIndentedText(doc, "Languages: " + String.join(", ", req.languages));
        addIndentedText(doc, "Frameworks & Tools: Flask, Spring Boot, OpenPyXL, Selenium, Tkinter, PyQt");
        addIndentedText(doc, "Cloud & DevOps: AWS (EC2, S3, IAM – basic), Docker, Git, GitHub Actions, Terraform (basic), Linux");
        addIndentedText(doc, "Databases: MySQL, MariaDB, SQL Server, PostgreSQL (Basic)");
        addIndentedText(doc, "Monitoring & IT Support: ServiceNow, SolarWinds, Server Monitoring");

        // 5. Projects section
        addSectionTitle(doc, "PROJECTS");
        if (req.projects != null) {
            for (ResumeRequest.Project p : req.projects) {
                addIndentedBold(doc, p.title);
                addIndentedText(doc, p.description, 600);
            }
        }

        // Optional horizontal line before Hobbies
        XWPFParagraph hr = doc.createParagraph();
        hr.setBorderBottom(Borders.SINGLE);
        hr.setSpacingBefore(200);
        hr.setSpacingAfter(200);

        // 6. Hobbies & Clubs
        if (req.hobbies != null && !req.hobbies.isEmpty()) {
            addSectionTitle(doc, "HOBBIES & CLUBS");
            for (String hobby : req.hobbies) {
                addIndentedText(doc, hobby);
            }
        }

        // 7. Export
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            doc.write(baos);
            return baos.toByteArray();
        }
    }

    // Helper methods
    private void addSectionTitle(XWPFDocument doc, String title) {
        XWPFParagraph titleP = doc.createParagraph();
        titleP.setSpacingBefore(200);
        XWPFRun t = titleP.createRun();
        t.setBold(true);
        t.setText(title);
    }

    private void addIndentedText(XWPFDocument doc, String text) {
        addIndentedText(doc, text, 300);
    }

    private void addIndentedText(XWPFDocument doc, String text, int indent) {
        XWPFParagraph p = doc.createParagraph();
        p.setIndentationLeft(indent);
        p.createRun().setText(text);
    }

    private void addIndentedBold(XWPFDocument doc, String text) {
        XWPFParagraph p = doc.createParagraph();
        p.setIndentationLeft(300);
        XWPFRun r = p.createRun();
        r.setBold(true);
        r.setText(text);
    }
}
