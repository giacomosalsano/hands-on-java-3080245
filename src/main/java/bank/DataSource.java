package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {

  public static Connection connect() {
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;

    try {
      connection = DriverManager.getConnection(db_file);
      System.out.println("---------------------------------------------------- \n");
      System.out.println("DataBase connected successfully!  \n");
      System.out.println("---------------------------------------------------- \n");
    } catch (SQLException e) {
      System.out.println("----------------------------------------------------");
      System.out.println("Error connecting DataBase: \n");
      e.printStackTrace();
      System.out.println("---------------------------------------------------- \n");
    }

    return connection;
  }

  public static Customer getCustomer(String username) {
    String sql = "select * from customers where username = ?";
    Customer customer = null;

    try (
        Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {

      statement.setString(1, username);

      try (ResultSet resultSet = statement.executeQuery()) {
        customer = new Customer(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("username"),
            resultSet.getString("password"),
            resultSet.getInt("account_id"));
      }
      ;
    } catch (SQLException e) {
      System.out.println("----------------------------------------------------");
      System.out.println("Error while getting user: " + username + ".\n"); // INFO: For security, avoid logging
                                                                           // sensitive information in production.
      e.printStackTrace();
      System.out.println("---------------------------------------------------- \n");
    }

    return customer;
  };

  public static Account getAccount(int accountId) {
    String sql = "select * from accounts where id = ?";
    Account account = null;

    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {

      statement.setInt(1, accountId);

      try (ResultSet resultSet = statement.executeQuery()) {
        account = new Account(
            resultSet.getInt("id"),
            resultSet.getString("type"),
            resultSet.getDouble("balance"));
      }
    } catch (SQLException e) {
      System.out.println("----------------------------------------------------");
      System.out.println("Error while getting account: " + accountId + ".\n"); // INFO: For security, avoid logging
                                                                               // sensitive
                                                                               // information in production.
      e.printStackTrace();
      System.out.println("---------------------------------------------------- \n");
    }
    ;

    return account;
  }

  public static void updateAccountBalance(int account_id, double balance) throws SQLException {
    String sql = "update accounts set balance = ? where id = ?";

    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql);) {

      statement.setDouble(1, balance);
      statement.setInt(2, account_id);

      statement.executeUpdate();

    } catch (SQLException e) {
      System.out.println("----------------------------------------------------");
      System.out.println("Error while updating balance from account with id: " + account_id + ".\n");
      e.printStackTrace();
      System.out.println("---------------------------------------------------- \n");
    }
  }

}
