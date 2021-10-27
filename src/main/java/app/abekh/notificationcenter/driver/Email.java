package app.abekh.notificationcenter.driver;

import app.abekh.notificationcenter.Properties;
import app.abekh.notificationcenter.domain.MessageEntity;
import app.abekh.notificationcenter.exception.SendFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Ebrahim Kh.
 */

@Slf4j
@RequiredArgsConstructor
public class Email implements Driver {
    private final Properties.Sms smsProperties;

    @Override
    public MessageEntity send(MessageEntity message) throws SendFailedException {
        return null;
    }

    @Override
    public MessageEntity.Type getType() {
        return MessageEntity.Type.EMAIL;
    }

    @Override
    public MessageEntity validation(MessageEntity message) throws SendFailedException {
        return null;
    }
}
