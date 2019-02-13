package de.holisticon.bewerber.grams.one.armed.bandit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class StarterConfig {

    @Bean
    @RequestScope
    public OneArmedBanditController oneArmedBanditController() {
        return new OneArmedBanditController();
    }

    @Bean
    @SessionScope(proxyMode = ScopedProxyMode.INTERFACES)
    public OneArmedBanditService oneArmedBanditService() {
        return new OneArmedBanditServiceImpl();
    }
}