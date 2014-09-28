package com.agiledeveloper;

public class Account {
  private int balance;
  
  public Account(int initialBalance) { balance = initialBalance; }
  
  public void deposit(int amount) {
    if (amount < 0) throw new RuntimeException("Invalid amount");
    balance += amount;
  }

  public void withdraw(int amount) {
    if (amount < 0) throw new RuntimeException("Invalid amount");
    if (amount > balance) throw new RuntimeException("Insufficient fund");
    balance -= amount;
  }
  
  public int getBalance() { return balance; }
}