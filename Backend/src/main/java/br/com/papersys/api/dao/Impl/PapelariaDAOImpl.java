package br.com.papersys.api.dao.Impl;

import br.com.papersys.api.dao.PapelariaDAO;
import br.com.papersys.api.model.Papelaria;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import org.hibernate.SessionFactory;

public class PapelariaDAOImpl extends AbstractDAO<Papelaria> implements PapelariaDAO {
  public PapelariaDAOImpl(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public List<Papelaria> buscarTodos() {
    return (List<Papelaria>) currentSession().createCriteria(Papelaria.class).list();
  }

  @Override
  public Papelaria buscarPorId(long id) {
    return currentSession().get(Papelaria.class, id);
  }

  @Override
  public String deletarPorId(long id) {
    var objPapelaria = get(id);

    if (objPapelaria == null) {
      return "Não foi possível remover o item com identificador: " + id;
    }

    currentSession().delete(objPapelaria);
    return "Item removido com sucesso!!!";
  }

  @Override
  public Papelaria cadastrar(Papelaria papelaria) {
    return persist(papelaria);
  }

  @Override
  public Papelaria editar(long id, Papelaria papelaria) {
    var objPapelaria = get(id);
    if (objPapelaria != null) {
      papelaria.setId(id);
      currentSession().saveOrUpdate(papelaria);
      return papelaria;
    }
    return null;
  }
}
