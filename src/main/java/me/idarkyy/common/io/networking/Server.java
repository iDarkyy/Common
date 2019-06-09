package me.idarkyy.common.io.networking;

import me.idarkyy.common.event.ListenerManager;
import me.idarkyy.common.event.listener.Listener;
import me.idarkyy.common.io.networking.worker.Accepter;
import me.idarkyy.common.io.networking.worker.IoWorker;
import me.idarkyy.common.io.networking.worker.Worker;

import java.util.logging.Logger;

public class Server {
    private static Logger logger = Logger.getLogger(Worker.class.getSimpleName());

    private ListenerManager listenerManager = new ListenerManager();

    private String host;
    private int port;
    private int threadPoolSize;

    public Server(String host, int port, int threadPoolSize) {
        this.host = host;
        this.port = port;
        this.threadPoolSize = threadPoolSize;
    }

    public Server(String host, int port) {
        this(host, port, 10);
    }

    public void start() {
        Accepter accepter = new Accepter(this);

        for (int i = 0; i < threadPoolSize; i++) {
            IoWorker worker = new IoWorker(i);
            accepter.addIoWorker(worker);

            new Thread(worker).start();
        }

        new Thread(accepter).start();
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public void registerListeners(Listener... listeners) {
        listenerManager.register(listeners);
    }

    public ListenerManager getListenerManager() {
        return listenerManager;
    }
}
