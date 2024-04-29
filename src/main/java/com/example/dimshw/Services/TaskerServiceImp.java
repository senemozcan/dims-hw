package com.example.dimshw.Services;
import com.example.dimshw.Exceptions.EmployeeNotFoundException;
import com.example.dimshw.Exceptions.TaskNotFoundException;
import com.example.dimshw.Exceptions.TaskerNotFoundException;
import com.example.dimshw.Model.Employee;
import com.example.dimshw.Model.Tasker;
import com.example.dimshw.Model.Task;
import com.example.dimshw.Repository.EmployeeRepository;
import com.example.dimshw.Repository.TaskerRepository;
import com.example.dimshw.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dimshw.DTOs.Taskerview;
import java.util.List;
import java.util.ArrayList;

import java.util.Optional;


@Service
public class TaskerServiceImp implements TaskerService {
    @Autowired
    private TaskerRepository taskerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public List<Tasker> getAllTaskers() {
        Iterable<Tasker> taskerIterable = taskerRepository.findAll();
        List<Tasker> taskerList = new ArrayList<>();
        taskerIterable.forEach(taskerList::add);
        return taskerList;
    }


    @Override
    public void saveTasker(Tasker tasker) {
        taskerRepository.save(tasker);
    }

    @Override
    public Tasker createTasker(Taskerview taskerview){
        Task task = taskRepository.findByName(taskerview.getTaskName());
        Employee employee = employeeRepository.findByName(taskerview.getEmployeeName());
        Tasker tasker = new Tasker();
        tasker.setId(tasker.getId());
        tasker.setDate(tasker.getDate());
        tasker.setEmployee(employee);
        tasker.setTask(task);
        return taskerRepository.save(tasker);

    }
    public Tasker getTaskerById(Long id) {
        Optional<Tasker> optionalTasker = taskerRepository.findById(id);
        if (optionalTasker.isPresent()) {
            Tasker tasker = optionalTasker.get();
            Taskerview taskerview = new Taskerview();
            taskerview.setId(tasker.getId());
            taskerview.setDate(tasker.getDate());
            taskerview.setTaskId(tasker.getTask().getId());
            taskerview.setEmployeeId(tasker.getEmployee().getId());
            return taskerRepository.save(tasker);
        } else {

            return null;
        }
    }

    public void updateTasker(Tasker tasker) throws TaskerNotFoundException, TaskNotFoundException, EmployeeNotFoundException {

        Optional<Tasker> optionalTasker = taskerRepository.findById(tasker.getId());


        if (optionalTasker.isPresent()) {
            Tasker existingTasker = optionalTasker.get();
            existingTasker.setDate(tasker.getDate());


            Task task = null;
            task = taskRepository.findByName(tasker.getTask().getName());
            if(task == null)
            {
                throw new TaskNotFoundException("task %s not found" .formatted(tasker.getTask().getName()));
            }


            Employee employee = null;
            employee = employeeRepository.findByName(tasker.getEmployee().getName());

            if(employee == null)
            {
                throw new EmployeeNotFoundException("employee %s not found" .formatted(tasker.getEmployee().getName()));
            }
            existingTasker.setTask(task);
            existingTasker.setEmployee(employee);


            taskerRepository.save(existingTasker);
        } else {

            throw new TaskerNotFoundException("Tasker not found with id: " + tasker.getId());
        }
    }








    @Override
    public void deleteTasker(Long id) throws TaskerNotFoundException {
        if (taskerRepository.existsById(id)) {
            taskerRepository.deleteById(id);
        } else {
            throw new TaskerNotFoundException("Tasker not found with id: " + id);
        }
    }

}
