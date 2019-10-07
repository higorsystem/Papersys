package br.com.papersys.api.dao;

import br.com.papersys.api.model.Papelaria;
import java.util.List;

public interface PapelariaDAO {

  List<Papelaria> buscarTodos();

  Papelaria buscarPorId(long id);

  String deletarPorId(long id);

  Papelaria cadastrar(Papelaria papelaria);

  Papelaria editar(long id, Papelaria papelaria);
}
