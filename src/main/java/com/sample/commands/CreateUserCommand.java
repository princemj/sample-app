package com.sample.commands;

import com.sample.models.CreateUserRequest;
import com.sample.models.User;
import com.sample.persistence.UserRepository;
import com.sample.util.ObjectMapper;
import spark.Route;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public class CreateUserCommand {
    private UserRepository userRepository;

    public CreateUserCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Route login() {
        return (request, response) -> {
            CreateUserRequest createUserRequest = new ObjectMapper<CreateUserRequest>().map(request.body(), CreateUserRequest.class);
            Timestamp now = Timestamp.from(Instant.now());
            User user = userRepository.getUserByEmailId(createUserRequest.getEmailId());
            if (user != null) return user;
            user = new User(UUID.randomUUID().toString(),
                    createUserRequest.getName(),
                    createUserRequest.getEmailId(),
                    createUserRequest.getPhoneNumber(),
                    now, now);
            userRepository.create(user);
            return user;
        };
    }
}
