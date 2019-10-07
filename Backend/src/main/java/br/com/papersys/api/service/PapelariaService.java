package br.com.papersys.api.service;

import br.com.papersys.api.model.Papelaria;
import java.util.List;

public interface PapelariaService {

  List<Papelaria> buscarTodos();

  Papelaria buscarPorId(long id);

  String deletarPorId(long id);

  Papelaria editar(long id, Papelaria papelaria);

  Papelaria cadastrar(Papelaria papelaria);
}
