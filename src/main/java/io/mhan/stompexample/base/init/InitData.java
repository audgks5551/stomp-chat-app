package io.mhan.stompexample.base.init;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class InitData {

    @Bean
    @Profile("default")
    public ApplicationRunner defaultInitData(TestService testService) {
        return args -> {
            testService.createTestData();
        };
    }
}
