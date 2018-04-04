package io.sattler;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class guardianAuthorizationServiceConfiguration extends Configuration {

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
    public void setDatabaseFactory(DataSourceFactory factory) { this.database = factory;}

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() { return database; }

}
