package app.lesson2.homework2;

import app.lesson2.homework2.exceptions.NotEnoughMoneyException;
import app.lesson2.homework2.exceptions.UnknownAccountException;

public interface AccountService {
    void withdraw(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException;
    void balance(int accountId) throws UnknownAccountException;
    void deposit(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException;
    void transfer(int from, int to, int amount) throws NotEnoughMoneyException, UnknownAccountException;
}
