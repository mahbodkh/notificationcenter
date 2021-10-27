package app.abekh.notificationcenter.service;

import app.abekh.notificationcenter.domain.MessageEntity;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Ebrahim Kh.
 */



@AllArgsConstructor
public class MessageProducer implements Runnable {
    private final static short MAX = 127;
    private final BlockingQueue<MessageEntity> messageQueue = new ArrayBlockingQueue<>(MAX);
    private final Lock lock = new ReentrantLock();

    @SneakyThrows
    @Override
    public void run() {
        var current = Thread.currentThread();
        while (current.isAlive()) {
            if (messageQueue.size() <= percentageSize(30)) {
                fetch();
            }
            if (messageQueue.size() >= percentageSize(90)) {
                synchronized (lock) {
                    lock.wait(16_000);
                }
            }
        }
    }

    protected BlockingQueue<MessageEntity> getQueue() {
        return messageQueue;
    }

    private void fetch() {
        var messages = messageService.loadTopRegisteredMessages();
        synchronized (lock) {
            messages.stream()
                .filter(Objects::nonNull)
                .forEach(messageQueue::offer);
        }
    }

    private int percentageSize(int percent) {
        return percent * 100 / MAX;
    }

    private final MessageService messageService;
}
