package kemboiZack.dao;

import java.util.List;

import kemboiZach.entity.Employee;

// Define a single method with return a list of employees
public interface EmployeeDAO {
    
	List<Employee> get();
    
	//declaring method that takes employee as an argument
    boolean save(Employee e);
    
    Employee get(int id);
    
    boolean update(Employee e);
    
    boolean delete(int id);
}
