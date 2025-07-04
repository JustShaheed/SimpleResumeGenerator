package com.simpleresumegenerator.service;

import com.simpleresumegenerator.dto.ResumeRequest;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ResumeService {

    public byte[] generateDocx(ResumeRequest req) throws IOException {
        XWPFDocument doc = new XWPFDocument();

        // 1. Header
        XWPFParagraph header = doc.createParagraph();
        header.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = header.createRun();
        run.setBold(true);
        run.setFontSize(16);
        run.setText(req.name);
        run.addBreak();
        run.setFontSize(11);
        run.setBold(false);
        run.setText(req.phone + " | " + req.email + " | " + req.city);

        // 2. Objective
        addSectionTitle(doc, "OBJECTIVE");
        addIndentedText(doc, req.targetJob);

        // 3. Education
        if (req.school != null && !req.school.isEmpty()) {
            addSectionTitle(doc, "EDUCATION");
            for (ResumeRequest.School s : req.school) {
                addIndentedBold(doc, s.name);
                addIndentedText(doc, s.degree + " — " + s.graduation, 600);
            }
        }

        // 4. Skills
        if (req.skills != null && !req.skills.isEmpty()) {
            addSectionTitle(doc, "SKILLS");
            for (String skill : req.skills) {
                addIndentedText(doc, "• " + skill);
            }
        }

        // 5. Projects
        if (req.projects != null && !req.projects.isEmpty()) {
            addSectionTitle(doc, "PROJECTS");
            for (ResumeRequest.Project p : req.projects) {
                addIndentedBold(doc, p.title);
                addIndentedText(doc, p.description, 600);
            }
        }

        // 6. Certifications
        if (req.certifications != null && !req.certifications.isEmpty()) {
            addSectionTitle(doc, "CERTIFICATIONS");
            for (String cert : req.certifications) {
                addIndentedText(doc, "• " + cert);
            }
        }

        // 7. Hobbies
        if (req.hobbies != null && !req.hobbies.isEmpty()) {
            addSectionTitle(doc, "HOBBIES");
            for (String hobby : req.hobbies) {
                addIndentedText(doc, hobby);
            }
        }

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
        p.setSpacingAfter(100);
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
