package app.lesson3;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "app.lesson3")
@PropertySource("classpath:app.properties")
public class Config {



    @Bean("lightEngine")
    public Engine getLightEngine() {
        return new LightEngine();
    }

    @Bean(value = "heavyEngine", initMethod = "init")
    public Engine getHeavyEngine() {
        return new HeavyEngine();
    }
}
