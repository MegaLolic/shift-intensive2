package by.koronatech.office.core;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer id;

    private String name;

    private Double salary;

    private String department;

    private Boolean manager;
}
