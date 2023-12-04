package com.epam.jmp.dhontar.task5.prodcom.semaphore;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class ConsumerSemaphore implements Runnable {

    private final Semaphore producerSemaphore;
    private final Semaphore consumerSemaphore;
    private final ConcurrentLinkedQueue<Integer> buffer;

    public ConsumerSemaphore(Semaphore producerSemaphore, Semaphore consumerSemaphore,
                             ConcurrentLinkedQueue<Integer> buffer) {
        this.producerSemaphore = producerSemaphore;
        this.consumerSemaphore = consumerSemaphore;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consumerSemaphore.acquire();
                consume();
                producerSemaphore.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private void consume() throws InterruptedException {
        System.out.println(">>>Consumed: " + buffer.poll());
    }
}
