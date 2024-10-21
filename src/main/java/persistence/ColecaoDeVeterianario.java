package persistence;

import java.util.List;

public interface ColecaoDeVeterianario extends Colecao<Veterinario> {

  public List<Veterinario> porNome(String nome) throws ColecaoException;
  public Veterinario porCrmv(String crmv) throws ColecaoException;

}
