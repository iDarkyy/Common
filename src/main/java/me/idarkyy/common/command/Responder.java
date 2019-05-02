package me.idarkyy.common.command;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Responder {
    private Consumer<String> consumer;

    public Responder(Consumer<String> consumer) {
        this.consumer = consumer;
    }

    public void send(String message) {
        consumer.accept(message);
    }

    public void send(String... messages) {
        Arrays.stream(messages).forEach(consumer);
    }

    public void send(List<String> messages) {
        messages.forEach(consumer);
    }
}
