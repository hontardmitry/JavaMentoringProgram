package com.epam.jmp.dhontar.prodcom;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Consumer implements Runnable {

    private final Semaphore producerSemaphore;
    private final Semaphore consumerSemaphore;
    private final BlockingQueue<Integer> buffer;

    public Consumer(Semaphore producerSemaphore, Semaphore consumerSemaphore, BlockingQueue<Integer> buffer) {
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
        System.out.println(">>>Consumed: " + buffer.take());
    }
}
