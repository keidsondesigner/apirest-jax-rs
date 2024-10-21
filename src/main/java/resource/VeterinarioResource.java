package resource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import exception.ColecaoException;
import exception.ConexaoException;
import model.Veterinario;
import persistence.ColecaoDeVeterinario;
import persistence.ColecaoDeVeterinarioImpl;
import util.ConexaoSingleton;

@Path("/veterinarios")
@Produces(MediaType.APPLICATION_JSON) // vai produzir um JSON no corpo(body) da requisição
@Provider
public class VeterinarioResource {

  @GET
  @Path("/")
  public List<Veterinario> todos() throws ConexaoException, ColecaoException {
    try {
      Connection conexao = ConexaoSingleton.getConnection();
      ColecaoDeVeterinario colecaoVeterianario = new ColecaoDeVeterinarioImpl(conexao);
      return colecaoVeterianario.todos();
    } catch (SQLException e) {
      throw new ConexaoException("Erro ao obter conexão com o banco de dados", e);
    }
  }

  @GET
  @Path("/{id}")
  public Veterinario porId(@PathParam("id") int id) throws ConexaoException, ColecaoException {
    try {
      Connection conexao = ConexaoSingleton.getConnection();
      ColecaoDeVeterinario colecaoVeterianario = new ColecaoDeVeterinarioImpl(conexao);
      return colecaoVeterianario.porId(id);
    } catch (SQLException e) {
      throw new ConexaoException("Erro ao obter conexão com o banco de dados", e);
    }
  }

  @POST
  @Path("/")
  @Consumes(MediaType.APPLICATION_JSON)
  public void inserir(Veterinario veterinario) throws ConexaoException, ColecaoException {
    try {
      Connection conexao = ConexaoSingleton.getConnection();
      ColecaoDeVeterinario colecaoVeterianario = new ColecaoDeVeterinarioImpl(conexao);
      colecaoVeterianario.inserir(veterinario);
    } catch (SQLException e) {
      throw new ConexaoException("Erro ao obter conexão com o banco de dados", e);
    }
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public void alterar(@PathParam("id") int id, Veterinario veterinario) throws ConexaoException, ColecaoException {
    try {
      Connection conexao = ConexaoSingleton.getConnection();
      ColecaoDeVeterinario colecaoVeterianario = new ColecaoDeVeterinarioImpl(conexao);
      veterinario.setId(id);
      colecaoVeterianario.alterar(veterinario);
    } catch (SQLException e) {
      throw new ConexaoException("Erro ao obter conexão com o banco de dados", e);
    }
  }

  @DELETE
  @Path("/{id}")
  public void excluir(@PathParam("id") int id) throws ConexaoException, ColecaoException {
    try {
      Connection conexao = ConexaoSingleton.getConnection();
      ColecaoDeVeterinario colecaoVeterianario = new ColecaoDeVeterinarioImpl(conexao);
      colecaoVeterianario.excluir(id);
    } catch (SQLException e) {
      throw new ConexaoException("Erro ao obter conexão com o banco de dados", e);
    }
  }

}
