package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

  public static Connection connect() {
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;

    try {
      connection = DriverManager.getConnection(db_file);
      System.out.println("-------------------------- \n");
      System.out.println("DataBase connected successfully!  \n");
      System.out.println("-------------------------- \n");
    } catch(SQLException e) {
      System.out.println("--------------------------");
      System.out.println("Error connecting DataBase: \n");
      e.printStackTrace();
      System.out.println("-------------------------- \n");
    }

    return connection;
  }

  public static void main(String[] args) {
    connect();
  }
}
