package kenzan.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kenzan.employee.domain.StatusEnum;
import kenzan.employee.repository.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, String> {
	/**
	 * Find an employee by id
	 */
	@Query(value = "SELECT t FROM Employee t WHERE t.status=:status AND t.id=:id ")
	Optional<Employee> findById(@Param("status") StatusEnum status, @Param("id") String id);
	/**
	 * Find employees based on status
	 * default order dateEmployment
	 */
	@Query(value = "SELECT t FROM Employee t WHERE t.status=:status ORDER BY t.dateEmployment DESC")
	Optional<List<Employee>> findEmployees(@Param("status") StatusEnum status);
}
