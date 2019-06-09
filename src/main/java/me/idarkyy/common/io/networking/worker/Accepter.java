package me.idarkyy.common.io.networking.worker;

import me.idarkyy.common.io.networking.Server;
import me.idarkyy.common.io.networking.event.AcceptErrorEvent;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Accepter extends Worker {
    private final Server server;
    private ServerSocketChannel channel;
    private Selector selector;

    public Accepter(Server server) {
        this.server = server;

        this.selector = openSelector();
        this.channel = openServerSocketChannel();
    }

    @Override
    public void run() {
        accept();
    }

    public void accept() {
        registerChannel();

        while (true) {
            try {
                this.selector.select();
                Set<SelectionKey> keys = this.selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    // handling

                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();

                        try {
                            SocketChannel channel = server.accept();

                            IoWorker worker = selectWorker();

                            worker.dispatch(); // TODO: 27/05/2019
                        } catch (IOException e) {
                            AcceptErrorEvent event = new AcceptErrorEvent(e);
                            this.server.getListenerManager().call(event);

                            registerChannel();
                        }
                    }

                    //

                    iterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
                registerChannel();
            }
        }
    }

    private void bind() {
        do {
            try {
                channel.socket().bind(new InetSocketAddress(server.getHost(), server.getPort()));
                break;
            } catch (IOException e) {
                System.out.println("An error occurred while binding to " + server.getHost() + ":" + server.getPort());
                e.printStackTrace();
            }
        } while (true);
    }

    private void registerChannel() {
        do {
            try {
                this.channel.register(this.selector, SelectionKey.OP_ACCEPT);
                break;
            } catch (ClosedChannelException e) {
                System.out.println("An error occurred while registering a socket channel");
                e.printStackTrace();

                this.channel = openServerSocketChannel();
                bind();
            }
        } while (true);
    }

    public void addIoWorker(IoWorker worker) {
        workers.add(worker);
    }

    public List<IoWorker> getWorkers() {
        return workers;
    }

    public Server getServer() {
        return server;
    }
}
