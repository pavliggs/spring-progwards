package app.lesson3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Car {

    private Engine engine;

    @Autowired
    Environment env;

    public Car() {
    }

    @Autowired
    @Qualifier("lightEngine")
    public void setEngine(Engine engine) {
        System.out.println(env.getProperty("name"));
        this.engine = engine;
    }

    public Engine getEngine() {
        return engine;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                '}';
    }
}
