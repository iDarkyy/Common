package me.idarkyy.common.io.networking.worker;

import java.nio.channels.Selector;

public class IoWorker extends Worker {
    private int id;
    private Selector selector;

    public IoWorker(int id) {
        this.id = id;
        this.selector = selector;
    }

    public void dispatch() {
        // TODO: 27/05/2019  
    }

    @Override
    public void run() {

    }

    public int getId() {
        return id;
    }

    public Selector getSelector() {
        return selector;
    }
}
