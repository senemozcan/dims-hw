package com.example.dimshw.Controller;
import com.example.dimshw.Model.Task;
import com.example.dimshw.Services.TaskNotFoundException;
import com.example.dimshw.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;
    @GetMapping("/taskindex")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/Task")
    public String showTaskList(Model model) {
        List<Task> taskList = taskService.listAll();
        model.addAttribute("taskList", taskList);
        return "Task/TaskList";
    }

    @PostMapping("/Task/save")
    public String saveTask(@ModelAttribute Task task, RedirectAttributes ra) {
        taskService.save(task);
        ra.addFlashAttribute("message", "The task has been saved successfully :)");
        return "redirect:/Task";
    }

    @GetMapping("/Task/create")
    public String showNewForm(Model model) {
        model.addAttribute("task", new Task());
        return "Task/TaskAdd";
    }

    @GetMapping("/Task/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            Task task = taskService.get(id);
            model.addAttribute("task", task);
        } catch (TaskNotFoundException e) {
            ra.addFlashAttribute("message", "Task not found :(");
            return "redirect:/Task";
        }
        return "Task/TaskEdit";
    }

    @PostMapping("/Task/edit/{id}")
    public String updateTask(@PathVariable("id") Long id, @ModelAttribute Task task, RedirectAttributes ra) {
        try {
            taskService.updateTask(id, task);
            ra.addFlashAttribute("message", "The task has been updated successfully :)");
        } catch (TaskNotFoundException e) {
            ra.addFlashAttribute("message", "Task not found :(");
        }
        return "redirect:/Task";
    }


    @GetMapping("/Task/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            taskService.delete(id);
            ra.addFlashAttribute("message", "The task with ID " + id + " has been deleted");
        } catch (TaskNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/Task";
    }
}
