package by.koronatech.office.api.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class GetEmployeeDTO {
    private Integer id;

    private String name;

    private Double salary;

    private String department;

    private Boolean manager;
}
