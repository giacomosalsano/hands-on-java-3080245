package bank.repositories;

import bank.models.Account;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepository {

  private final Connection connection;

  public AccountRepository(Connection connection) {
    this.connection = connection;
  }

  // Busca a conta no banco e converte para nosso Modelo rico
  public Account findById(int id) {
    String sql = """
        SELECT id, type, balance
        FROM accounts
        WHERE id = ?
        """;

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, id);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          return new Account(
              rs.getInt("id"),
              rs.getString("type"),
              rs.getBigDecimal("balance") // JDBC já converte para BigDecimal!
          );
        }
      }
    } catch (SQLException e) {
      // Convertendo erro de infraestrutura para exceção não-verificada (Unchecked)
      throw new RuntimeException("Erro ao buscar a conta bancária", e);
    }

    return null;
  }

  // Atualiza apenas o saldo no banco de dados
  public void updateBalance(int id, BigDecimal newBalance) {
    String sql = """
        UPDATE accounts
        SET balance = ?
        WHERE id = ?
        """;

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setBigDecimal(1, newBalance); // Passando BigDecimal direto pro SQLite
      stmt.setInt(2, id);

      stmt.executeUpdate(); // Executa o comando de UPDATE
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao atualizar o saldo da conta", e);
    }
  }
}