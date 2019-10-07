package br.com.papersys.api;

import br.com.papersys.api.dao.Impl.PapelariaDAOImpl;
import br.com.papersys.api.dao.PapelariaDAO;
import br.com.papersys.api.service.Impl.PapelariaServiceImpl;
import br.com.papersys.api.service.PapelariaService;
import br.com.papersys.api.util.helpers.DropwizardHelper;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class PaperSysApiApplication extends Application<PaperSysConfiguration> {

  private static final String APPLICATION_NAME = "papersys-backend";
  private static final String BASE_URL = "/api/v1/*";

  public final HibernateBundle<PaperSysConfiguration> hibernateBundle;

  public PaperSysApiApplication() {
    hibernateBundle = DropwizardHelper.criarHibernateBundle();
  }

  @Override
  public String getName() {
    return APPLICATION_NAME;
  }

  @Override
  public void initialize(final Bootstrap<PaperSysConfiguration> bootstrap) {
    bootstrap.setConfigurationSourceProvider(
        new SubstitutingSourceProvider(
            bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));

    bootstrap.addBundle(hibernateBundle);
    bootstrap.addBundle(
        new SwaggerBundle<PaperSysConfiguration>() {
          @Override
          protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
              PaperSysConfiguration configuration) {
            configuration.swaggerBundleConfiguration.setTitle("PaperSys API - Documentação");
            configuration.swaggerBundleConfiguration.setDescription(
                "API de controle de estoque da papelaria.");
            configuration.swaggerBundleConfiguration.setVersion("v1");
            configuration.swaggerBundleConfiguration.setUriPrefix(BASE_URL.replace("/*", ""));

            return configuration.swaggerBundleConfiguration;
          }
        });
  }

  public static void main(String[] args) throws Exception {
    new PaperSysApiApplication().run(args);
  }

  @Override
  public void run(final PaperSysConfiguration configuration, final Environment environment) {

    PapelariaDAO dao = new PapelariaDAOImpl(hibernateBundle.getSessionFactory());
    PapelariaService service = new PapelariaServiceImpl(dao);
    var jersey = environment.jersey();

    jersey.setUrlPattern(BASE_URL);
    DropwizardHelper.endpoints(configuration, service).forEach(jersey::register);
  }
}
