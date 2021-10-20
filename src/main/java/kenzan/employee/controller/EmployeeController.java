package kenzan.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kenzan.employee.controller.dto.CreateEmployeeRequest;
import kenzan.employee.controller.dto.Employee;
import kenzan.employee.controller.dto.UpdateEmployeeRequest;
import kenzan.employee.domain.ValidationException;
import kenzan.employee.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	private EmployeeService service;
	/**
	 * Constructor for spring dependency injection
	 * @param service {@link EmployeeService}
	 */
	public EmployeeController(EmployeeService service) {
		this.service = service;
	}
	@GetMapping("/{id}")	
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") String id) {
		Optional<Employee> emp = service.getEmployeeById(id);
		if (!emp.isPresent()) {
			throw new ValidationException("Employee not found");
		}
		return new ResponseEntity<>(emp.get(), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Employee> registerEmployee(@RequestBody CreateEmployeeRequest request){
		Optional<Employee> emp =service.createEmployee(request);
		if (!emp.isPresent()) {
			throw new ValidationException("Employee could not be registered");
		}
		return new ResponseEntity<>(emp.get(), HttpStatus.CREATED);
	}
	@GetMapping()
	public ResponseEntity<List<Employee>> getEmployees() {
		Optional<List<Employee>> employees = service.getEmployees();		
		return new ResponseEntity<>(employees.get(), HttpStatus.OK);
	}
	@PostMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") String id,
			@RequestBody UpdateEmployeeRequest request){
		Optional<Employee> emp =service.updateEmployee(id,request);
		if (!emp.isPresent()) {
			throw new ValidationException("Employee could not be registered");
		}
		return new ResponseEntity<>(emp.get(), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") String id){
		Optional<Employee> emp =service.deleteEmployee(id);
		if (!emp.isPresent()) {
			throw new ValidationException("Employee could not be deleted");
		}
		return new ResponseEntity<>("Employee unregistered successfully", HttpStatus.OK);
	}


}
