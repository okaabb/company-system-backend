package org.project.onboarding.security;

import lombok.RequiredArgsConstructor;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.QEmployee;
import org.project.onboarding.repository.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QEmployee qEmployee = QEmployee.employee;
        Optional<Employee> user = employeeRepository.findOne(qEmployee.username.eq(username).and(qEmployee.isDeleted.eq(false)  ));
        if (user.isEmpty()) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        }

        return new EmployeePrinciple(user.get());
    }
}