package app.abekh.notificationcenter.consomer;

import app.abekh.notificationcenter.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by Ebrahim Kh.
 */

@Slf4j
@Component
@SuppressWarnings("unchecked")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MessageConsumer {

//    @KafkaListener(
//        topics = "v1.notificationcenter.sendemail",
//        containerFactory = "singleKafkaListenerContainerFactory",
//        errorHandler = "deadLetterQueueErrorHandler"
//    )
//    public void sendEmail(byte[] buffer) throws Exception {
////        messageService.create();
//    }
//
//
//    @KafkaListener(
//        topics = "v1.notificationcenter.sendsms",
//        containerFactory = "singleKafkaListenerContainerFactory",
//        errorHandler = "deadLetterQueueErrorHandler"
//    )
//    public void sendSms(byte[] buffer) throws Exception {
//
//    }
//
//
//    @KafkaListener(
//        topics = "v1.notificationcenter.sendfirebase",
//        containerFactory = "singleKafkaListenerContainerFactory",
//        errorHandler = "deadLetterQueueErrorHandler"
//    )
//    public void sendFirebase(byte[] buffer) throws Exception {
//
//    }
//
//
//    @KafkaListener(
//        topics = "v1.notificationcenter.sendtelegram",
//        containerFactory = "singleKafkaListenerContainerFactory",
//        errorHandler = "deadLetterQueueErrorHandler"
//    )
//    public void sendTelegram(byte[] buffer) throws Exception {
//
//    }
//
//
//    @KafkaListener(
//        topics = "v1.notificationcenter.sendwhatsapp",
//        containerFactory = "singleKafkaListenerContainerFactory",
//        errorHandler = "deadLetterQueueErrorHandler"
//    )
//    public void sendWhatsapp(byte[] buffer) throws Exception {
//
//    }


    private final MessageService messageService;
}
