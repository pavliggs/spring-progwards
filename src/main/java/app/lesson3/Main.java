package app.lesson3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("annotationContext.xml");
        System.out.println(context.getBean(Car.class).getEngine());
    }
}
