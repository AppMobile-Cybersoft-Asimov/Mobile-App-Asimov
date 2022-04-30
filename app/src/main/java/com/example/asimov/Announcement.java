package com.example.asimov;

public class Announcement {
    private Long id;
    private String title;
    private String description;

    public Announcement(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Announcement setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Announcement setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Announcement setDescription(String description) {
        this.description = description;
        return this;
    }
}
