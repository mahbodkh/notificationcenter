package app.abekh.notificationcenter.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by Ebrahim Kh.
 */


@Data
//@Entity
//@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class MessageEntity implements Comparable<MessageEntity> {

    private static final ObjectMapper OBJECT_MAPPER
        = new ObjectMapper();

    @Override
    public int compareTo(MessageEntity o) {
        return getId().compareTo(o.getId());
    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Builder
    public static class EmailProperties {
        private String subject;
        private String sender;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Builder
    public static class SmsProperties {
        private String result;       // result from your third-party
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Builder
    public static class PushProperties {
        private String subject;
        private String sid;         // sender id
        private Map<String, String> data;
        private String image;
    }

    private Long id;
    private Long userId;
    private String to;
    private String content;
    private Status status;
    @Enumerated(EnumType.STRING)
    private Type type;
//    @org.hibernate.annotations.Type(type = "jsonb")
    private Map<String, Object> properties;
    @CreationTimestamp
    private Date created;
    @UpdateTimestamp
    private Date changed;


    public enum Type {
        SMS,
        EMAIL,
        FIREBASE,
        TELEGRAM,
        WHATSAPP,
        PUSH,
    }

    public enum Status {
        REGISTERED,
        PROCESS,
        DONE,
        FAILED,
        CANCELED
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageEntity that = (MessageEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, to);
    }


    @Transient
    public EmailProperties getEmailProperties() {
        assert getType() == Type.EMAIL;
        return getPropertiesByType(EmailProperties.class);
    }

    @Transient
    public void setEmailProperties(EmailProperties emailProperties) {
        assert getType() == Type.EMAIL;
        setPropertiesByType(emailProperties);
    }

    @Transient
    public SmsProperties getSmsProperties() {
        assert getType() == Type.SMS;
        return getPropertiesByType(SmsProperties.class);
    }

    @Transient
    public void setSmsProperties(SmsProperties smsProperties) {
        assert getType() == Type.SMS;
        setPropertiesByType(smsProperties);
    }

    @Transient
    public PushProperties getPushProperties() {
        assert getType() == Type.PUSH;
        return getPropertiesByType(PushProperties.class);
    }

    @Transient
    public void setPushProperties(PushProperties firebasePushProperties) {
        assert getType() == Type.PUSH;
        setPropertiesByType(firebasePushProperties);
    }

    @Transient
    private <T> T getPropertiesByType(Class<T> classType) {
        var props = Optional.ofNullable(getProperties()).orElseGet(HashMap::new);
        setProperties(props);
        return OBJECT_MAPPER.convertValue(props, classType);
    }

    @SuppressWarnings("unchecked")
    @Transient
    private <T> void setPropertiesByType(T t) {
        var newMap = OBJECT_MAPPER.convertValue(t, Map.class);
        setProperties(newMap);
    }

}
