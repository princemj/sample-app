package com.sample.persistence;

import com.zaxxer.hikari.HikariDataSource;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;

import java.util.function.Function;

public class Repository<T> {

    private HikariDataSource dataSource;

    public Repository(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected DBI getDBI() {
        return new DBI(dataSource);
    }

    protected T getDBInterface(Class<T> dBInterfaceClass) {
        DBI connection = getDBI();
        return connection.open(dBInterfaceClass);
    }

    protected <ReturnedType> ReturnedType withDBInterface(Class<T> dBInterfaceClass, Function<T, ReturnedType> sqlExecution) {
        T dbInterface = getDBInterface(dBInterfaceClass);
        try {
            return sqlExecution.apply(dbInterface);
        } catch (UnableToExecuteStatementException e) {
            throw e;
        } finally {
            getDBI().close(dbInterface);
        }
    }
}
