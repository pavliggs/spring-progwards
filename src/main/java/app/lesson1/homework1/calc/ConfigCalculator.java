package app.lesson1.homework1.calc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigCalculator {

    @Bean
    public ICalculator getSimpleCalculator() {
        return new SimpleCalculator();
    }

    @Bean
    public ICalculator getAdvancedCalculator() {
        return new AdvancedCalculator();
    }
}
