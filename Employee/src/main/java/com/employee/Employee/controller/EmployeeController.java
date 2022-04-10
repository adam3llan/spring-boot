package com.employee.Employee.controller;

import javax.validation.Valid;
import com.employee.Employee.models.employee.Employee;
import com.employee.Employee.models.task.Task;
import com.employee.Employee.service.EmployeeService;
import com.employee.Employee.service.RoleService;
import com.employee.Employee.service.TaskService;
import com.employee.Employee.repository.EmployeeRepository;
import com.employee.Employee.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final TaskService taskService;
    private final RoleService roleService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, RoleService roleService, TaskService taskService) {
        this.employeeService = employeeService;
        this.roleService = roleService;
        this.taskService = taskService;
    }

    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }
    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "signup_form";
    }
    @PostMapping("/process_register")
    public String newEmployee(@ModelAttribute("employee") Employee employee) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(employee.getPassword());
        employee.setPassword(encodedPassword);
        roleService.addUserRole(employee);
        employeeService.addEmployee(employee);
        return "register_success";
    }
    @GetMapping("/users")
    public String findAllEmployees(Model model) {
        List<Employee> employeeList = employeeService.findAllEmployees();
        model.addAttribute("employeeList", employeeList);
        return "employees";
    }

    @GetMapping("/addTask")
    public String taskForm(String email, Model model, HttpSession session) {
        session.setAttribute("email", email);
        model.addAttribute("task", new Task());
        return "sendTaskForm";
    }
    @PostMapping("/addTask")
    public String addTask(@Valid Task task, HttpSession session) {
        String email = (String) session.getAttribute("email");
        Employee employee = employeeService.findEmployeeByEmail(email);
        taskService.addTask(task, employee);
        return "redirect:/employees";
    }

    @GetMapping("/dashboard/tasks")
    public String dashPage(Model model, Principal principal){
        String email = principal.getName();
        Employee employee = employeeService.findEmployeeByEmail(email);
        model.addAttribute("tasks", taskService.findUserTask(employee));
        return "profile";
    }

    @GetMapping("/dashboard")
    public String Dashboard(Model model, Principal principal) {
        String email = principal.getName();
        Employee employee = employeeService.findEmployeeByEmail(email);
        model.addAttribute("employee", employee);
        return "dashboard";
    }

}
