package br.com.papersys.api.util.helpers;

import br.com.papersys.api.PaperSysConfiguration;
import br.com.papersys.api.model.Papelaria;
import br.com.papersys.api.resources.PapelariaResource;
import br.com.papersys.api.service.PapelariaService;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import java.util.Arrays;
import java.util.List;

public class DropwizardHelper {

  public static HibernateBundle<PaperSysConfiguration> criarHibernateBundle() {
    return new HibernateBundle<PaperSysConfiguration>(Papelaria.class) {
      @Override
      public PooledDataSourceFactory getDataSourceFactory(PaperSysConfiguration configuration) {
        return configuration.getDataSourceFactory();
      }
    };
  }

  public static List<Object> endpoints(
      PaperSysConfiguration configuration, PapelariaService service) {
    // TODO incluir novos Endpoints aqui!
    Object[] endpoints = {new PapelariaResource(service)};

    return Arrays.asList(endpoints);
  }
}
