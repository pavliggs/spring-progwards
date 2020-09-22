package app.lesson2.homework2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigAccount {

    @Bean
    public Store<Account> getAccountStore() {
        return new AccountStore();
    }

    @Bean
    public AccountService getAccountService() {
        return new AccountServiceImpl();
    }
}
