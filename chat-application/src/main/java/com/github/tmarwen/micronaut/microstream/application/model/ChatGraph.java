package com.github.tmarwen.micronaut.microstream.application.model;

import com.github.tmarwen.micronaut.microstream.annotation.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * The Chat Graph model root entity.
 *
 * @since 1.0.0
 */
@Root
public class ChatGraph {

    private List<Thread> threads;

    private List<User> users;

    public ChatGraph() {
        this.threads = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
