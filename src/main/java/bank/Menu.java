package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import bank.exceptions.AmountException;

public class Menu {

  private Scanner scanner;

  public static void main(String[] args) {
    System.out.println("Welcome to Globe Bank!");

    Menu menu = new Menu();

    menu.scanner = new Scanner(System.in);

    Customer customer = menu.authenticateUser();

    if (customer != null) {
      Account account = DataSource.getAccount(customer.getAccountId());

      menu.showMenu(customer, account);
    }

    menu.scanner.close();
  }

  private Customer authenticateUser() {
    System.out.println("Please enter your username:");
    String username = scanner.next();

    System.out.println("Please enter your password:");
    String password = scanner.next();

    Customer customer = null;

    try {
      customer = Authenticator.login(username, password);
    } catch (LoginException e) {
      System.out.println("----------------------------------------------------");
      System.out.println("Error while authenticating user: " + username + ".\n");
      e.getMessage();
      System.out.println("---------------------------------------------------- \n");
    }

    return customer;
  }

  private void showMenu(Customer customer, Account account) {

    int selection = 0;

    while (selection != 4 && customer.isAuthenticated()) {
      System.out.println("----------------------------------------------------");
      System.out.println("Please select one of the following options: \n");
      System.out.println("1. Deposit;");
      System.out.println("2. Withdraw;");
      System.out.println("3. Check Balance;");
      System.out.println("4. Exit.");
      System.out.println("----------------------------------------------------");

      selection = scanner.nextInt();
      double amount = 0;

      switch (selection) {
        case 1:
          System.out.println("How much would you like to deposit?");

          amount = scanner.nextDouble();

          try {
            account.deposit(amount);
          } catch (AmountException e) {
            System.out.println("----------------------------------------------------");
            System.out.println(
                "Error while depositing the amount of " + amount + " into the " + account.getId() + " account:\n");
            e.getMessage();
            System.out.println("---------------------------------------------------- \n");
          }
          ;

          break;

        case 2:
          System.out.println("How much would you like to withdraw?");

          amount = scanner.nextDouble();

          account.withdraw(amount);

          break;

        case 3:
          double balance = account.getBalance();

          System.out.println("Your current balance is: " + balance + "€.");

          break;

        case 4:
          Authenticator.logout((customer));

          System.out.println("Thank you for banking at Globe Bank!");

          break;

        default:
          System.out.println("Please select a valid option.");

          break;
      }

    }
  }
}
