package com.example.dimshw.Model;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import com.example.dimshw.Model.Tasker;
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name;
    private String lastname;
    private String department;

    @OneToMany(mappedBy = "employee" , cascade = CascadeType.ALL)
    private Set<Tasker> taskers = new HashSet<>();

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Set<Tasker> getTaskers() {
        return taskers;
    }

    public void setTaskers(Set<Tasker> taskers) {
        this.taskers = taskers;
    }
}

