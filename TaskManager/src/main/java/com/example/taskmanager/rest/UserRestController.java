package com.example.taskmanager.rest;

import com.example.taskmanager.Entity.User;
import com.example.taskmanager.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private CrudService crudService;

    @Autowired
    public UserRestController(@Qualifier("userServiceImplementation") CrudService theCrudService) {
        crudService = theCrudService;
    }

    // expose "/employees" and return list of employees
    @GetMapping("/users")
    public List<User> findAll() {
        return crudService.findAll();
    }

    // add mapping for GET /employees/{employeeId}

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId) {

        User theUser = (User) crudService.findById(userId);

        if (theUser == null) {
            throw new RuntimeException("Employee id not found - " + userId);
        }

        return theUser;
    }

    // add mapping for POST /employees - add new employee

    @PostMapping("/users")
    public User addUser(@RequestBody User theUser) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        theUser.setId(0);

        crudService.save(theUser);

        return theUser;
    }

    // add mapping for PUT /employees - update existing employee

    @PutMapping("/users")
    public User updateUser(@RequestBody User theUser) {

        crudService.save(theUser);

        return theUser;
    }

    // add mapping for DELETE /employees/{employeeId} - delete employee

    @DeleteMapping("/users/{userId}")
    public String deleteEmployee(@PathVariable int userId) {

        User tempUser = (User) crudService.findById(userId);

        // throw exception if null

        if (tempUser == null) {
            throw new RuntimeException("User id not found - " + userId);
        }

        crudService.deleteById(userId);

        return "Deleted User id - " + userId;
    }





}
