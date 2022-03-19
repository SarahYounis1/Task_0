package com.example.taskmanager.service;

import com.example.taskmanager.Entity.Task;
import com.example.taskmanager.dao.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImplementation implements CrudService<Task>{

    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImplementation(TaskRepository theTaskRepository) {
        taskRepository = theTaskRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(int theId) {
        Optional<Task> result = taskRepository.findById(theId);

        Task theTask = null;

        if (result.isPresent()) {

            theTask = result.get();
        }

        else {
            // we didn't find the Task
            throw new RuntimeException("Did not find task id - " + theId);
        }

        return theTask;

    }

    @Override
    public void save(Task theTask) {
     taskRepository.save(theTask);
    }

    @Override
    public void deleteById(int theId) {
        taskRepository.deleteById(theId);

    }
}
