package com.simpleresumegenerator.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class ResumeRequest {
    @NotBlank
    public String name;

    @NotBlank
    public String phone;

    @Email @NotBlank
    public String email;

    @NotBlank
    public String city;

    @NotBlank
    public String targetJob;

    @NotNull
    public School school;

    // Skills
    public List<@NotBlank String> computerSkills;
    public List<@NotBlank String> languages;
    public List<@NotBlank String> otherSkills;

    // Projects
    public List<Project> projects;

    // Hobbies/clubs
    public List<String> hobbies;

    public static class School {
        @NotBlank public String name;
        @NotBlank public String degree;
        @NotBlank public String graduation; // e.g. "May 2025"
    }

    public static class Project {
        @NotBlank public String title;
        @NotBlank public String description;
    }
}
