package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import model.Veterinario;
import exception.ColecaoException;

public class ColecaoDeVeterinarioImpl implements ColecaoDeVeterinario {

  private Connection conexao;

  public ColecaoDeVeterinarioImpl(Connection conexao) {
    this.conexao = conexao;
  }

  @Override
  public List<Veterinario> todos() throws ColecaoException {
    PreparedStatement comandoSql = null;
    ResultSet resultadoConsulta = null;
    List<Veterinario> listaVeterinarios = new ArrayList<>();
    try {
      String sql = "SELECT id, nome, crmv, salario FROM veterinario";
      comandoSql = this.conexao.prepareStatement(sql);
      resultadoConsulta = comandoSql.executeQuery();
      while (resultadoConsulta.next()) {
        Veterinario veterinario = new Veterinario(
            resultadoConsulta.getInt("id"),
            resultadoConsulta.getString("nome"),
            resultadoConsulta.getString("crmv"),
            resultadoConsulta.getDouble("salario"));
        listaVeterinarios.add(veterinario);
      }
    } catch (SQLException e) {
      throw new ColecaoException("Erro ao buscar todos os veterinários!", e);
    } finally {
      try {
        if (resultadoConsulta != null)
          resultadoConsulta.close();
        if (comandoSql != null)
          comandoSql.close();
      } catch (SQLException e) {
        throw new ColecaoException("Erro ao fechar ResultSet e PreparedStatement!", e);
      }
    }
    return listaVeterinarios;
  }

  @Override
  public Veterinario porId(int id) throws ColecaoException {
    PreparedStatement comandoSql = null;
    ResultSet resultadoConsulta = null;
    Veterinario veterinario = null;
    try {
      String sql = "SELECT id, nome, crmv, salario FROM veterinario WHERE id = ?";
      comandoSql = this.conexao.prepareStatement(sql);
      comandoSql.setInt(1, id);
      resultadoConsulta = comandoSql.executeQuery();
      if (resultadoConsulta.next()) {
        veterinario = new Veterinario(resultadoConsulta.getInt("id"), resultadoConsulta.getString("nome"),
            resultadoConsulta.getString("crmv"), resultadoConsulta.getDouble("salario"));
      }
    } catch (SQLException e) {
      throw new ColecaoException("Erro ao buscar o veterinário pelo id!", e);
    } finally {
      try {
        resultadoConsulta.close();
        comandoSql.close();
      } catch (SQLException e) {
        throw new ColecaoException("Erro ao fechar ResultSet e PreparedStatement!", e);
      }
    }

    return veterinario;
  }

  @Override
  public List<Veterinario> porNome(String nome) throws ColecaoException {
    PreparedStatement comandoSql = null;
    ResultSet resultadoConsulta = null;
    List<Veterinario> listaVeterinarios = null;
    try {
      listaVeterinarios = new ArrayList<Veterinario>();
      String sql = "SELECT id, nome, crmv, salario FROM veterinario WHERE nome LIKE ?";
      comandoSql = this.conexao.prepareStatement(sql);
      comandoSql.setString(1, "%" + nome + "%");
      resultadoConsulta = comandoSql.executeQuery();
      while (resultadoConsulta.next()) {
        Veterinario veterinario = new Veterinario(resultadoConsulta.getInt("id"), resultadoConsulta.getString("nome"),
            resultadoConsulta.getString("crmv"), resultadoConsulta.getDouble("salario"));
        listaVeterinarios.add(veterinario);
      }
    } catch (SQLException e) {
      throw new ColecaoException("Erro ao buscar os veterinários pelo nome!", e);
    } finally {
      try {
        resultadoConsulta.close();
        comandoSql.close();
      } catch (SQLException e) {
        throw new ColecaoException("Erro ao fechar ResultSet e PreparedStatement!", e);
      }
    }

    return listaVeterinarios;
  }

  @Override
  public Veterinario porCrmv(String crmv) throws ColecaoException {
    PreparedStatement comandoSql = null;
    ResultSet resultadoConsulta = null;
    Veterinario veterinario = null;
    try {
      String sql = "SELECT id, nome, crmv, salario FROM veterinario WHERE crmv = ?";
      comandoSql = this.conexao.prepareStatement(sql);
      comandoSql.setString(1, crmv);
      resultadoConsulta = comandoSql.executeQuery();
      if (resultadoConsulta.next()) {
        veterinario = new Veterinario(resultadoConsulta.getInt("id"), resultadoConsulta.getString("nome"),
            resultadoConsulta.getString("crmv"), resultadoConsulta.getDouble("salario"));
      }
    } catch (SQLException e) {
      throw new ColecaoException("Erro ao buscar o veterinário pelo crmv!", e);
    } finally {
      try {
        resultadoConsulta.close();
        comandoSql.close();
      } catch (SQLException e) {
        throw new ColecaoException("Erro ao fechar ResultSet e PreparedStatement!", e);
      }
    }

    return veterinario;
  }

  @Override
public void inserir(Veterinario veterinario) throws ColecaoException {
    PreparedStatement comandoSql = null;
    ResultSet resultadoConsulta = null;
    try {
        String sql = "INSERT INTO veterinario (nome, crmv, salario) VALUES (?, ?, ?)";
        // Passando Statement.RETURN_GENERATED_KEYS para obter as chaves geradas
        comandoSql = this.conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        comandoSql.setString(1, veterinario.getNome());
        comandoSql.setString(2, veterinario.getCrmv());
        comandoSql.setDouble(3, veterinario.getSalario());
        comandoSql.execute();

        // Obtendo a chave gerada
        resultadoConsulta = comandoSql.getGeneratedKeys();
        if (resultadoConsulta.next()) {
            veterinario.setId(resultadoConsulta.getInt(1)); // Definindo o ID gerado
        }
    } catch (SQLException e) {
        throw new ColecaoException("Erro ao inserir o veterinário!", e);
    } finally {
        try {
            if (resultadoConsulta != null) resultadoConsulta.close();
            if (comandoSql != null) comandoSql.close();
        } catch (SQLException e) {
            throw new ColecaoException("Erro ao fechar recursos!", e);
        }
    }
}


  @Override
  public void alterar(Veterinario veterinario) throws ColecaoException {
    PreparedStatement comandoSql = null;
    try {
      String sql = "UPDATE veterinario SET nome = ?, crmv = ?, salario = ? WHERE id = ?";
      comandoSql = this.conexao.prepareStatement(sql);
      comandoSql.setString(1, veterinario.getNome());
      comandoSql.setString(2, veterinario.getCrmv());
      comandoSql.setDouble(3, veterinario.getSalario());
      comandoSql.setInt(4, veterinario.getId());
      comandoSql.execute();
    } catch (SQLException e) {
      throw new ColecaoException("Erro ao alterar o veterinário!", e);
    } finally {
      try {
        comandoSql.close();
      } catch (SQLException e) {
        throw new ColecaoException("Erro ao fechar PreparedStatement!", e);
      }
    }
  }

  @Override
  public void excluir(int id) throws ColecaoException {
    PreparedStatement comandoSql = null;
    try {
      String sql = "DELETE FROM veterinario WHERE id = ?";
      comandoSql = this.conexao.prepareStatement(sql);
      comandoSql.setInt(1, id);
      comandoSql.execute();
    } catch (SQLException e) {
      throw new ColecaoException("Erro ao excluir o veterinário!", e);
    } finally {
      try {
        if (comandoSql != null) {
          comandoSql.close();
        }
      } catch (SQLException e) {
        throw new ColecaoException("Erro ao fechar PreparedStatement!", e);
      }
    }
  }
}
