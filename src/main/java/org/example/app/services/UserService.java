package org.example.app.services;

import org.example.app.repository.UserRepository;
import org.example.web.dto.RegistrationForm;
import org.example.web.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.retreiveAll();
    }

    public void saveUser(User user) {
        if (!user.getLogin().isEmpty() && !user.getPassword().isEmpty()){
            userRepo.store(user);
        }
    }

    public boolean findUser(RegistrationForm registrationForm){
        if (registrationForm.getUsername().isEmpty() || registrationForm.getPassword().isEmpty()){
            return true;
        }
        return userRepo.findUserByName(registrationForm);
    }

    public boolean removeUserById(Integer userIdToRemove) {
        return userRepo.removeItemById(userIdToRemove);
    }
}
