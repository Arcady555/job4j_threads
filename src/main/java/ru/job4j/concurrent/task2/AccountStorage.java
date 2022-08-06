package ru.job4j.concurrent.task2;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        int key  = getNewKey(accounts);
        accounts.putIfAbsent(key, account);
        return accounts.get(key) != null;
    }

    public synchronized boolean update(Account account) {
        int key = getKeyByValue(account);
        if (key != -1) {
            accounts.put(key, account);
        }
        return accounts.get(key).amount() == account.amount();
    }

    public synchronized boolean delete(int id) {
        if (getById(id).isPresent()) {
            Account account = getById(id).get();
            int key = getKeyByValue(account);
            if (key != -1) {
                accounts.remove(key);
            }
        }
        return getById(id).equals(Optional.empty());
    }

    public synchronized Optional<Account> getById(int id) {
        Account rsl = null;
        for (Account account : accounts.values()) {
            if (account.id() == id) {
                rsl = account;
                break;
            }
        }
        return rsl == null ? Optional.empty() : Optional.of(rsl);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean rsl = false;
        if (getById(fromId).isPresent() && getById(toId).isPresent()) {
            int amountFrom = getById(fromId).get().amount();
            int amountTo = getById(toId).get().amount();
            if (amountFrom >= amount) {
                Account accountFrom = new Account(fromId, amountFrom - amount);
                Account accountTo = new Account(toId, amountTo + amount);
                update(accountFrom);
                update(accountTo);
                rsl = getById(toId).get().amount() == amountTo + amount;
            }
        }
        return rsl;
    }

    private int getNewKey(Map<Integer, Account> map) {
        int newKey = 0;
        List<Integer> list = map.keySet().stream().sorted().toList();
        for (int key : list) {
            if (newKey == key) {
                newKey++;
            } else {
                break;
            }
        }
        return newKey;
    }

    private synchronized int getKeyByValue(Account account) {
        int key = -1;
        for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
            if (entry.getValue().id() == account.id()) {
                key = entry.getKey();
                break;
            }
        }
        return key;
    }
}