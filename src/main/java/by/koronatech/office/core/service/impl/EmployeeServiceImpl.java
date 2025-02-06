package by.koronatech.office.core.service.impl;

import by.koronatech.office.api.dto.CreateEmployeeDTO;
import by.koronatech.office.api.dto.GetEmployeeDTO;
import by.koronatech.office.core.Employee;
import by.koronatech.office.core.mapper.employee.CreateEmployeeMapper;
import by.koronatech.office.core.mapper.employee.GetEmployeeMapper;
import by.koronatech.office.core.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final GetEmployeeMapper getEmployeeMapper;
    private final CreateEmployeeMapper createEmployeeMapper;

    private final List<Employee> cachedEmployeesRepository = new ArrayList<>(
            List.of(
                    Employee.builder().id(1).name("Arslan").salary(100.58).department("Accounting").manager(false).build(),
                    Employee.builder().id(2).name("Petya").salary(50.00).department("Accounting").manager(true).build()
            )
    );

    @Override
    public GetEmployeeDTO getById(int id) {
        return getEmployeeMapper.toDto(findById(id));
    }



    @Override
    public List<GetEmployeeDTO> getByDepartment(String department) {
        return getEmployeeMapper.toDtos(findByDepartment(department));
    }

    @Override
    public GetEmployeeDTO create(int id, CreateEmployeeDTO createEmployeeDTO) {
        cachedEmployeesRepository.add(createEmployeeMapper.toEntity(createEmployeeDTO));
        return getEmployeeMapper.toDto(createEmployeeMapper.toEntity(createEmployeeDTO));
    }

    @Override
    public GetEmployeeDTO update(int id, CreateEmployeeDTO createEmployeeDTO) {
        Employee employee = findById(id);
        createEmployeeMapper.merge(employee, createEmployeeDTO);
        return getEmployeeMapper.toDto(employee);
    }
    @Override
    public GetEmployeeDTO updatefordep(int id, String department) {
        Employee employee = findById(id);
        employee.setDepartment(department);
        return getEmployeeMapper.toDto(employee);
    }

    @Override
    public void delete(int id) {
        cachedEmployeesRepository.remove(findById(id));
    }

    private Employee findById(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        }
        return cachedEmployeesRepository.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Employee with ID " + id + " not found"));
    }

    private List<Employee> findByDepartment(String department) {
        if (department == null || department.isEmpty()) {
            System.out.println("Department name cannot be null or empty");
            return Collections.emptyList();
        }
        List<Employee> filteredEmployees = cachedEmployeesRepository.stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .collect(Collectors.toList());
        if (filteredEmployees.isEmpty()) {
            System.out.println("No employee found for department: " + department);
        }
        return filteredEmployees;
    }
}
