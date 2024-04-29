package com.example.dimshw.Services;

import com.example.dimshw.Exceptions.TaskNotFoundException;
import com.example.dimshw.Model.Task;
import com.example.dimshw.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> listAll() {
        return (List<Task>) taskRepository.findAll();
    }

    public Task get(Long id) throws TaskNotFoundException {
        Optional<Task> result = taskRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new TaskNotFoundException("Could not find any task with ID " + id);

    }
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    public Task findByName2(String name) {
        return taskRepository.findByName(name);
    }
    public String findTaskNameById(Long id) throws TaskNotFoundException {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            return task.getName();
        } else {
            throw new TaskNotFoundException("Could not find any task with ID " + id);
        }
    }

    public void save(Task task) {
        taskRepository.save(task);
    }

    public void updateTask(Long id, Task task) throws TaskNotFoundException {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setName(task.getName());
            existingTask.setDescription(task.getDescription());
            taskRepository.save(existingTask);
        } else {
            throw new TaskNotFoundException("Could not find any task with ID " + id);
        }
    }

    public void delete(Long id) throws TaskNotFoundException {
        Long count = taskRepository.countById(id);
        if (count == null || count == 0) {
            throw new TaskNotFoundException("Could not find any product with ID " + id);
        }
        taskRepository.deleteById(id);
    }
}
