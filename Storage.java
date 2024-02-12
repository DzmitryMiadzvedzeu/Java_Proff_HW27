package com.telran.org.homework_27;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage {

    private List<String> cells;

    private int currentIndex;
    private AtomicInteger index;

    public Storage() {
        cells = new ArrayList<>();
        currentIndex = 0;
        index = new AtomicInteger(0);
    }

    public boolean putGood(String good) {
        while (cells.size() < 10) {
            cells.add(currentIndex, good);
            currentIndex++;
            return true;
        }
        System.out.println("Storage is full!");
        currentIndex = 0;
        return false;
    }

    public String getGood() {
        if (index.get() == 10) {
            System.out.println("Storage is empty!");
            index.set(0);
            return null;
        }
        synchronized (this) {
            String good = cells.get(index.get());
            cells.set(index.get(), null);
            index.getAndIncrement();
            return good;
        }
    }

    public void cleanList() {
        cells.clear();
    }
}
