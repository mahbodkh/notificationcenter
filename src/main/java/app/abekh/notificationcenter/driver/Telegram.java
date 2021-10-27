package app.abekh.notificationcenter.driver;

import app.abekh.notificationcenter.domain.MessageEntity;
import app.abekh.notificationcenter.exception.SendFailedException;

/**
 * Created by Ebrahim Kh.
 */


public class Telegram implements Driver {

    @Override
    public MessageEntity send(MessageEntity message) throws SendFailedException {
        return null;
    }

    @Override
    public MessageEntity.Type getType() {
        return MessageEntity.Type.TELEGRAM;
    }

    @Override
    public MessageEntity validation(MessageEntity message) throws SendFailedException {
        return null;
    }
}
