package persistence;

import model.Veterinario;
import exception.ColecaoException;
import java.util.List;

public interface ColecaoDeVeterinario extends Colecao<Veterinario> {
  public List<Veterinario> todos() throws ColecaoException;
  public Veterinario porId(int id) throws ColecaoException;
  public List<Veterinario> porNome(String nome) throws ColecaoException;
  public Veterinario porCrmv(String crmv) throws ColecaoException;

}
