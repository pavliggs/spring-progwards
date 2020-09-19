package app.homework2;

import app.homework2.exceptions.NotEnoughMoneyException;
import app.homework2.exceptions.UnknownAccountException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AccountServiceImpl implements AccountService, ApplicationContextAware {
    private ApplicationContext context;
    private AccountStore store;

    public AccountServiceImpl() {
        System.out.println("AccountServiceImpl constructor");
    }

    @Override
    public void withdraw(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException {
        store.read();
        Account account = store.getValues().get(accountId);
        System.out.println(account);
        if (account == null)
            throw new UnknownAccountException("Аккаунта с id=" + accountId + " не существует");
        int result = account.getAmount() - amount;
        if (result < 0)
            throw new NotEnoughMoneyException("Недостаточно средств на счёте");
        account.setAmount(result);
        System.out.println(account);
        store.write(account);
    }

    @Override
    public void balance(int accountId) throws UnknownAccountException {

    }

    @Override
    public void deposit(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException {

    }

    @Override
    public void transfer(int from, int to, int amount) throws NotEnoughMoneyException, UnknownAccountException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        store = context.getBean(AccountStore.class);
//        store.addingAccounts();
    }
}
