package com.agiledeveloper;

import clojure.lang.LockingTransaction;
import clojure.lang.Ref;

import java.util.concurrent.Callable;

public class Account {
  private Ref balance;
  
  public Account(int initialBalance) { balance = new Ref(initialBalance); }
  
  public void deposit(final int amount) throws Exception {
    if (amount < 0) throw new RuntimeException("Invalid amount");

    LockingTransaction.runInTransaction(
        new Callable<Void>() {
          @Override
          public Void call() throws Exception {
            balance.set(getBalance() + amount);
            return null;
          }
        });
  }

  public void withdraw(final int amount) throws Exception {
    if (amount < 0) throw new RuntimeException("Invalid amount");
    LockingTransaction.runInTransaction(
        new Callable<Void>() {
          @Override
          public Void call() throws Exception {
            if (amount > getBalance()) throw new RuntimeException("Insufficient fund");
            balance.set(getBalance() - amount);
            return null;
          }
        });
  }
  
  public int getBalance() { return (Integer) balance.deref(); }
}