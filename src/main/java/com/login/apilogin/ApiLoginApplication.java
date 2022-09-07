package com.login.apilogin;

import com.login.apilogin.config.jwt.JwtConfiguration;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import static com.login.apilogin.constants.Asg.Asg.AUTHOR_LINKEDIN;
import static com.login.apilogin.constants.Asg.Asg.AUTHOR_SIGNATURE;


@SpringBootApplication
@Log4j2
@EnableConfigurationProperties(value = JwtConfiguration.class)
@EnableEurekaClient
@EnableCaching
public class ApiLoginApplication {

    public static void main(String[] args) {
        log.info(AUTHOR_SIGNATURE);
        log.info(AUTHOR_LINKEDIN);
        log.info("Versão 0.0.3");
        SpringApplication.run(ApiLoginApplication.class, args);
    }

//    @Bean
//    public RedisCacheConfiguration cacheConfiguration() {
//        return RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofMinutes(60))
//                .disableCachingNullValues()
//                .serializeValuesWith(RedisSerializationContext.SerializationPair
//                                             .fromSerializer(new GenericJackson2JsonRedisSerializer()));
//    }

//    @Bean
//    MeterRegistryCustomizer<MeterRegistry> configurer(
//            @Value("${spring.application.name}") String applicationName) {
//        return (registry) -> registry.config().commonTags("application", applicationName);
//    }

}
