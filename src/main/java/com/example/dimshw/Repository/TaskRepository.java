package com.example.dimshw.Repository;
import com.example.dimshw.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TaskRepository extends CrudRepository<Task, Long> {
    Long countById(Long id);
    Task findByName(String name);

}
