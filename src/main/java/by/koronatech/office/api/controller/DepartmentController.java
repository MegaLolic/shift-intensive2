package by.koronatech.office.api.controller;

import by.koronatech.office.api.dto.CreateEmployeeDTO;
import by.koronatech.office.api.dto.GetDepartmentDTO;
import by.koronatech.office.api.dto.GetEmployeeDTO;
import by.koronatech.office.core.Department;
import by.koronatech.office.core.service.DepartmentService;
import by.koronatech.office.core.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
@Slf4j

public class DepartmentController {

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    @GetMapping("/all")
    public List<GetDepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartment();
    }

    @PostMapping("/{departmentId}/employee")
    public GetEmployeeDTO addEmployeeInDep(@PathVariable int departmentId, @RequestBody CreateEmployeeDTO createEmployeeDTO) {
        String newDep=getByIdDep(departmentId).getName();
        createEmployeeDTO.setDepartment(newDep);
        return departmentService.addEmployee(departmentId, createEmployeeDTO);
    }

    @GetMapping("/{id}")
    public GetDepartmentDTO getByIdDep(@PathVariable int id) {
        log.info("Get Department by id: {}", id);
        return departmentService.getByIdDep(id);
    }
    @GetMapping("/employee/{id}")
    public GetEmployeeDTO getById(@PathVariable int id) {
        log.info("Get employee by id: {}", id);
        return employeeService.getById(id);
    }
    @GetMapping("/{departmentId}/employees")
    public List<GetEmployeeDTO> getByDepartmentForId(@PathVariable int departmentId) {
        log.info("Get employee by department: {}", departmentService.getByIdDep(departmentId).getName());
        return employeeService.getByDepartment(departmentService.getByIdDep(departmentId).getName());
    }

    @PostMapping("/employee")
    public GetEmployeeDTO create(int id,@RequestBody CreateEmployeeDTO createEmployeeDTO) {
        return employeeService.create(id, createEmployeeDTO);
    }

    @PutMapping("/employee/{id}")
    public GetEmployeeDTO update(@PathVariable int id, @RequestBody CreateEmployeeDTO createEmployeeDTO) {
        return employeeService.update(id, createEmployeeDTO);
    }
    @PutMapping("/{departmentId}/employee/{id}/manage")
    public GetEmployeeDTO updatemanage(@PathVariable int id, @RequestBody CreateEmployeeDTO createEmployeeDTO) {
        createEmployeeDTO.setManager(true);
        return employeeService.update(id,createEmployeeDTO);

    }

    @DeleteMapping("/{departmentsId}/employee/{id}")
    public void delete(@PathVariable int departmentsId, @PathVariable int id) {
        employeeService.delete(id);
        log.info("All good!!!");
    }
}
