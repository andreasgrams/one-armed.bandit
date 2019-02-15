package de.dreamnetworx.one.armed.bandit;

import de.dreamnetworx.one.armed.bandit.endpoint.OneArmedBanditService;
import de.dreamnetworx.one.armed.bandit.endpoint.OneArmedBanditServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class StarterConfig {

    @Bean
    @SessionScope(proxyMode = ScopedProxyMode.INTERFACES)
    public OneArmedBanditService oneArmedBanditService() {
        return new OneArmedBanditServiceImpl();
    }
}
