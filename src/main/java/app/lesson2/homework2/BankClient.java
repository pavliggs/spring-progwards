package app.lesson2.homework2;

import app.lesson2.homework2.exceptions.NotEnoughMoneyException;
import app.lesson2.homework2.exceptions.UnknownAccountException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.util.List;

public class BankClient {
    private final static List<String> operations = List.of("1. balance [id]", "2. withdraw [id] [amount]",
            "3. deposit [id] [amount]", "4. transfer [idFrom] [idTo] [amount]");

    public static void operationsManagement() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ConfigAccount.class);
        context.refresh();
        AccountService service = context.getBean(AccountService.class);

        System.out.println("Доступные операции:");
        operations.forEach(System.out::println);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (br.markSupported()) {
                String text = br.readLine();
                String[] textArray = getStringArray(text);
                if (textArray.length == 2 && textArray[0].equals("balance"))
                    service.balance(toInt(textArray[1]));
                else if (textArray.length == 3 && textArray[0].equals("withdraw"))
                    service.withdraw(toInt(textArray[1]), toInt(textArray[2]));
                else if (textArray.length == 3 && textArray[0].equals("deposit"))
                    service.deposit(toInt(textArray[1]), toInt(textArray[2]));
                else if (textArray.length == 4 && textArray[0].equals("transfer"))
                    service.transfer(toInt(textArray[1]), toInt(textArray[2]), toInt(textArray[3]));
                else
                    System.out.println("Операция " + text + " не поддерживается банком");
            }
        } catch (IOException | UnknownAccountException | NotEnoughMoneyException ex) {
            ex.printStackTrace();
        }

    }

    private static String[] getStringArray(String str) {
        return str.split("\\s+");
    }

    private static int toInt(String str) {
        return Integer.parseInt(str);
    }

    public static void main(String[] args) {
        operationsManagement();
    }
}
