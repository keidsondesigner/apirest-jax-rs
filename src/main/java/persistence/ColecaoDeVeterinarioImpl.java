package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import model.Veterinario;


public class ColecaoDeVeterinarioImpl implements ColecaoDeVeterinario {

  private Connection conexao;

  public ColecaoDeVeterinarioImpl(Connection conexao) {
    this.conexao = conexao;
  }

  @Override
  public List<Veterinario> todos() throws ColecaoException {
    PreparedStatement comandoSql = null;
    ResultSet resultadoConsulta = null;
    List<Veterinario> listaVeterinarios = null;
    try {
      listaVeterinarios = new ArrayList<Veterinario>();
      String sql = "SELECT ID, NOME, CRMV, SALARIO FROM VETERINARIO";
      comandoSql = this.conexao.prepareStatement(sql);
      resultadoConsulta = comandoSql.executeQuery();
      while (resultadoConsulta.next()) {
        Veterinario veterinario = new Veterinario(resultadoConsulta.getInt("ID"), resultadoConsulta.getString("NOME"), resultadoConsulta.getString("CRMV"), resultadoConsulta.getDouble("SALARIO"));
        listaVeterinarios.add(veterinario);
      }
    } catch (SQLException e) {
      throw new ColecaoException("Erro ao buscar todos os veterinários!", e);
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
  public Veterinario porId(int id) throws ColecaoException {
    PreparedStatement comandoSql = null;
    ResultSet resultadoConsulta = null;
    Veterinario veterinario = null;
    try {
      String sql = "SELECT ID, NOME, CRMV, SALARIO FROM VETERINARIO WHERE ID = ?";
      comandoSql = this.conexao.prepareStatement(sql);
      comandoSql.setInt(1, id);
      resultadoConsulta = comandoSql.executeQuery();
      if (resultadoConsulta.next()) {
        veterinario = new Veterinario(resultadoConsulta.getInt("ID"), resultadoConsulta.getString("NOME"), resultadoConsulta.getString("CRMV"), resultadoConsulta.getDouble("SALARIO"));
      }
    } catch (SQLException e) {
      throw new ColecaoException("Erro ao buscar o veterinário pelo ID!", e);
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
      String sql = "SELECT ID, NOME, CRMV, SALARIO FROM VETERINARIO WHERE NOME LIKE ?";
      comandoSql = this.conexao.prepareStatement(sql);
      comandoSql.setString(1, "%"+nome+"%");
      resultadoConsulta = comandoSql.executeQuery();
      while (resultadoConsulta.next()) {
        Veterinario veterinario = new Veterinario(resultadoConsulta.getInt("ID"), resultadoConsulta.getString("NOME"), resultadoConsulta.getString("CRMV"), resultadoConsulta.getDouble("SALARIO"));
        listaVeterinarios.add(veterinario);
      }
    } catch (SQLException e) {
      throw new ColecaoException("Erro ao buscar os veterinários pelo nome!", e);
    } finally {
      try {
        resultadoConsulta.close();
        comandoSql.close();
      } catch (SQLException e)  {
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
      String sql = "SELECT ID, NOME, CRMV, SALARIO FROM VETERINARIO WHERE CRMV = ?";
      comandoSql = this.conexao.prepareStatement(sql);
      comandoSql.setString(1, crmv);
      resultadoConsulta = comandoSql.executeQuery();
      if (resultadoConsulta.next()) {
        veterinario = new Veterinario(resultadoConsulta.getInt("ID"), resultadoConsulta.getString("NOME"), resultadoConsulta.getString("CRMV"), resultadoConsulta.getDouble("SALARIO"));
      }
    } catch (SQLException e) {
      throw new ColecaoException("Erro ao buscar o veterinário pelo CRMV!", e);
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
      String sql = "INSERT INTO VETERINARIO (NOME, CRMV, SALARIO) VALUES (?, ?, ?)";
      comandoSql = this.conexao.prepareStatement(sql);
      comandoSql.setString(1, veterinario.getNome());
      comandoSql.setString(2, veterinario.getCrmv());
      comandoSql.setDouble(3, veterinario.getSalario());
      comandoSql.execute();
      resultadoConsulta = comandoSql.getGeneratedKeys();
      if (resultadoConsulta.next()) {
        veterinario.setId(resultadoConsulta.getInt(1));
      }
    } catch (SQLException e) {
      throw new ColecaoException("Erro ao inserir o veterinário!", e);
    } finally {
      try {
        comandoSql.close();
      } catch (SQLException e) {
        throw new ColecaoException("Erro ao fechar PreparedStatement!", e);
      }
    }
  }

  @Override
  public void alterar(Veterinario veterinario) throws ColecaoException {
    PreparedStatement comandoSql = null;
    try {
      String sql = "UPDATE VETERINARIO SET NOME = ?, CRMV = ?, SALARIO = ? WHERE ID = ?";
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
  public void excluir(Veterinario veterinario) throws ColecaoException {
    PreparedStatement comandoSql = null;
    try {
      String sql = "DELETE FROM VETERINARIO WHERE ID = ?";
      comandoSql = this.conexao.prepareStatement(sql);
      comandoSql.setInt(1, veterinario.getId());
      comandoSql.execute();
    } catch (SQLException e) {
      throw new ColecaoException("Erro ao excluir o veterinário!", e);
    } finally {
      try {
        comandoSql.close();
      } catch (SQLException e) {
        throw new ColecaoException("Erro ao fechar PreparedStatement!", e);
      }
    }
  }

}
