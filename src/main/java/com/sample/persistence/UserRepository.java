package com.sample.persistence;

import com.sample.models.User;
import com.zaxxer.hikari.HikariDataSource;

public class UserRepository extends Repository<UserDBInterface> {

    public UserRepository(HikariDataSource dataSource) {
        super(dataSource);
    }

    public int create(User user) {
        return withDBInterface(UserDBInterface.class, dbInterface -> dbInterface.create(
                user.getId(),
                user.getName(),
                user.getEmailId(),
                user.getPhoneNumber(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        ));
    }

    public User getUserByEmailId(String emailId) {
        return withDBInterface(UserDBInterface.class, dbInterface -> dbInterface.getUserByEmailId(emailId));
    }

}
