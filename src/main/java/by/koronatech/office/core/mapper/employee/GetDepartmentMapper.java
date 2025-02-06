package by.koronatech.office.core.mapper.employee;
import by.koronatech.office.api.dto.GetDepartmentDTO;
import by.koronatech.office.core.Department;
import by.koronatech.office.core.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapper.class)
public interface GetDepartmentMapper extends BaseMapper<Department, GetDepartmentDTO> {
}
