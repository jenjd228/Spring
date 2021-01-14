package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.app.repository.UserRepository;
import org.example.web.dto.LoginForm;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private Logger logger = Logger.getLogger(LoginService.class);

    private final UserRepository userRepo;

    public LoginService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public boolean authenticate(LoginForm loginFrom) {
        logger.info("try auth with user-form: " + loginFrom);
        return userRepo.authenticate(loginFrom.getUsername(),loginFrom.getPassword());
    }
}
