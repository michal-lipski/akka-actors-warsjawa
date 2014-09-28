package com.agiledeveloper;

import java.util.*;
import java.util.concurrent.*;

public class UseAccounts {
  public static void main(String[] args) throws Exception {
    final Account account1 = new Account(1000);
    final Account account2 = new Account(1000);
    
    System.out.println("Account1: " + account1.getBalance());
    System.out.println("Account2: " + account2.getBalance());
    final AccountService accountService = new AccountService();

    List<Callable<Boolean>> transfers = new ArrayList<Callable<Boolean>>();
    transfers.add(new Callable<Boolean>() {public Boolean call() {
      accountService.transfer(account1, account2, 100);
      return true;
    }});
    transfers.add(new Callable<Boolean>() {public Boolean call() {
      accountService.transfer(account2, account1, 200);
      return true;
    }});

    ExecutorService executorService = Executors.newFixedThreadPool(10);
    executorService.invokeAll(transfers);

    System.out.println("Account1: " + account1.getBalance());
    System.out.println("Account2: " + account2.getBalance());
    
    try {
      accountService.transfer(account1, account2, 2000);      
    } catch(Exception ex) {
      System.out.println(ex.getMessage());
    }
    System.out.println("Account1: " + account1.getBalance());
    System.out.println("Account2: " + account2.getBalance());    
    
    executorService.shutdown();
  }
}
