package kenzan.employee.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import kenzan.employee.domain.StatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "T_EMPLOYEE")
public class Employee {
	@Id
	@Column
	private String id;
	@Column
	private String firstName;
	@Column
	private String middleInitial;
	@Column
	private String lastName;
	@Column
	private String dob;
	@Column
	private String dateEmployment;
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
}
