package com.example.dimshw.Model;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<Tasker> taskers = new HashSet<>();

    public Task() {
    }

    public Task(String name, String description, Set<Tasker> orders) {
        this.name = name;
        this.description = description;
        this.taskers = taskers;
    }


    public Set<Tasker> getOrders() {
        return taskers;
    }

    public void setOrders(Set<Tasker> orders) {
        this.taskers = orders;
    }
}
