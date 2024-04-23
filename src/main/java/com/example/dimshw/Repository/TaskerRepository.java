package com.example.dimshw.Repository;
import com.example.dimshw.Model.Tasker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskerRepository extends CrudRepository<Tasker, Long> {
    Long countById(Long id);

    Tasker getById(int id);
}
