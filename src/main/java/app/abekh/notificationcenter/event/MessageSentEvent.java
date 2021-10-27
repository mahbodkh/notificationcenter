package app.abekh.notificationcenter.event;

import app.abekh.notificationcenter.domain.MessageEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Ebrahim Kh.
 */


@RequiredArgsConstructor
@Getter
public class MessageSentEvent {
    private final MessageEntity messageEntity;
}
