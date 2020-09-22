package app.lesson1.homework1.calc;

import app.lesson3.Config;
import com.google.gson.internal.bind.util.ISO8601Utils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Scanner;

public class MainCalculator {
    static final String PATH_CONTEXT = "C:\\Users\\Эльдорадо\\IdeaProjects\\spring-progwards\\" +
            "src\\main\\resources\\context.xml";
    static String numberOne;
    static String numberTwo;

    public static void startCalculator() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String text = "";
        System.out.println("Введите имя метода");
        while (scanner.hasNext()) {
            text = scanner.nextLine();
            if (text.equals("sum")) {
                runMethod("sum", scanner);
                break;
            }
            else if (text.equals("diff")) {
                runMethod("diff", scanner);
                break;
            }
            else if (text.equals("mult")) {
                runMethod("mult", scanner);
                break;
            }
            else if (text.equals("div")) {
                runMethod("div", scanner);
                break;
            }
            else if (text.equals("stop"))
                break;
            else
                System.out.println("Такого метода не существует");
        }
    }

    private static void runMethod(String method, Scanner scanner) {
        System.out.println("Введите тип калькулятора (калькулятор может быть \"simple\" или \"advanced\")");
        String text = scanner.nextLine();
        ICalculator calculator = null;
        if (text.equals("simple")) {
            calculator = initCalculator(SimpleCalculator.class);
        } else if (text.equals("advanced")) {
            calculator = initCalculator(AdvancedCalculator.class);
        } else {
            System.out.println("Такого калькулятора не существует");
            return;
        }
        System.out.println("Введите первое число");
        numberOne = scanner.nextLine();
        System.out.println("Введите второе число");
        numberTwo = scanner.nextLine();
        if (method.equals("sum"))
            System.out.println(method + " = " +
                    calculator.sum(Integer.parseInt(numberOne), Integer.parseInt(numberTwo)));
        if (method.equals("diff"))
            System.out.println(method + " = " +
                    calculator.diff(Integer.parseInt(numberOne), Integer.parseInt(numberTwo)));
        if (method.equals("mult"))
            System.out.println(method + " = " +
                    calculator.mult(Integer.parseInt(numberOne), Integer.parseInt(numberTwo)));
        if (method.equals("div"))
            System.out.println(method + " = " +
                    calculator.div(Integer.parseInt(numberOne), Integer.parseInt(numberTwo)));
    }

    private static ICalculator initCalculator(Class<?> clazz) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ConfigCalculator.class);
        context.refresh();
        return (ICalculator) context.getBean(clazz);
    }

    // метод срабатывает когда у контекста вызывается метод refresh()
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Context refresh");
    }

    public static void main(String[] args) throws IOException {
        startCalculator();
    }
}
