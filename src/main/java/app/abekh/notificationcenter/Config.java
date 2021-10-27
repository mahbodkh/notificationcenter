package app.abekh.notificationcenter;

import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Ebrahim Kh.
 */


@Configuration
@Import({
//    KafkaAutoConfiguration.class,
//    CacheAutoConfiguration.class,
})
@EnableTransactionManagement(order = 3)
@EnableCaching(order = 2)
@EnableJpaRepositories
@EnableConfigurationProperties(Properties.class)
//@EnableAsync
@EnableScheduling
//@EnableKafka
public class Config {

    @Bean("threadPoolTaskExecutor")
    public TaskExecutor getAsyncExecutor(Properties properties) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getThreadPoolTaskExecutor().getCorePoolSize());
        executor.setMaxPoolSize(properties.getThreadPoolTaskExecutor().getMaxPoolSize());
        executor.setThreadNamePrefix("Async-");
        return executor;
    }

}
