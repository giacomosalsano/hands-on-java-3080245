package bank.models;

public class Customer {
  private int id;
  private String name;
  private String username;
  private String passwordHash;
  private int accountId;

  public Customer(int id, String name, String username, String passwordHash, int accountId) {
    this.id = id;
    this.name = name;
    this.username = username;
    this.passwordHash = passwordHash;
    this.accountId = accountId;
  }

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getUsername() {
    return this.username;
  }

  public String getPasswordHash() {
    return this.passwordHash;
  }

  public int getAccountId() {
    return this.accountId;
  }

}