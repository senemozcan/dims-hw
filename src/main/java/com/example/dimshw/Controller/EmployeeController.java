package com.example.dimshw.Controller;
import com.example.dimshw.Model.Employee;
import com.example.dimshw.Services.EmployeeService;
import com.example.dimshw.Services.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeeController {
    @Autowired private EmployeeService service;
    @GetMapping("/index")
    public String getIndexPage() {
        return "index";
    }
    @GetMapping("/Employee")
    public String showEmployeeList(Model model)
    {
        List<Employee> listEmployee =service.listAll();
        model.addAttribute("listEmployee" , listEmployee);
        return "Employee/EmployeeList";
    }
    @PostMapping("/Employee/save")
    public String saveCustomer(@ModelAttribute Employee employee, RedirectAttributes ra) {
        service.save(employee);
        ra.addFlashAttribute("message", "The employee has been saved successfully :)");
        return "redirect:/Employee";
    }
    @GetMapping("/Employee/create")
    public String showNewForm(Model model) {
        model.addAttribute("Customer", new Employee());
        return "Employee/EmployeeAdd";
    }
    @GetMapping("/Employee/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            Employee employee = service.get(id);
            model.addAttribute("employee", employee);
        } catch (EmployeeNotFoundException e) {
            ra.addFlashAttribute("message", "Employee not found :(");
            return "redirect:/Employee";
        }
        return "Employee/EmployeeEdit";
    }

    @PostMapping("/Employee/edit/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @ModelAttribute Employee employee, RedirectAttributes ra) {
        try {
            service.updateEmployee(id, employee);
            ra.addFlashAttribute("message", "The employee has been updated successfully :)");
        } catch (EmployeeNotFoundException e) {
            ra.addFlashAttribute("message", "Employee not found :(");
        }
        return "redirect:/Employee";
    }

    @GetMapping("/Employee/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id,  RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message","The employee with ID " +id+" has been deleted");


        }
        catch (EmployeeNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/Employee";

    }

}
