package com.example.taskmanager.rest;

import com.example.taskmanager.Entity.Task;
import com.example.taskmanager.Entity.User;
import com.example.taskmanager.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskRestController {

    private CrudService crudService;

    @Autowired
    public TaskRestController(@Qualifier("taskServiceImplementation") CrudService theCrudService) {
        crudService = theCrudService;
    }

    // expose "/employees" and return list of employees
    @GetMapping("/tasks")
    public List<Task> findAll() {
        return crudService.findAll();
    }

    // add mapping for GET /employees/{employeeId}

    @GetMapping("/tasks/{taskId}")
    public Task getTask(@PathVariable int taskId) {

        Task theTask = (Task) crudService.findById(taskId);

        if (theTask == null) {
            throw new RuntimeException("Employee id not found - " + taskId);
        }

        return theTask;
    }

    // add mapping for POST /employees - add new employee

    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task theTask) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        theTask.setId(0);

        crudService.save(theTask);

        return theTask;
    }

    // add mapping for PUT /employees - update existing employee

    @PutMapping("/tasks")
    public Task updateTask(@RequestBody Task theTask) {

        crudService.save(theTask);

        return theTask;
    }

    // add mapping for DELETE /employees/{employeeId} - delete employee

    @DeleteMapping("/tasks/{taskId}")
    public String deleteTask(@PathVariable int taskId) {

        User tempUser = (User) crudService.findById(taskId);

        // throw exception if null

        if (tempUser == null) {
            throw new RuntimeException("User id not found - " + taskId);
        }

        crudService.deleteById(taskId);

        return "Deleted User id - " + taskId;
    }





}
