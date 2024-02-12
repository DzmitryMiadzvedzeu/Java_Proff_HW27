package com.telran.org.homework_27;


import java.util.Scanner;

public class StorageApp {
    public static void main(String[] args) {

        Storage storage = new Storage();

        Producer producer = new Producer(storage);

        Scanner s = new Scanner(System.in);
        System.out.println("Enter number of consumers from 0 tо 10 : ");
        int numberOfConsumers = s.nextInt();
        s.close();
        if (numberOfConsumers == 0 | numberOfConsumers > 10) {
            System.out.println("Storage doesn't work today. See you next time");
            return;
        }
        for (int i = 0; i <= numberOfConsumers; i++) {
            new Consumer(storage).start();
        }

        new Thread(producer).start();
    }
}
