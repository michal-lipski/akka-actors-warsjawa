import scala.annotation.meta.companionClass;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account implements Comparable<Account> {
  //implement compareTo method...

  private int balance;
  public final Lock monitor = new ReentrantLock();

  public Account(final int initialBalance) {
    balance = initialBalance;
  }

  public void deposit(final int amount) throws InterruptedException {
    if (monitor.tryLock(1, TimeUnit.SECONDS)) {
      try {
        if (amount > 0) balance += amount;
      } finally {
        monitor.unlock();
      }
    } else {
      throw new RuntimeException("No luck");
    }
  }

  public synchronized boolean withdraw(final int amount) throws InterruptedException {
    if (monitor.tryLock(1, TimeUnit.SECONDS)) {
      try {
        if (amount > 0 && balance >= amount) {
          balance -= amount;
          return true;
        }

        return false;
      } finally {
        monitor.unlock();
      }
    } else {
      throw new RuntimeException("No luck");
    }
  }

  public synchronized int getBalance() {
    return balance;
  }
}
