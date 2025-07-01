package com.simpleresumegenerator.controller;

import com.simpleresumegenerator.dto.ResumeRequest;
import com.simpleresumegenerator.service.ResumeService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    private final ResumeService service;

    public ResumeController(ResumeService service) {
        this.service = service;
    }

    @PostMapping("/docx")
    public ResponseEntity<ByteArrayResource> downloadDocx(
            @Valid @RequestBody ResumeRequest req
    ) throws Exception {
        byte[] data = service.generateDocx(req);
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=resume.docx")
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                .contentLength(data.length)
                .body(resource);
    }
}
