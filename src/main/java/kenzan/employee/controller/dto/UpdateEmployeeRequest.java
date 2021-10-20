package kenzan.employee.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateEmployeeRequest {
 private String firstName;
 private String middleInitial;
 private String lastName;
 private String dob;
 private String dateEmployment; 
}
