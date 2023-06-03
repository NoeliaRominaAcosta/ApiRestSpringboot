package com.itrsa.monolith.configuration;

import com.itrsa.monolith.service.UserServiceBusiness;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestConfiguration {

    @Bean
    @Primary
    public UserServiceBusiness userServiceBusiness() {
        return Mockito.mock(UserServiceBusiness.class);
    }
}
