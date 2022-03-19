package com.example.taskmanager.service;

import com.example.taskmanager.Entity.User;
import com.example.taskmanager.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements CrudService<User> {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImplementation (UserRepository theUserRepository){
        userRepository=theUserRepository;
    }
    @Override
    public List<User> findAll() {

       return  userRepository.findAll();
    }

    @Override
    public User findById(int theId) {
        Optional<User> result = userRepository.findById(theId);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find User id - " + theId);
        }

        return theUser;
    }

    @Override
    public void save(User theUser) {

        userRepository.save(theUser);
    }

    @Override
    public void deleteById(int theId) {

        userRepository.deleteById(theId);
    }
}
