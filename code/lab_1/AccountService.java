package com.agiledeveloper;

public class AccountService {
  public void transfer(final Account from, final Account to, final int amount) {
    from.withdraw(amount);
    to.deposit(amount);
  }
}
