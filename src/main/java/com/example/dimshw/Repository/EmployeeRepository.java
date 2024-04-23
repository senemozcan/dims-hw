package com.example.dimshw.Repository;
import com.example.dimshw.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    public Long countById(Long id);
    Employee findByName(String name);

    Employee getById(int id);

   // boolean isPresent();
}
