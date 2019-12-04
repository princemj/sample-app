package com.sample.servers;

import com.sample.commands.*;
import com.sample.util.ApplicationConfiguration;
import com.sample.util.EnvironmentConfiguration;
import com.sample.persistence.UserRepository;
import com.sample.util.JsonTransformer;
import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.ds.PGSimpleDataSource;

import static spark.Spark.*;

public class SampleServer {

    public static void main(String[] args) {

        ApplicationConfiguration appConfig = new EnvironmentConfiguration();
        String appPort = appConfig.getValueAsString("APP_SERVICE_PORT");
        System.out.println("Running Server at " + appPort);
        port(Integer.parseInt(appPort));

//      Persistence
        UserRepository userRepository = new UserRepository(getHikariDataSource(appConfig));

//      Commands
        JsonTransformer transformer = new JsonTransformer();
        get("/ping", (request, response) -> "pong");
        post("/user", new CreateUserCommand(userRepository).login(), transformer);
        get("/user", new GetUserProfileCommand(userRepository).get(), transformer);
    }

    private static HikariDataSource getHikariDataSource(ApplicationConfiguration config) {
        String dbUsername = config.getValueAsString("DB_USERNAME");
        String dbPassword = config.getValueAsString("DB_PASSWORD");
        Integer dbPoolSize = 5;
        Integer dbTimeout = config.getValueAsInt("DB_TIMEOUT", 1);
        String dbHost = config.getValueAsString("DB_HOST");
        String dbName = config.getValueAsString("DB_NAME");
        String dbPoolName = config.getValueAsString("DB_POOL_NAME");

        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        pgSimpleDataSource.setServerName(dbHost);
        pgSimpleDataSource.setDatabaseName(dbName);
        pgSimpleDataSource.setUser(dbUsername);
        pgSimpleDataSource.setPassword(dbPassword);
        pgSimpleDataSource.setSocketTimeout(dbTimeout);
        pgSimpleDataSource.setConnectTimeout(dbTimeout);

        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDataSource(pgSimpleDataSource);
        hikariDataSource.setMaximumPoolSize(dbPoolSize);
        hikariDataSource.setPoolName(dbPoolName);
        return hikariDataSource;
    }
}
