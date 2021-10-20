package kenzan.employee.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import kenzan.employee.controller.dto.CreateEmployeeRequest;
import kenzan.employee.controller.dto.UpdateEmployeeRequest;
import kenzan.employee.domain.StatusEnum;
import kenzan.employee.domain.ValidationException;
import kenzan.employee.repository.EmployeeRepo;
import kenzan.employee.repository.model.Employee;

@Service
public class EmployeeService {
	private EmployeeRepo employeeRepo;

	/**
	 * Constructor for spring dependency injection
	 * 
	 * @param employeeRepo {@link EmployeeRepo}
	 */
	public EmployeeService(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	public Optional<kenzan.employee.controller.dto.Employee> createEmployee(CreateEmployeeRequest request) {
		Employee modelEmployee = new Employee();
		modelEmployee.setId(UUID.randomUUID().toString());
		modelEmployee.setDateEmployment(request.getDateEmployment());
		modelEmployee.setDob(request.getDob());
		modelEmployee.setFirstName(request.getFirstName());
		modelEmployee.setLastName(request.getLastName());
		modelEmployee.setMiddleInitial(request.getMiddleInitial());
		modelEmployee.setStatus(StatusEnum.ACTIVE);
		return getDtoFromModel(Optional.of(employeeRepo.save(modelEmployee)));

	}
	
	public Optional<kenzan.employee.controller.dto.Employee> updateEmployee(
			String id,
			UpdateEmployeeRequest request) {
		Optional<Employee> existingEmployee = employeeRepo.findById(StatusEnum.ACTIVE, id);
		if (!existingEmployee.isPresent()) {
			throw new ValidationException("Employee not found");
		}
		Employee emp = existingEmployee.get();
		if (!ObjectUtils.isEmpty(request.getDateEmployment())) {
			emp.setDateEmployment(request.getDateEmployment());
		}
		if (!ObjectUtils.isEmpty(request.getDob())) {
			emp.setDob(request.getDob());
		}
		if (!ObjectUtils.isEmpty(request.getFirstName())) {
			emp.setFirstName(request.getFirstName());
		}
		if (!ObjectUtils.isEmpty(request.getLastName())) {
			emp.setLastName(request.getLastName());
		}
		if (!ObjectUtils.isEmpty(request.getMiddleInitial())) {
			emp.setMiddleInitial(request.getMiddleInitial());
		}		
		return getDtoFromModel(Optional.of(employeeRepo.save(emp)));

	}
	
	public Optional<kenzan.employee.controller.dto.Employee> deleteEmployee(
			String id) {
		Optional<Employee> existingEmployee = employeeRepo.findById(StatusEnum.ACTIVE, id);
		if (!existingEmployee.isPresent()) {
			throw new ValidationException("Employee not found");
		}
		Employee emp = existingEmployee.get();
		emp.setStatus(StatusEnum.INACTIVE);
		return getDtoFromModel(Optional.of(employeeRepo.save(emp)));

	}


	public Optional<kenzan.employee.controller.dto.Employee> getEmployeeById(String id) {
		return getDtoFromModel(employeeRepo.findById(StatusEnum.ACTIVE, id));
	}

	public Optional<List<kenzan.employee.controller.dto.Employee>> getEmployees() {

		Optional<List<Employee>> optEmployees = employeeRepo.findEmployees(StatusEnum.ACTIVE);
		if (!optEmployees.isPresent()) {
			return Optional.empty();
		}
		return Optional.of(optEmployees.get()
				.stream()
				.map(e -> getDtoFromModel(Optional.of(e)))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList()));

	}

	private static Optional<kenzan.employee.controller.dto.Employee> getDtoFromModel(Optional<Employee> optionalModel) {
		if (!optionalModel.isPresent()) {
			return Optional.empty();
		}
		Employee model = optionalModel.get();
		kenzan.employee.controller.dto.Employee dto = new kenzan.employee.controller.dto.Employee();
		dto.setId(model.getId());
		dto.setDateEmployment(model.getDateEmployment());
		dto.setDob(model.getDob());
		dto.setFirstName(model.getFirstName());
		dto.setLastName(model.getLastName());
		dto.setMiddleInitial(model.getMiddleInitial());
		dto.setStatus(model.getStatus());
		return Optional.of(dto);
	}	

}
