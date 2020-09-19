package app.homework2;

import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

public class AccountStore extends StoreImpl<Integer, Account> {

    public AccountStore() {
        super(new TypeToken<ArrayList<Account>>(){}.getType());
    }

    public void addingAccounts() {
        write(new Account(123, "Pavel", 2278990));
        write(new Account(124, "Igor", 1008990));
        write(new Account(125, "Maria", 8976790));
        write(new Account(126, "Kirill", 755600));
        write(new Account(127, "Vladimir", 345800));
        write(new Account(128, "Tanya", 9567900));
        write(new Account(129, "Olga", 3300678));
        write(new Account(130, "Irina", 6789123));
        write(new Account(131, "Ilya", 4678000));
        write(new Account(132, "Timofey", 1675000));
    }
}
