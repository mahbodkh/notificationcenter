package app.abekh.notificationcenter.service;

import app.abekh.notificationcenter.domain.MessageEntity;
import app.abekh.notificationcenter.driver.Driver;
import app.abekh.notificationcenter.event.MessageRejectedEvent;
import app.abekh.notificationcenter.event.MessageSentEvent;
import app.abekh.notificationcenter.exception.SendFailedException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Ebrahim Kh.
 */


@AllArgsConstructor
public class MessageConsumer implements Runnable {
    private final Lock lock = new ReentrantLock();

    @SneakyThrows
    @Override
    public void run() {
        var current = Thread.currentThread();
        while (current.isAlive()) {
            var messages = producer.getQueue();
            runWorker(messages.take());
        }
    }

    protected void runWorker(MessageEntity message) {
        try {
            var sent = driver.send(message);
            System.out.println("message");
            eventPublisher.publishEvent(new MessageSentEvent(sent));
        } catch (Throwable t) {
            if (t instanceof SendFailedException) {
                // log
            }
            eventPublisher.publishEvent(new MessageRejectedEvent(message, ""));
        }
    }

    private final Driver driver;
    private final MessageProducer producer;
    private final ApplicationEventPublisher eventPublisher;
}
