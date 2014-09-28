public class Account {
  private int balance;
  
  public Account(final int initialBalance) {
    balance = initialBalance;
  }
  
  public void deposit(final int amount) {
    if(amount > 0) balance += amount;
  }
  
  public boolean withdraw(final int amount) {
    if(amount > 0 && balance >= amount) {
      balance -= amount;
      return true;
    }
    
    return false;
  }
  
  public int getBalance() { return balance; }
}
