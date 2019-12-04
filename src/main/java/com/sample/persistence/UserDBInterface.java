package com.sample.persistence;

import com.sample.models.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.sql.Timestamp;

@RegisterMapper(com.sample.persistence.UserMapper.class)
public interface UserDBInterface {

    @SqlUpdate("INSERT INTO users (id, name, email_id, phone_number, created_at, updated_at) VALUES (:id, :name, :emailId, :phoneNumber, :createdAt, :updatedAt);")
    int create(@Bind("id") String id, @Bind("name") String name, @Bind("emailId") String emailId, @Bind("phoneNumber") String phoneNumber,
               @Bind("createdAt") Timestamp createdAt, @Bind("updatedAt") Timestamp updatedAt);

    @SqlQuery("SELECT id, name, email_id, phone_number, created_at, updated_at FROM users WHERE email_id=:emailId limit 1;")
    User getUserByEmailId(@Bind("emailId") String emailId);
}
