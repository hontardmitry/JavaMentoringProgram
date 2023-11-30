package com.epam.jmp.dhontar.task5.prodcom.semaphore;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class SemaphoreConsumer implements Runnable {

    private final Semaphore producerSemaphore;
    private final Semaphore consumerSemaphore;
    private final BlockingQueue<Integer> buffer;

    public SemaphoreConsumer(Semaphore producerSemaphore, Semaphore consumerSemaphore, BlockingQueue<Integer> buffer) {
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
