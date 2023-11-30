package com.epam.jmp.dhontar.task5.prodcom.semaphore;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class SemaphoreProducer implements Runnable{

    private final Semaphore producerSemaphore;
    private final Semaphore consumerSemaphore;
    private final BlockingQueue<Integer> buffer;

    public SemaphoreProducer(Semaphore producerSemaphore, Semaphore consumerSemaphore, BlockingQueue<Integer> buffer) {
        this.producerSemaphore = producerSemaphore;
        this.consumerSemaphore = consumerSemaphore;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                producerSemaphore.acquire();
                int value = produce();
                buffer.put(value);
                System.out.println("<<<<Produced: " + value);
                consumerSemaphore.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private int produce() {
        return (int) (Math.random() * 100);
    }
}
