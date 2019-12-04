package com.sample.persistence;

import com.sample.models.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements  ResultSetMapper<User>  {
    @Override
    public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new User(r.getString("id"),
                r.getString("name"),
                r.getString("email_id"),
                r.getString("phone_number"),
                r.getTimestamp("created_at"),
                r.getTimestamp("updated_at"));
    }
}
