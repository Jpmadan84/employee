package kenzan.employee.domain;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 6960942503688255462L;
	
	public ValidationException(String message) {
		super(message);
	}
}
