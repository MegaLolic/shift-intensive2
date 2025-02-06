package by.koronatech.office.api.dto;

import by.koronatech.office.core.Employee;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class GetDepartmentDTO {
    private Integer id;
    private String name;
}
