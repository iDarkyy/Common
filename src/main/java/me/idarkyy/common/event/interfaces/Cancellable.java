package me.idarkyy.common.event.interfaces;

public interface Cancellable {
    void setCancelled(boolean cancel);

    boolean isCancelled();
}
