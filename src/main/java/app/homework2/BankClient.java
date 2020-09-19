package app.homework2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BankClient {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("account.xml");
        AccountService service = context.getBean(AccountService.class);

        service.withdraw(123, 990);
    }
}
