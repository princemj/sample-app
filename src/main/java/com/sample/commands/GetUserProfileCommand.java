package com.sample.commands;

import com.sample.persistence.UserRepository;
import spark.Route;

public class GetUserProfileCommand {
    private UserRepository userRepository;

    public GetUserProfileCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Route get() {
        return (request, response) -> {
            String userId = request.queryParams("email_id");
            return userRepository.getUserByEmailId(userId);
        };
    }

}
