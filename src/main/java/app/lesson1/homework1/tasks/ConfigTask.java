package app.lesson1.homework1.tasks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigTask {

    @Bean
    public TaskRepository getTaskRepository() {
        return new TaskRepository();
    }
}
