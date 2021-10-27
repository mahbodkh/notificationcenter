package app.abekh.notificationcenter.driver;

import app.abekh.notificationcenter.domain.MessageEntity;
import app.abekh.notificationcenter.driver.provider.SmsProvider;

/**
 * Created by Ebrahim Kh.
 */


public class Sms extends SmsProvider implements Driver {
    @Override
    public MessageEntity send(MessageEntity message) {

        //
        message = provide(message);

        message.setStatus(MessageEntity.Status.DONE);
        return message;
    }

    @Override
    public MessageEntity validation(MessageEntity message) {
        return null;
    }

    @Override
    public MessageEntity.Type getType() {
        return MessageEntity.Type.SMS;
    }
}
