package kenzan.employee.controller.dto;

import kenzan.employee.domain.StatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Employee {
 private String id;
 private String firstName;
 private String middleInitial;
 private String lastName;
 private String dob;
 private String dateEmployment;
 private StatusEnum status;
}
