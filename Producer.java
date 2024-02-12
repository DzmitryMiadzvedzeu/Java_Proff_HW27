package com.telran.org.homework_27;

public class Producer extends Thread {

    private Storage storage;

    public Producer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int count = 0;
        synchronized (storage) {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String good = "A" + ++count;
                boolean result = storage.putGood(good);
                if (!result) {
                    count = 0;
                    storage.notifyAll();
                    try {
                        storage.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Put good " + good + " into storage.");
                }
            }
        }
    }
}