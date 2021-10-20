package kenzan.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class ErrorResponse {
	
	private final String message;

}
