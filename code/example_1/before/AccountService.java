public class AccountService {
  public static void transfer(final Account from, final Account to, final int amount) {
    if(from.withdraw(amount))
      to.deposit(amount);
  }
}