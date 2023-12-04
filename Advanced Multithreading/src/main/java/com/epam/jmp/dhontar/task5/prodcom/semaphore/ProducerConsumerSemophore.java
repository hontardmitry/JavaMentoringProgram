package com.epam.jmp.dhontar.task5.prodcom.semaphore;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class ProducerConsumerSemophore {

    private static final int BUFFER_SIZE = 10;
    private static final int PRODUCER_COUNT = 3;
    private static final int CONSUMER_COUNT = 2;

    private final ConcurrentLinkedQueue<Integer> buffer = new ConcurrentLinkedQueue<>();
    private final Semaphore producerSemaphore = new Semaphore(BUFFER_SIZE);
    private final Semaphore consumerSemaphore = new Semaphore(0);

    public void start() {
        for (int i = 0; i < PRODUCER_COUNT; i++) {
            new Thread(new ProducerSemaphore(producerSemaphore, consumerSemaphore, buffer)).start();
        }
        for (int i = 0; i < CONSUMER_COUNT; i++) {
            new Thread(new ConsumerSemaphore(producerSemaphore, consumerSemaphore, buffer)).start();
        }
    }

    public static void main(String[] args) {
        ProducerConsumerSemophore pc = new ProducerConsumerSemophore();
        pc.start();
    }
}
