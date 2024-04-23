package com.example.dimshw.Controller;

import com.example.dimshw.DTOs.EmployeeTaskerCreateDTO;
import com.example.dimshw.DTOs.TaskTaskerCreateDTO;
import com.example.dimshw.DTOs.TaskerCreateDTO;
import com.example.dimshw.Model.Tasker;
import com.example.dimshw.Model.Task;
import com.example.dimshw.Repository.EmployeeRepository;
import com.example.dimshw.Repository.TaskerRepository;
import com.example.dimshw.Repository.TaskRepository;
import com.example.dimshw.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.dimshw.DTOs.Taskerview;
import com.example.dimshw.Model.Employee;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.sql.Date;
import java.time.Duration;
import java.util.List;

@Controller
public class TaskerController {
    private TaskerService taskerService;
    private final TaskService taskService;
    private final EmployeeService employeeService;
    private TaskerRepository taskerRepository;
    private EmployeeRepository employeeRepository;
    private TaskRepository taskRepository;


    @Autowired
    public TaskerController(TaskerService taskerService,TaskRepository taskRepository, TaskService taskService, EmployeeService employeeService, TaskerRepository taskerRepository, EmployeeRepository employeeRepository) {
        this.taskerService = taskerService;
        this.taskService = taskService;
        this.employeeService = employeeService;
        this.taskerRepository = taskerRepository;
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/taskerindex")
    public String getIndexPage() {
        return "index";
    }


    @GetMapping("/Task/TaskTaskerList/{id}")
    public String showTaskTaskerListPage(Model model, @PathVariable("id") Long id, RedirectAttributes ra) {

        Tasker tasker = new Tasker();
        Task task= taskService.findById(id);
        List<Employee> employees= (List<Employee>) employeeRepository.findAll();

        model.addAttribute("tasker", tasker);
        model.addAttribute("employees", employees);
        model.addAttribute("task",task);
        //model.addAttribute("product_name", o.getProduct().getName());
        //model.addAttribute("customerName", customerService.findCustomerNameById(id));
        // model.addAttribute("product_name", productService.findProductNameById(id));

        return "Task/TaskTaskerList";
    }


    @PostMapping("/Task/createtask/{taskId}")
    public String createTasker(@ModelAttribute("taskCreateDTO") TaskTaskerCreateDTO taskCreateDTO, RedirectAttributes ra, @PathVariable Long taskId) {
        Tasker tasker = new Tasker();
        Employee employee = employeeRepository.findById(taskCreateDTO.getEmployeeId()).orElseThrow();
        Task task = taskRepository.findById(taskId).orElseThrow();

        tasker.setDate(Date.valueOf(taskCreateDTO.getDate()));
        tasker.setEmployee(employee);
        tasker.setTask(task);
        tasker.setTime(Duration.ofDays(taskCreateDTO.getDays()));

        taskerService.saveTasker(tasker);
        ra.addFlashAttribute("message", "The tasker was successfully added");
        return "redirect:/taskers";
    }

    @GetMapping("/tasker/add")
    public String showAddTaskerForm(Model model) {
        List<Task> tasks= (List<Task>) taskRepository.findAll();
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();

        model.addAttribute("tasker", new Taskerview());
        model.addAttribute("employees", employees);
        model.addAttribute("tasks",tasks);
        return "tasker/taskerAdd";
    }

    @PostMapping("/tasker/add")
    public String addTasker(TaskerCreateDTO taskerCreateDTO, RedirectAttributes ra)
    {
        Tasker tasker = new Tasker(); // Create a new Tasker object
        Task task = taskRepository.findById(taskerCreateDTO.getTaskId()).orElseThrow();
        Employee employee = employeeRepository.findById(taskerCreateDTO.getEmployeeId()).orElseThrow();

        tasker.setDate(Date.valueOf(taskerCreateDTO.getDate()));
        tasker.setEmployee(employee);
        tasker.setTask(task);
        tasker.setTime(Duration.ofDays(taskerCreateDTO.getDays()));

        taskerRepository.save(tasker);
        ra.addFlashAttribute("message", "The tasker was successfully added");
        return "redirect:/taskers";
    }



    @GetMapping("/taskers")
    public String showTaskerList(Model model) {
        Iterable<Tasker> taskers = taskerRepository.findAll();
        model.addAttribute("taskers", taskers);
        return "tasker/taskerList";
    }


    @GetMapping("/tasker/update/{id}")
    public String getTaskerById(@PathVariable Long id, Model model) throws TaskerNotFoundException {
        Tasker tasker = taskerService.getTaskerById(id);
        model.addAttribute("tasker", tasker);
        return "tasker/taskerEdit";
    }

    @PostMapping("/tasker/update/{id}")
    public String updateTasker(Tasker taskerview, RedirectAttributes ra) throws TaskerNotFoundException {
        taskerService.updateTasker(taskerview);
        ra.addFlashAttribute("message", "The tasker was successfully updated");
        return "redirect:/taskers";
    }


    @GetMapping("/Employee/EmployeeTaskerList/{EmployeeId}")
    public String showEmployeeTaskerListPage(Model model, @PathVariable("EmployeeId") Long EmployeeId, RedirectAttributes ra) {


        Tasker tasker = new Tasker();
        Employee employee = employeeService.findById(EmployeeId);
        List<Task> tasks= (List<Task>) taskRepository.findAll();

        model.addAttribute("tasker", tasker);
        model.addAttribute("employee", employee);
        model.addAttribute("tasks",tasks);
        //model.addAttribute("product_name", o.getProduct().getName());
        //model.addAttribute("customerName", customerService.findCustomerNameById(id));
        // model.addAttribute("product_name", productService.findProductNameById(id));
        return "Employee/EmployeeTaskerList";
    }


    @PostMapping("/Employee/createEmployee/{employeeId}")
    public String createTaskerEmployee(EmployeeTaskerCreateDTO taskCreateDTO, RedirectAttributes ra, @PathVariable Long employeeId) throws EmployeeNotFoundException {
        Tasker tasker = new Tasker();
        Task task = taskRepository.findById(taskCreateDTO.getTaskId()).orElseThrow();
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();

        tasker.setDate(Date.valueOf(taskCreateDTO.getDate()));
        tasker.setEmployee(employee);
        tasker.setTask(task);
        tasker.setTime(Duration.ofDays(taskCreateDTO.getDays()));

        taskerService.saveTasker(tasker);
        ra.addFlashAttribute("message", "The tasker was successfully added");
        return "redirect:/taskers";
    }

    @GetMapping("/tasker/delete/{id}")
    public String deleteTasker(@PathVariable("id") Long id , RedirectAttributes ra) {
        try {
            taskerService.deleteTasker(id);
            ra.addFlashAttribute("message", "Tasker deleted successfully");
        } catch (TaskerNotFoundException e) {
            ra.addFlashAttribute("error", "Failed to delete tasker: " + e.getMessage());

        }
        return "redirect:/taskers";
    }
}
