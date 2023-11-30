package com.epam.jmp.dhontar.task5.prodcom;

import com.epam.jmp.dhontar.task5.prodcom.semaphore.SemaphoreConsumer;
import com.epam.jmp.dhontar.task5.prodcom.semaphore.SemaphoreProducer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class ProducerConsumer {

    private static final int BUFFER_SIZE = 10;
    private static final int PRODUCER_COUNT = 3;
    private static final int CONSUMER_COUNT = 2;

    private final BlockingQueue<Integer> buffer = new LinkedBlockingQueue<>(BUFFER_SIZE);
    private final Semaphore producerSemaphore = new Semaphore(BUFFER_SIZE);
    private final Semaphore consumerSemaphore = new Semaphore(0);

    public void start() {
        for (int i = 0; i < PRODUCER_COUNT; i++) {
            new Thread(new SemaphoreProducer(producerSemaphore, consumerSemaphore, buffer)).start();
        }
        for (int i = 0; i < CONSUMER_COUNT; i++) {
            new Thread(new SemaphoreConsumer(producerSemaphore, consumerSemaphore, buffer)).start();
        }
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        pc.start();
    }
}
