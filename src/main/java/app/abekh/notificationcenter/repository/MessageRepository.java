package app.abekh.notificationcenter.repository;

import app.abekh.notificationcenter.domain.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ebrahim Kh.
 */


@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findTop100ByStatusOrderByCreatedAsc(MessageEntity.Status status);
}
