package ru.job4j.concurrent.task2;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

@ThreadSafe
public class AccountStorage {

    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), account) != null;
    }

    public synchronized boolean delete(int id) {
        return accounts.remove(id) != null;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean rsl = false;
        Optional<Account> accountFrom = getById(fromId);
        Optional<Account> accountTo = getById(toId);
        if (accountFrom.isPresent() && accountTo.isPresent() && accountFrom.get().amount() >= amount) {
            int amountFrom = accountFrom.get().amount();
            int amountTo = accountTo.get().amount();
            update(new Account(fromId, amountFrom - amount));
            update(new Account(toId, amountTo + amount));
            rsl = true;
        }
        return rsl;
    }
}