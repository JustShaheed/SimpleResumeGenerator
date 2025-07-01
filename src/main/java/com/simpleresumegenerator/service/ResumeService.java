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
        XWPFParagraph eduTitle = doc.createParagraph();
        eduTitle.setSpacingBefore(200);
        XWPFRun et = eduTitle.createRun();
        et.setBold(true);
        et.setText("Education");

        XWPFParagraph edu = doc.createParagraph();
        edu.setIndentationLeft(300);
        XWPFRun eduRun = edu.createRun();
        eduRun.setText(String.format("%s, %s (%s)",
                req.school.name,
                req.school.degree,
                req.school.graduation
        ));

        // 4. Skills section
        writeSection(doc, "Skills", mergeLists(
                req.computerSkills,
                req.languages,
                req.otherSkills
        ));

        // 5. Projects section
        XWPFParagraph projTitle = doc.createParagraph();
        projTitle.setSpacingBefore(200);
        XWPFRun pt = projTitle.createRun();
        pt.setBold(true);
        pt.setText("Projects");

        if (req.projects != null) {
            for (ResumeRequest.Project p : req.projects) {
                XWPFParagraph pHead = doc.createParagraph();
                pHead.setIndentationLeft(300);
                XWPFRun ph = pHead.createRun();
                ph.setBold(true);
                ph.setText(p.title);

                XWPFParagraph pDesc = doc.createParagraph();
                pDesc.setIndentationLeft(600);
                XWPFRun pd = pDesc.createRun();
                pd.setText(p.description);
            }
        }

        // 6. Hobbies & Clubs
        if (req.hobbies != null && !req.hobbies.isEmpty()) {
            writeSection(doc, "Hobbies & Clubs", req.hobbies);
        }

        // 7. Export
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            doc.write(baos);
            return baos.toByteArray();
        }
    }

    private void writeSection(XWPFDocument doc, String title, List<String> items) {
        XWPFParagraph titleP = doc.createParagraph();
        titleP.setSpacingBefore(200);
        XWPFRun t = titleP.createRun();
        t.setBold(true);
        t.setText(title);

        if (items != null) {
            for (String item : items) {
                XWPFParagraph p = doc.createParagraph();
                p.setStyle("ListBullet");
                p.setIndentationLeft(300);
                XWPFRun r = p.createRun();
                r.setText(item);
            }
        }
    }

    @SafeVarargs
    private final List<String> mergeLists(List<String>... lists) {
        return java.util.stream.Stream.of(lists)
                .filter(l -> l != null)
                .flatMap(List::stream)
                .toList();
    }
}
