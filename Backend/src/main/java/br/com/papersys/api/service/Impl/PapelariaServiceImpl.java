package br.com.papersys.api.service.Impl;

import br.com.papersys.api.dao.PapelariaDAO;
import br.com.papersys.api.model.Papelaria;
import br.com.papersys.api.service.PapelariaService;
import java.util.List;

public class PapelariaServiceImpl implements PapelariaService {

  private PapelariaDAO dao;

  public PapelariaServiceImpl(PapelariaDAO dao) {
    this.dao = dao;
  }

  @Override
  public List<Papelaria> buscarTodos() {
    return dao.buscarTodos();
  }

  @Override
  public Papelaria buscarPorId(long id) {
    return dao.buscarPorId(id);
  }

  @Override
  public String deletarPorId(long id) {
    return dao.deletarPorId(id);
  }

  @Override
  public Papelaria editar(long id, Papelaria papelaria) {
    return dao.editar(id, papelaria);
  }

  @Override
  public Papelaria cadastrar(Papelaria papelaria) {
    return dao.cadastrar(papelaria);
  }
}
