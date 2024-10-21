package persistence;

import exception.ColecaoException;
import java.util.List;

public interface Colecao<T> {

  public List<T> todos() throws ColecaoException;
  public T porId(int id) throws ColecaoException;
  public List<T> porNome(String nome) throws ColecaoException;
  public T porCrmv(String crmv) throws ColecaoException;

  public void inserir(T objeto) throws ColecaoException;
  public void alterar(T objeto) throws ColecaoException;
  public void excluir(int id) throws ColecaoException;
  
}
