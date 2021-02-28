package com.github.tmarwen.micronaut.microstream.application.model;

import java.time.Instant;
import java.util.List;

/**
 * Thread
 *
 * @since 1.0.0
 */
public class Thread {

    private Long id;

    private String name;

    private Instant createdAt;

    private List<User> members;

    private List<Message> messages;

    public Thread(Long id, String name, Instant createdAt, List<User> members, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.members = members;
        this.messages = messages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
