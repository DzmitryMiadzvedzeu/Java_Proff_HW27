package com.telran.org.homework_27;

public class Consumer extends Thread {

    private Storage storage;

    public Consumer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String good = storage.getGood();
            if (good != null) {
                System.out.println("Get good with name " + good + ".");
            } else {
                System.out.println("We are get all goods from storage. Work is done.");
                storage.cleanList();
                synchronized (storage) {
                    storage.notifyAll();
                    try {
                        storage.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}