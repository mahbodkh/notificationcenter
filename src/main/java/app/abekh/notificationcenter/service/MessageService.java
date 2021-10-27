package app.abekh.notificationcenter.service;

import app.abekh.notificationcenter.domain.MessageEntity;
import app.abekh.notificationcenter.event.MessageRejectedEvent;
import app.abekh.notificationcenter.event.MessageSentEvent;
import app.abekh.notificationcenter.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ebrahim Kh.
 */


@Service
@AllArgsConstructor
public class MessageService {
//    private final MessageRepository messageRepository;

    @EventListener(MessageSentEvent.class)
    public void messageSentEventForPersistence(MessageSentEvent event) {
        var message = event.getMessageEntity();
//        if (message.getStatus().equals(MessageEntity.Status.REGISTERED))
//            messageRepository.save(event.getMessageEntity());
    }

    @EventListener(MessageRejectedEvent.class)
    public void messageFailedEventForPersistence(MessageRejectedEvent event) {
        var message = event.getMessageEntity();
        message.setStatus(MessageEntity.Status.FAILED);
//        messageRepository.save(message);
    }

    @Transactional(readOnly = true)
    public List<MessageEntity> loadTopRegisteredMessages() {
//        return messageRepository.findTop100ByStatusOrderByCreatedAsc(MessageEntity.Status.REGISTERED);
        List<MessageEntity> messages = new ArrayList<>();
        for (int i = 1 ; i < 10 ; i++) messages.add(buildMessages());
        return messages;
    }


    private MessageEntity buildMessages(){
        var message = new MessageEntity();
        message.setStatus(MessageEntity.Status.REGISTERED);
        message.setContent("hello content");
        message.setTo("+989121231234");
        message.setType(MessageEntity.Type.SMS);
        message.setUserId(1L);
        return message;
    }


    @Transactional
    public <T> MessageEntity create(Long user, MessageEntity.Type type, String to, String content, T properties) {
        var message = new MessageEntity();
        message.setTo(to);
        message.setContent(content);
        message.setUserId(user);
        message.setType(type);
        message.setStatus(MessageEntity.Status.REGISTERED);
        if (properties instanceof MessageEntity.SmsProperties)
            message.setSmsProperties((MessageEntity.SmsProperties) properties);
        if (properties instanceof MessageEntity.EmailProperties)
            message.setEmailProperties((MessageEntity.EmailProperties) properties);
        if (properties instanceof MessageEntity.PushProperties)
            message.setPushProperties((MessageEntity.PushProperties) properties);
        return message;
    }
}
