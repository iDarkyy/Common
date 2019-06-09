package me.idarkyy.common.io.networking.worker;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Connector extends Worker {
    private Selector selector;

    public Connector() {
        this.selector = openSelector();
    }

    public void connect(String host, int port) {
        InetSocketAddress address = new InetSocketAddress(host, port);

        try {
            SocketChannel channel = openSocketChannel();

            channel.connect(address);
            this.selector.wakeup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }
}
