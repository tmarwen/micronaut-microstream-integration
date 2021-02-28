package com.github.tmarwen.micronaut.microstream.application.model;

import java.time.Instant;

/**
 * Message
 *
 * @since 1.0.0
 */
public class Message {

    private Long id;

    private String content;

    private User author;

    private Thread thread;

    private Instant createdAt;

    public Message(Long id, String content, User author, Thread thread, Instant createdAt) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.thread = thread;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
