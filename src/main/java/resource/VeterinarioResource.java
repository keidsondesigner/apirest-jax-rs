package resource;

import java.sql.Connection;
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

import exception.ColecaoException;
import exception.ConexaoException;
import persistence.ColecaoDeVeterianario;

@Path("/veterinarios")
@Produces(MediaType.APPLICATION_JSON) // vai produzir um JSON no corpo(body) da requisição
public class VeterinarioResource {

  @GET
  @Path("/")
  public List<Veterinario> todos() throws ConexaoException, ColecaoException{
    Connection conexao = ConexaoSingleton.getConnection();
    ColecaoDeVeterianario colecaoVeterianario = new ColecaoDeVeterinarioImpl(conexao);
    return colecaoVeterianario.todos();
  }

  @GET
  @Path("/{id}")
  public Veterinario porId(@PathParam("id") int id) throws ConexaoException, ColecaoException{
    Connection conexao = ConexaoSingleton.getConnection();
    ColecaoDeVeterianario colecaoVeterianario = new ColecaoDeVeterinarioImpl(conexao);
    return colecaoVeterianario.porId(id);
  }

  @POST
  @Path("/")
  @Consumes(MediaType.APPLICATION_JSON) // vai consumir(receber) um JSON no corpo(body) da requisição
  public void inserir(Veterinario veterinario) throws ConexaoException, ColecaoException{
    Connection conexao = ConexaoSingleton.getConnection();
    ColecaoDeVeterianario colecaoVeterianario = new ColecaoDeVeterinarioImpl(conexao);
    colecaoVeterianario.inserir(veterinario);
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON) // vai consumir(receber) um JSON no corpo(body) da requisição
  public void alterar(@PathParam("id") int id, Veterinario veterinario) throws ConexaoException, ColecaoException{
    Connection conexao = ConexaoSingleton.getConnection();
    ColecaoDeVeterianario colecaoVeterianario = new ColecaoDeVeterinarioImpl(conexao);
    veterinario.setId(id);
    colecaoVeterianario.alterar(veterinario);
  }

  @DELETE
  @Path("/{id}")
  public void excluir(@PathParam("id") int id) throws ConexaoException, ColecaoException{
    Connection conexao = ConexaoSingleton.getConnection();
    ColecaoDeVeterianario colecaoVeterianario = new ColecaoDeVeterinarioImpl(conexao);
    colecaoVeterianario.excluir(id);
  }

}
