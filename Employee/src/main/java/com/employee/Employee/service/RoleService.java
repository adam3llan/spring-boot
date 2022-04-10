package com.employee.Employee.service;

import com.employee.Employee.models.employee.Employee;
import com.employee.Employee.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void addUserRole(Employee employee) {
         employee.setRoles(new HashSet<>(roleRepository.findByName("USER")));
    }
}
