package kemboiZach.entity;

public class Employee {
	
	//Step 1: Define fields
	private int  id;
	
	private String name;
	
	private String dob;
	
	private String department;
	
	//Step 2: generating getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	
	
	
	
	// Step 3: generate to string
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", dob=" + dob + ", department=" + department + "]";
	}
    
	
	
	
	
	
	

}
