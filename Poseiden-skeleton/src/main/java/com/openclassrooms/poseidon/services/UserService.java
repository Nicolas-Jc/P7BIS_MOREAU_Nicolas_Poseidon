package com.openclassrooms.poseidon.services;

import com.openclassrooms.poseidon.models.UserModel;
import com.openclassrooms.poseidon.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final Logger logger = LogManager.getLogger("UserService");

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getAllUsers() {
        logger.info("Find All Users");
        return userRepository.findAll();
    }

    public UserModel getUserById(int id) {
        logger.info("Find User By Id");
        return userRepository.findById(id);
    }

    public boolean checkIfUserExistsById(int id) {
        logger.info("Check if User exists ById");
        return userRepository.existsById(id);
    }

    public void saveUser(UserModel user) {
        userRepository.save(user);
        logger.info("User saved in UserList");
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
        logger.info("Delete User ById");
    }

    public UserModel getUserByEmail(String username) {
        logger.info("Find User by email");
        return userRepository.findByUsername(username);
    }

    public boolean checkIfUserExistsByUsername(String username) {
        logger.info("Check if User exists by email");
        return userRepository.existsByUsername(username);
    }


}
