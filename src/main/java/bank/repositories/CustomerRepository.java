package bank.repositories;

import bank.models.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepository {

  private final Connection connection;

  public CustomerRepository(Connection connection) {
    this.connection = connection;
  }

  public Customer findByUsername(String username) {
    String sql = """
        SELECT id, name, username, password, account_id
        FROM customers
        WHERE username = ?
        """;

    try (PreparedStatement statement = connection.prepareStatement(sql)) {

      statement.setString(1, username);

      try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          return new Customer(
              resultSet.getInt("id"),
              resultSet.getString("name"),
              resultSet.getString("username"),
              resultSet.getString("password"),
              resultSet.getInt("account_id"));
        }
      }
    } catch (SQLException e) {
      // TODO: Handle exception appropriately, possibly logging the error and
      // rethrowing a custom exception
      throw new RuntimeException("Erro de banco de dados ao buscar cliente", e);
    }

    return null;
  }
}