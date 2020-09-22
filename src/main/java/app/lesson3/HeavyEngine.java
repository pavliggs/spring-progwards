package app.lesson3;

import javax.annotation.PostConstruct;

public class HeavyEngine implements Engine {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    public void init() {
        System.out.println("HeavyEngine init");
    }

    @Override
    public String toString() {
        return "HeavyEngine{" +
                "name='" + name + '\'' +
                '}';
    }
}
