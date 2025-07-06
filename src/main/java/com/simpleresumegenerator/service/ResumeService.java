package com.simpleresumegenerator.service;

import java.math.BigInteger;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;
import com.simpleresumegenerator.dto.ResumeRequest;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ResumeService {

    public byte[] generateDocx(ResumeRequest req) throws IOException {
        XWPFDocument doc = new XWPFDocument();

        // Header
        XWPFParagraph header = doc.createParagraph();
        header.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = header.createRun();
        run.setBold(true);
        run.setFontSize(11);
        run.setText(req.name);
        run.addBreak();
        run.setFontSize(11);
        run.setBold(false);
        run.setText(req.phone + " | " + req.email + " | " + req.city);

        // Summary
        addSectionTitle(doc, "SUMMARY");
        addMultilineParagraph(doc, req.targetJob);

        // Education
        if (req.school != null && !req.school.isEmpty()) {
            addSectionTitle(doc, "EDUCATION");
            for (ResumeRequest.School s : req.school) {
                addIndentedBold(doc, s.name);
                addIndentedText(doc, s.degree + " — " + s.graduation, 600);
            }
        }

        // Skills
        if (req.skills != null && !req.skills.isEmpty()) {
            addSectionTitle(doc, "SKILLS");
            addBulletedList(doc, req.skills);
        }

        // Work Experience
        if (req.experience != null && !req.experience.isEmpty()) {
            addSectionTitle(doc, "WORK EXPERIENCE");
            for (ResumeRequest.Experience exp : req.experience) {
                addIndentedBold(doc, exp.company + " — " + exp.title);
                addIndentedItalic(doc, exp.duration, 600);
                addMultilineParagraph(doc, exp.description, 600);
            }
        }


        // Projects
        if (req.projects != null && !req.projects.isEmpty()) {
            addSectionTitle(doc, "PROJECTS");
            for (ResumeRequest.Project p : req.projects) {
                addIndentedBold(doc, p.title);
                addMultilineParagraph(doc, p.description, 600);
            }
        }

        // Certifications
        if (req.certifications != null && !req.certifications.isEmpty()) {
            addSectionTitle(doc, "CERTIFICATIONS");
            addBulletedList(doc, req.certifications);
        }

        // Hobbies
        if (req.hobbies != null && !req.hobbies.isEmpty()) {
            addSectionTitle(doc, "HOBBIES");
            addBulletedList(doc, req.hobbies);
        }

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            doc.write(baos);
            return baos.toByteArray();
        }
    }

    // --- Helper Methods ---

    private void addSectionTitle(XWPFDocument doc, String title) {
        XWPFParagraph titleP = doc.createParagraph();
        titleP.setSpacingBefore(200);
        XWPFRun t = titleP.createRun();
        t.setBold(true);
        t.setFontSize(12);
        t.setText(title);
    }

    private void addIndentedText(XWPFDocument doc, String text, int indent) {
        XWPFParagraph p = doc.createParagraph();
        p.setIndentationLeft(indent);
        p.setSpacingAfter(100);
        XWPFRun run = p.createRun();
        run.setText(text);
    }

    private void addIndentedBold(XWPFDocument doc, String text) {
        XWPFParagraph p = doc.createParagraph();
        p.setIndentationLeft(300);
        p.setSpacingAfter(50);
        XWPFRun r = p.createRun();
        r.setBold(true);
        r.setText(text);
    }

    private void addIndentedItalic(XWPFDocument doc, String text, int indent) {
        XWPFParagraph p = doc.createParagraph();
        p.setIndentationLeft(indent);
        XWPFRun r = p.createRun();
        r.setItalic(true);
        r.setText(text);
    }

    private void addBulletedList(XWPFDocument doc, java.util.List<String> items) {
        for (String item : items) {
            XWPFParagraph p = doc.createParagraph();
            p.setIndentationLeft(400);
            p.setNumID(addNumbering(doc));  // apply bullet style
            XWPFRun run = p.createRun();
            run.setText(item);
        }
    }

    private void addMultilineParagraph(XWPFDocument doc, String text) {
        addMultilineParagraph(doc, text, 300);
    }

    private void addMultilineParagraph(XWPFDocument doc, String text, int indent) {
        String[] lines = text.split("\n");
        for (String line : lines) {
            addIndentedText(doc, line.trim(), indent);
        }
    }

    private BigInteger addNumbering(XWPFDocument doc) {
        XWPFNumbering numbering = doc.createNumbering();
        CTAbstractNum abstractNum = CTAbstractNum.Factory.newInstance();
        abstractNum.setAbstractNumId(BigInteger.valueOf(0));
        CTLvl level = abstractNum.addNewLvl();
        level.setIlvl(BigInteger.ZERO);
        level.addNewNumFmt().setVal(STNumberFormat.BULLET);
        level.addNewLvlText().setVal("•");
        level.addNewStart().setVal(BigInteger.ONE);
        XWPFAbstractNum xwpfAbstractNum = new XWPFAbstractNum(abstractNum);
        BigInteger abstractNumID = numbering.addAbstractNum(xwpfAbstractNum);
        return numbering.addNum(abstractNumID);
    }
}
