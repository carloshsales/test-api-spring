package com.api.authentication.domain.entities;

import com.api.authentication.domain.entities.user.User;
import com.api.authentication.domain.enums.Category;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private String content;
    @ManyToOne
    private User user;
    @Enumerated(value = EnumType.STRING)
    private Category category;

    public Post(){}

    public Post(String title, String description, String content, Category category) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
