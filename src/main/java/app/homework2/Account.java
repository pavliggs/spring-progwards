package app.homework2;

import java.util.Objects;

public class Account implements AccountInterface<Integer> {
    private Integer id;
    private String holder;
    private int amount;

    public Account(Integer id, String holder, int amount) {
        this.id = id;
        this.holder = holder;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public String getHolder() {
        return holder;
    }

    public int getAmount() {
        return amount;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", holder='" + holder + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return amount == account.amount &&
                Objects.equals(id, account.id) &&
                Objects.equals(holder, account.holder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, holder, amount);
    }
}
