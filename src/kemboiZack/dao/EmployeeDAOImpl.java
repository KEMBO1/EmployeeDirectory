package kemboiZack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kemboiZach.entity.Employee;
import kemboiZack.util.DBConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {
    //declaring variables
	Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
     @Override
	public List<Employee> get() {
		
		// create a reference variable
		List<Employee> list = null;
		Employee employee = null;
		
		try {
			
			// initializing the list/ArrayList
			list = new ArrayList<Employee>();
			
			// create a sql query
			String sql = "SELECT * FROM tbl_employee";
			
			// GETTING A DATABASE CONNECTION FROM THE STATIC METHOD(OpenConnection ) WHICH HAD BEEN CREATED BEFORE this is by calling a class DBConnectionUtil
			connection = DBConnectionUtil.openConnection();
			
			// Execute a statement
			statement = connection.createStatement();
			 
			//execute the sql query
			resultSet = statement.executeQuery(sql);
			
			//process the resultset
			while(resultSet.next()) {
				//create a Employee object
				employee = new Employee();
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setDepartment(resultSet.getString("department"));
				employee.setDob(resultSet.getString("dob"));
				
				//add employee object to the list
				list.add(employee);
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return the list
		return list;
		
	}

	@Override
	public boolean save(Employee e) {
	   //creating a flag
		boolean flag = false;
	   //try catch which catches sql exception
		try {
			//writting sql query to insert data into database and passing the parameters name,dob  and department
		String sql = "INSERT INTO tbl_employee(name, dob,department)VALUES('"+e.getName()+"','"+e.getDob()+"','"+e.getDepartment()+"')";
		//getting database connection
		connection=DBConnectionUtil.openConnection();
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.executeUpdate();
		//changing the flag to true
		flag = true;
	} catch (SQLException ex) {
		ex.printStackTrace();
		
	}
	return flag;

}

	@Override
	public Employee get(int id) {
		Employee employee = null;
		
		try {
			employee = new Employee();
			String sql = "SELECT * FROM tbl_employee WHERE id = "+id;
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setDepartment(resultSet.getString("department"));
				employee.setDob(resultSet.getString("dob"));
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
//		System.out.println("Employee name:"+employee.getName()+" Employee dob:"+employee.getDob()+" Employee department:"+employee.getDepartment());
		return employee;
	
	}

	@Override
	public boolean update(Employee e) {
		boolean flag = false;
		try {
			String sql = "UPDATE tbl_employee SET name='"+e.getName()+"',dob='"+e.getDob()+"',department='"+e.getDepartment()+"'WHERE id="+e.getId();
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
		
		
	}

	@Override
	public boolean delete(int id) {
		boolean flag = false;
		try {
			String sql = "DELETE FROM tbl_employee WHERE id="+id;
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
	}
}
