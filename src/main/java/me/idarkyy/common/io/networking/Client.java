package me.idarkyy.common.io.networking;

import me.idarkyy.common.event.ListenerManager;
import me.idarkyy.common.event.listener.Listener;
import me.idarkyy.common.io.networking.worker.Connector;
import me.idarkyy.common.io.networking.worker.Worker;

import java.util.logging.Logger;

public class Client {
    private static final Logger logger = Logger.getLogger(Worker.class.getSimpleName());
    private final ListenerManager listenerManager = new ListenerManager();

    private int threadPoolSize;
    private Connector connector;

    public Client(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
        this.connector = new Connector();
    }

    public Client() {
        this(1);
    }

    public void connect(String host, int port) {
        connector.connect(host, port);
    }

    public void registerListeners(Listener... listeners) {
        listenerManager.register(listeners);
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public ListenerManager getListenerManager() {
        return listenerManager;
    }
}
