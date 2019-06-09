package me.idarkyy.common.io.networking.worker;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Worker implements Runnable {
    private static Logger logger = Logger.getLogger(Worker.class.getSimpleName());

    protected List<IoWorker> workers = new ArrayList<>();
    private int workerId = 0;

    public static Logger getLogger() {
        return logger;
    }

    public Selector openSelector() {
        Selector selector;

        do {
            try {
                selector = Selector.open();

                logger.info("Opened a new selector");
                return selector;
            } catch (IOException e) {
                logger.log(Level.WARNING, "Could not open a selector", e);
            }
        } while (true);
    }

    public SocketChannel openSocketChannel() {
        SocketChannel socketChannel;
        do {
            try {
                socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(false);

                logger.info("Opened a NIO socket channel");
                return socketChannel;
            } catch (IOException e) {
                logger.log(Level.WARNING, "Could not open a socket channel", e);
            }
        } while (true);
    }

    public ServerSocketChannel openServerSocketChannel() {
        ServerSocketChannel serverSocketChannel;
        do {
            try {
                serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.configureBlocking(false);

                logger.info("Opened a NIO server socket channel");
                return serverSocketChannel;
            } catch (IOException e) {
                logger.log(Level.WARNING, "Could not open a server socket channel", e);
            }
        } while (true);
    }

    public void addWorker(IoWorker worker) {
        workers.add(worker);
    }

    public IoWorker selectWorker() {
        IoWorker worker = workers.get(workerId);
        workerId = (workerId + 1) % workers.size();

        return worker;
    }

    @Override
    public abstract void run();
}
