package app.lesson3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

//        System.out.println(context.getBean(Car.class));
        System.out.println(context.getBean(HeavyEngine.class));

    }
}
