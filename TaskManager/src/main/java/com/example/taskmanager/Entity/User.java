package com.example.taskmanager.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
public class User {

    //define & Annotate  the fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private int age;

         //Generate The relationships
    @OneToMany(mappedBy="instructor",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Task> tasks;

    //Generate Constructors

      //No-Argument Constructor

    public User() {
    }

    public User(String name, String email, String password, int age) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
    }


    //Generate Getter and setter for each attribute


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

  // Bidirectional RelationShip
    public void add(Task tempTask) {

        if (tasks == null) {
            tasks = new ArrayList<>();
        }

        tasks.add(tempTask);

        tempTask.setUser(this);
    }

 //To string method

    @Override
    public String toString(){
        return "User [id =" + id +" ,Name =" +name +" ,age =" +
                age + " ,Email =" + email + " ,Password" + password +" ]";
    }
}
