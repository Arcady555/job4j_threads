package ru.job4j.concurrent.task2;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

@ThreadSafe
public class AccountStorage {

    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        accounts.putIfAbsent(account.id(), account);
        return accounts.get(account.id()) != null;
    }

    public synchronized boolean update(Account account) {
        accounts.put(account.id(), account);
        return getById(account.id()).get().amount() == account.amount();
    }

    public synchronized boolean delete(int id) {
        accounts.remove(id);
        return getById(id).equals(Optional.empty());
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean rsl = false;
        if (getById(fromId).isPresent() && getById(toId).isPresent()) {
            Account accountFrom = getById(fromId).get();
            Account accountTo = getById(toId).get();
                int amountFrom = accountFrom.amount();
                int amountTo = accountTo.amount();
                if (amountFrom >= amount) {
                    update(new Account(fromId, amountFrom - amount));
                    update(new Account(toId, amountTo + amount));
                    rsl = getById(toId).get().amount() == amountTo + amount;
                }
            }
        return rsl;
    }
}