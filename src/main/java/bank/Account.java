package bank;

import java.sql.SQLException;

import bank.exceptions.AmountException;

public class Account {
  private int id;
  private String type;
  private double balance;

  public Account(int id, String type, double balance) {
    setId(id);
    setType(type);
    setBalance(balance);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String isType() {
    return this.type;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void deposit(double amount) throws AmountException {
    if (amount < 0.5) {
      throw new AmountException("The minimun deposit is 0.50€");
    } else {
      double newBalance = balance + amount;
      setBalance(newBalance);
      try {
        DataSource.updateAccountBalance(id, newBalance);
        System.out.println(
            "Successfully deposited: " + amount + "€ into your account. Your balance is now: " + newBalance + "€.");
      } catch (SQLException e) {
        System.out.println("----------------------------------------------------");
        System.out.println("Error while depositing " + amount + "€ into your account. Please try again later.\n");
        System.out.println(e.getMessage());
        System.out.println("---------------------------------------------------- \n");
        throw new RuntimeException(e);
      }
    }
  }

  public void withdraw(double amount) throws AmountException {
    double actualBalance = getBalance();
    double newBalance = actualBalance - amount;
    boolean itCanWithdraw = amount <= actualBalance;

    if (amount < 0.5) {
      throw new AmountException("The minimun withdraw is 0.50€");
    } else if (!itCanWithdraw) {
      throw new AmountException("You do not have sufficient funds for this withdraw. Your balance is: ");
    } else {
      setBalance(newBalance);
      try {
        DataSource.updateAccountBalance(id, newBalance);
        System.out.println(
            "Successfully withdrawed: " + amount + "€ into your account. Your balance is now: " + newBalance + "€.");
      } catch (SQLException e) {
        System.out.println("----------------------------------------------------");
        System.out.println("Error while withdrawing " + amount + "€ from your account. Please try again later.\n");
        System.out.println(e.getMessage());
        System.out.println("---------------------------------------------------- \n");
        throw new RuntimeException(e);
      }
    }

  }

}
