package by.koronatech.office.core.service;

import by.koronatech.office.api.dto.CreateEmployeeDTO;
import by.koronatech.office.api.dto.GetEmployeeDTO;

import java.util.List;

public interface EmployeeService {
    GetEmployeeDTO getById(int id);

    List<GetEmployeeDTO> getByDepartment(String department);

    GetEmployeeDTO create(int id, CreateEmployeeDTO createEmployeeDTO);

    GetEmployeeDTO update(int id, CreateEmployeeDTO createEmployeeDTO);
    GetEmployeeDTO updatefordep(int id, String department);
    void delete(int id);
}
