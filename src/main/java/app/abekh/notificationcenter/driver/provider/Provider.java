package app.abekh.notificationcenter.driver.provider;

import app.abekh.notificationcenter.domain.MessageEntity;

/**
 * Created by Ebrahim Kh.
 */


public interface Provider {
    MessageEntity provide(MessageEntity message);
}
