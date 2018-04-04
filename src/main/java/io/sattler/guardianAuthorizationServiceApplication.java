package io.sattler;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.sattler.db.UserPermissionDAO;
import io.sattler.resources.AuthorizationResource;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class guardianAuthorizationServiceApplication extends Application<guardianAuthorizationServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new guardianAuthorizationServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "guardianAuthorizationService";
    }

    @Override
    public void initialize(final Bootstrap<guardianAuthorizationServiceConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        new ResourceConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                        )
        );
    }

    @Override
    public void run(final guardianAuthorizationServiceConfiguration configuration,
                    final Environment environment) {
        try {
            final DBIFactory factory = new DBIFactory();
            final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");

            UserPermissionDAO userPermissionDAO = jdbi.onDemand(UserPermissionDAO.class);

            AuthorizationResource authorizationResource = new AuthorizationResource(userPermissionDAO);

            environment.jersey().register(authorizationResource);


        } catch (Exception e) {
            Logger log = LoggerFactory.getLogger(guardianAuthorizationServiceApplication.class);
            log.error(e.getLocalizedMessage());
            log.error(e.getMessage());
        }
    }

}
