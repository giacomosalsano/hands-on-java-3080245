package bank.models;

import bank.exceptions.AmountException;
import java.math.BigDecimal;

public class Account {
  private int id;
  private String type;
  private BigDecimal balance;

  public Account(int id, String type, BigDecimal balance) {
    this.id = id;
    this.type = type;
    this.balance = balance;
  }

  public int getId() {
    return this.id;
  }

  public String getType() {
    return this.type;
  }

  public BigDecimal getBalance() {
    return this.balance;
  }

  public void deposit(BigDecimal amount) throws AmountException {
    if (amount.compareTo(new BigDecimal("0.50")) < 0) {
      throw new AmountException("The minimum deposit is 0.50€");
    }
    this.balance = this.balance.add(amount);
  }

  public void withdraw(BigDecimal amount) throws AmountException {
    if (amount.compareTo(new BigDecimal("0.50")) < 0) {
      throw new AmountException("The minimum withdraw is 0.50€");
    }
    if (amount.compareTo(this.balance) > 0) {
      throw new AmountException("You do not have sufficient funds. Your balance is: " + this.balance + "€");
    }
    this.balance = this.balance.subtract(amount);
  }
}