package com.simpleresumegenerator.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class ResumeRequest {

    @NotBlank
    public String name;

    @NotBlank
    public String phone;

    @Email
    @NotBlank
    public String email;

    @NotBlank
    public String city;

    @NotBlank
    public String targetJob;

    @NotNull
    @Valid
    public List<School> school;

    @Valid
    public List<@NotBlank String> skills;

    @Valid
    public List<Project> projects;

    @Valid
    public List<@NotBlank String> certifications;

    public List<String> hobbies;

    public static class School {
        @NotBlank public String name;
        @NotBlank public String degree;
        @NotBlank public String graduation; // e.g., "May 2025"
    }

    public static class Project {
        @NotBlank public String title;
        @NotBlank public String description;
    }
}
