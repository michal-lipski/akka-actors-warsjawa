public class AccountService {
  public static void transfer(final Account from, final Account to, final int amount) {
    Account[] accounts = new Account[] {from, to};

    if(accounts[0].monitor.tryLock(...)) {
      try {
        if(accounts[1].monitor.tryLock(...)) {
          try {
            if(from.withdraw(amount))
              to.deposit(amount);

          } finally {
            accounts[1].monitor.unlock();
          }
        }  else {
          throw new RuntimeException("no lock");
        }
      } finally {
        accounts[0].monitor.unlock();
      }
    } else {
      throw new RuntimeException("no lock");
    }
  }
}