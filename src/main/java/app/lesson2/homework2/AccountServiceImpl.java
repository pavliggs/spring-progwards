package app.lesson2.homework2;

import app.lesson2.homework2.exceptions.NotEnoughMoneyException;
import app.lesson2.homework2.exceptions.UnknownAccountException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AccountServiceImpl implements AccountService, ApplicationContextAware, InitializingBean {
    private ApplicationContext context;
    private AccountStore store;

    public AccountServiceImpl() {
        System.out.println("AccountServiceImpl constructor");
    }

    @Override
    public void withdraw(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException {
        store.read();
        Account account = store.getMapValues().get(accountId);
        if (account == null)
            throw new UnknownAccountException("Аккаунта с id=" + accountId + " не существует");
        System.out.println("Баланс аккаунта с id=" + accountId + " до снятия суммы " + amount + " = " +
                account.getAmount());
        int result = account.getAmount() - amount;
        if (result < 0)
            throw new NotEnoughMoneyException("Недостаточно средств на счёте");
        account.setAmount(result);
        store.write(account);
        System.out.println("Баланс аккаунта с id=" + accountId + " после снятия суммы " + amount + " = " +
                account.getAmount());
    }

    @Override
    public void balance(int accountId) throws UnknownAccountException {
        Account account = store.getMapValues().get(accountId);
        if (account == null)
            throw new UnknownAccountException("Аккаунта с id=" + accountId + " не существует");
        System.out.println(account.getAmount());
    }

    @Override
    public void deposit(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException {
        store.read();
        Account account = store.getMapValues().get(accountId);
        if (account == null)
            throw new UnknownAccountException("Аккаунта с id=" + accountId + " не существует");
        System.out.println("Баланс аккаунта с id=" + accountId + " до внесения суммы " + amount + " = " +
                account.getAmount());
        int result = account.getAmount() + amount;
        account.setAmount(result);
        store.write(account);
        System.out.println("Баланс аккаунта с id=" + accountId + " после внесения суммы " + amount + " = " +
                account.getAmount());
    }

    @Override
    public void transfer(int from, int to, int amount) throws NotEnoughMoneyException, UnknownAccountException {
        store.read();
        Account accountFrom = store.getMapValues().get(from);
        Account accountTo = store.getMapValues().get(to);
        if (accountFrom == null)
            throw new UnknownAccountException("Аккаунта с id=" + from + " не существует");
        if (accountTo == null)
            throw new UnknownAccountException("Аккаунта с id=" + to + " не существует");
        int amountFrom = accountFrom.getAmount() - amount;
        if (amountFrom < 0)
            throw new NotEnoughMoneyException("Недостаточно средств на счёте аккаунта с id=" + from +
                    " для перевода суммы " + amount);
        System.out.println("Баланс аккаунта с id=" + from + " до перевода суммы " + amount + " = " +
                accountFrom.getAmount());
        System.out.println("Баланс аккаунта с id=" + to + " до получения суммы " + amount + " = " +
                accountTo.getAmount());
        accountFrom.setAmount(amountFrom);
        int amountTo = accountTo.getAmount() + amount;
        accountTo.setAmount(amountTo);
        System.out.println("Баланс аккаунта с id=" + from + " после перевода суммы " + amount + " = " +
                accountFrom.getAmount());
        System.out.println("Баланс аккаунта с id=" + to + " после получения суммы " + amount + " = " +
                accountTo.getAmount());
        store.write(accountFrom);
        store.write(accountTo);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        store = context.getBean(AccountStore.class);
        if (store.read().size() != 10)
            store.addingAccounts();
    }
}
