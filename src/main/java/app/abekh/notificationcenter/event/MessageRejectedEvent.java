package app.abekh.notificationcenter.event;

import app.abekh.notificationcenter.domain.MessageEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Ebrahim Kh.
 */


@RequiredArgsConstructor
@Getter
public class MessageRejectedEvent {
    private final MessageEntity messageEntity;
    private final String rejectReason;
}
