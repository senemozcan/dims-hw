package com.example.dimshw.Services;
import com.example.dimshw.DTOs.Taskerview;
import com.example.dimshw.Exceptions.EmployeeNotFoundException;
import com.example.dimshw.Exceptions.TaskNotFoundException;
import com.example.dimshw.Exceptions.TaskerNotFoundException;
import  com.example.dimshw.Model.Tasker;

import java.util.List;

public interface TaskerService {
    List<Tasker> getAllTaskers();
    void saveTasker(Tasker tasker);

    Tasker createTasker(Taskerview taskerview);
    Tasker getTaskerById(Long id) throws TaskerNotFoundException;
    void updateTasker(Tasker tasker) throws TaskerNotFoundException, TaskNotFoundException, EmployeeNotFoundException;

    void deleteTasker(Long id) throws TaskerNotFoundException;
}
