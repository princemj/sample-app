package com.sample.servers;

import com.sample.commands.*;
import com.sample.util.ApplicationConfiguration;
import com.sample.util.EnvironmentConfiguration;
import com.sample.persistence.UserRepository;
import com.sample.util.JsonTransformer;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.Liquibase;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.resource.FileSystemResourceAccessor;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static spark.Spark.*;

public class SampleServer {

    public static void main(String[] args) {

        ApplicationConfiguration appConfig = new EnvironmentConfiguration();
        String appPort = appConfig.getValueAsString("APP_SERVICE_PORT");
        HikariDataSource hikariDataSource = getHikariDataSource(appConfig);
        System.out.println("Running Server at " + appPort);
        port(Integer.parseInt(appPort));

//      Persistence
        UserRepository userRepository = new UserRepository(hikariDataSource);

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
        runMigration(pgSimpleDataSource);
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDataSource(pgSimpleDataSource);
        hikariDataSource.setMaximumPoolSize(dbPoolSize);
        hikariDataSource.setPoolName(dbPoolName);
        return hikariDataSource;
    }

    private static void runMigration(PGSimpleDataSource pgSimpleDataSource) {
        Liquibase liquibase = null;
        try {
            System.out.println("Running Migrations");
            DatabaseChangeLog dbChangeLog = new DatabaseChangeLog();
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(pgSimpleDataSource.getConnection()));
            liquibase = new Liquibase(dbChangeLog, new FileSystemResourceAccessor(), database);
            liquibase.update("Run Migration");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
