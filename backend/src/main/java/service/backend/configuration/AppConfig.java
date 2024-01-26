package service.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.backend.service.AccountService;

@Configuration
public class AppConfig {

    @Bean
    public AccountService accountService() {
        return new AccountService();
    }
}
