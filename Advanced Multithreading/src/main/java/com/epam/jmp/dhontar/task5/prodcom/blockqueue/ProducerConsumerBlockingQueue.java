package com.epam.jmp.dhontar.task5.prodcom.blockqueue;

import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerBlockingQueue {

    private static final int BUFFER_SIZE = 10;
    private static final int PRODUCER_COUNT = 3;
    private static final int CONSUMER_COUNT = 2;

    public static void main(String[] args) {
        var buffer = new LinkedBlockingQueue<Integer>(BUFFER_SIZE);
        for (int i = 0; i < PRODUCER_COUNT; i++) {
            (new Thread(() -> {
                while (true){
                    var value = (int) (Math.random() * 100);
                    try {
                        buffer.put(value);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("<<<<Produced: " + value);
                }})).start();
        }
        for (int i = 0; i < CONSUMER_COUNT; i++) {
            (new Thread(() -> {
                while (true){
                    try {
                        System.out.println(">>>Consumed: " + buffer.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }})).start();
        }

    }
}
