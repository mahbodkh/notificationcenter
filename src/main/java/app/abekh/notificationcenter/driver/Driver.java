package app.abekh.notificationcenter.driver;

import app.abekh.notificationcenter.exception.SendFailedException;
import app.abekh.notificationcenter.domain.MessageEntity;

/**
 * Created by Ebrahim Kh.
 */


public interface Driver {
    MessageEntity send(MessageEntity message) throws SendFailedException;
    MessageEntity.Type getType();
    default MessageEntity validation(MessageEntity message) throws SendFailedException { return message; }
}
