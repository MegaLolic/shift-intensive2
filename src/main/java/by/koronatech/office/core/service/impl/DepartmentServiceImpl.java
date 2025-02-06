package by.koronatech.office.core.service.impl;

import by.koronatech.office.api.dto.CreateEmployeeDTO;
import by.koronatech.office.api.dto.GetDepartmentDTO;
import by.koronatech.office.api.dto.GetEmployeeDTO;
import by.koronatech.office.core.Department;
import by.koronatech.office.core.Employee;
import by.koronatech.office.core.mapper.employee.GetDepartmentMapper;
import by.koronatech.office.core.service.DepartmentService;
import by.koronatech.office.core.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;
    private final GetDepartmentMapper getDepartmentMapper;

    private final List<Department> cachedDepartmentRepository = new ArrayList<>(
            List.of(
                    Department.builder().id(1).name("Accounting").build(),
                    Department.builder().id(2).name("HR").build()
            )
    );
    @Override
    public List<GetDepartmentDTO> getAllDepartment() {
        return getDepartmentMapper.toDtos(findByAll());
    }
    @Override
    public GetEmployeeDTO addEmployee(int id, CreateEmployeeDTO createEmployeeDTO) {
        return  employeeService.create(id,createEmployeeDTO);
    }

    @Override
    public GetDepartmentDTO deleteEmployee(int id, Employee employee) {
        return null;
    }
    public List<Department> findByAll() {
        return cachedDepartmentRepository;
    }
    @Override
    public GetDepartmentDTO getByIdDep(int id) {
        return getDepartmentMapper.toDto(findByIdDep(id));
    }

    private Department findByIdDep(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        }
        return cachedDepartmentRepository.stream()
                .filter(department -> department.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Depatment with ID " + id + " not found"));
    }
}
