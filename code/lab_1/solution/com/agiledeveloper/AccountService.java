package com.agiledeveloper;

import clojure.lang.LockingTransaction;

import java.util.concurrent.Callable;

public class AccountService {
  public void transfer(final Account from, final Account to, final int amount) {
    try {
      LockingTransaction.runInTransaction(new Callable<Void>() {
        @Override
        public Void call() throws Exception {
          to.deposit(amount);
          from.withdraw(amount);
          return null;
        }
      });
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }
}
