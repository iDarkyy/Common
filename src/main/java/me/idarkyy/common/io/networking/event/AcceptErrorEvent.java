package me.idarkyy.common.io.networking.event;

import me.idarkyy.common.event.Event;

import java.io.IOException;

public class AcceptErrorEvent extends Event {
    private IOException exception;

    public AcceptErrorEvent(IOException exception) {
        this.exception = exception;
    }

    public IOException getException() {
        return exception;
    }
}
