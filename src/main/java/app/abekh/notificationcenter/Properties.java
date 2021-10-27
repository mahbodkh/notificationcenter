package app.abekh.notificationcenter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Ebrahim Kh.
 */

@ConfigurationProperties(prefix = "abekh.notificationcenter")
@Data
public class Properties {

    @Data
    public static class Sms {

    }

    private Sms sms = new Sms();


    @Data
    public static class Email {

    }
    private Email email = new Email();

    @Data
    public static class ThreadPoolTaskExecutor{
        private Integer corePoolSize;
        private Integer maxPoolSize;
    }
    private ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();


}
