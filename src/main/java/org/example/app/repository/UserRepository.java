package org.example.app.repository;

import org.apache.log4j.Logger;
import org.example.web.dto.RegistrationForm;
import org.example.web.dto.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements ProjectRepository<User> {

    private final Logger logger = Logger.getLogger(UserRepository.class);
    private final List<User> repo = new ArrayList<>();

    @Override
    public List<User> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(User user) {
        user.setId(user.hashCode());
        logger.info("store new user: " + user);
        repo.add(user);
    }

    @Override
    public boolean removeItemById(Integer userIdToRemove) {
        for (User user : retreiveAll()) {
            if (user.getId().equals(userIdToRemove)) {
                logger.info("remove user completed: " + user);
                return repo.remove(user);
            }
        }
        return false;
    }

    public boolean findUserByName(RegistrationForm registrationForm){
        for (User user : repo){
            if (user.getLogin().equals(registrationForm.getUsername())){
                return true;
            }
        }
        User user = new User();
        user.setLogin(registrationForm.getUsername());
        user.setPassword(registrationForm.getPassword());
        store(user);
        return false;
    }

    public boolean authenticate(String userName,String password){
        for (User user : repo){
            if (user.getLogin().equals(userName)){
                if (user.getPassword().equals(password)){
                    return true;
                }
            }
        }
        return false;
    }
}
