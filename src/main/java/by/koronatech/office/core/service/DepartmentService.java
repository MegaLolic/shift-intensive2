package by.koronatech.office.core.service;

import by.koronatech.office.api.dto.CreateEmployeeDTO;
import by.koronatech.office.api.dto.GetDepartmentDTO;
import by.koronatech.office.api.dto.GetEmployeeDTO;
import by.koronatech.office.core.Employee;

import java.util.List;

public interface DepartmentService {
    List<GetDepartmentDTO> getAllDepartment();

    GetEmployeeDTO addEmployee(int id, CreateEmployeeDTO createEmployeeDTO);

    GetDepartmentDTO deleteEmployee(int id, Employee employee);
    GetDepartmentDTO getByIdDep(int id);


}
