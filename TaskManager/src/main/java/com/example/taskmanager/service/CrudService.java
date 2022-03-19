package com.example.taskmanager.service;

import java.util.List;

public interface CrudService <T>{
    public List<T> findAll();

    public T findById(int theId);

    public void save(T theEmployee);

    public void deleteById(int theId);
}
