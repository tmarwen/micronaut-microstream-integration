package com.github.tmarwen.micronaut.microstream.application;

import com.github.tmarwen.micronaut.microstream.application.model.ChatGraph;
import com.github.tmarwen.micronaut.microstream.application.model.Message;
import com.github.tmarwen.micronaut.microstream.application.model.Thread;
import com.github.tmarwen.micronaut.microstream.application.model.User;
import graphql.GraphQL;
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;
import one.microstream.storage.types.EmbeddedStorageManager;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Application {

    /**
     * Identifier holder for object identities generation (dummy usage).
     */
    private static long id = 0;

    public static void main(String[] args) {
        ApplicationContext context = Micronaut.run(Application.class, args);
        // Retrieve storageManager bean
        EmbeddedStorageManager storageManager = context.getBean(EmbeddedStorageManager.class);
        // Retrieve root entity
        ChatGraph root = (ChatGraph) storageManager.root();
        // Create a conversation thread
        Thread conversation = thread();
        // Create the first Micronaut-MicroStream user ever
        User user = user();
        conversation.getMembers().add(user);
        // Here goes the first conversation message
        Message message = message("MicroStream is cool!", user, conversation);
        thread().getMessages().add(message);
        // And the second message
        Message anotherMessage = message("MicroStream is cool!", user, conversation);
        thread().getMessages().add(anotherMessage);
        // Add the user to the store
        root.getUsers().add(user);
        // Save everything
        storageManager.storeAll(root);
        System.out.println(storageManager.root());
    }

    public static User user() {
        return new User(id++, "tmarouane", "marouane.trab@gmail.com", Instant.now());
    }

    public static Thread thread() {
        return new Thread(id++, "hackathon.microstream.on", Instant.now(), new ArrayList<>(), new ArrayList<>());
    }

    public static Message message(final String content, final User author, final Thread conversation) {
        return new Message(id++, content, author, conversation, Instant.now());
    }
}
