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
        run.setFontSize(18);
        run.setText(req.name);
        run.addBreak();
        run.setBold(false);
        run.setFontSize(11);
        run.setText(req.phone + " | " + req.email + " | " + req.city);

        // 2. Objective
        XWPFParagraph obj = doc.createParagraph();
        obj.setSpacingBefore(200);
        XWPFRun objRun = obj.createRun();
        objRun.setItalic(true);
        objRun.setText("Objective: " + req.targetJob);

        // 3. Education
        addSectionTitle(doc, "EDUCATION");
        if (req.school != null) {
            for (ResumeRequest.School s : req.school) {
                addIndentedText(doc, String.format("%s, %s (%s)", s.name, s.degree, s.graduation));
            }
        }

        // 4. Skills
        addSectionTitle(doc, "SKILLS");
        if (req.skills != null && !req.skills.isEmpty()) {
            for (String skill : req.skills) {
                addIndentedText(doc, "• " + skill);
            }
        }

        // 5. Projects
        addSectionTitle(doc, "PROJECTS");
        if (req.projects != null && !req.projects.isEmpty()) {
            for (ResumeRequest.Project p : req.projects) {
                addIndentedBold(doc, p.title);
                addIndentedText(doc, p.description, 600);
            }
        }

        // 6. Optional horizontal line
        XWPFParagraph hr = doc.createParagraph();
        hr.setBorderBottom(Borders.SINGLE);
        hr.setSpacingBefore(200);
        hr.setSpacingAfter(200);

        // 7. Hobbies
        if (req.hobbies != null && !req.hobbies.isEmpty()) {
            addSectionTitle(doc, "HOBBIES & CLUBS");
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
