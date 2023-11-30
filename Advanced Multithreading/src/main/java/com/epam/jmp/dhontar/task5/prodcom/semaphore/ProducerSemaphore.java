package com.epam.jmp.dhontar.task5.prodcom.semaphore;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class ProducerSemaphore implements Runnable{

    private final Semaphore producerSemaphore;
    private final Semaphore consumerSemaphore;
    private final ConcurrentLinkedQueue<Integer> buffer;

    public ProducerSemaphore(Semaphore producerSemaphore, Semaphore consumerSemaphore,
                             ConcurrentLinkedQueue<Integer> buffer) {
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
                buffer.add(value);
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
