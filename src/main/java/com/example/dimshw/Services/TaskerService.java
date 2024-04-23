package com.example.dimshw.Services;
import com.example.dimshw.DTOs.Taskerview;
import  com.example.dimshw.Model.Task;
import  com.example.dimshw.Model.Tasker;
import com.example.dimshw.Model.Employee;
import com.example.dimshw.Services.TaskerNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TaskerService {
    List<Tasker> getAllTaskers();
    void saveTasker(Tasker tasker);

    Tasker createTasker(Taskerview taskerview);
    Tasker getTaskerById(Long id) throws TaskerNotFoundException;
    void updateTasker(Tasker tasker) throws TaskerNotFoundException;

    void deleteTasker(Long id) throws TaskerNotFoundException;
}
