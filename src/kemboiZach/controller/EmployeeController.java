 package kemboiZach.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kemboiZach.entity.Employee;
import kemboiZack.dao.EmployeeDAO;
import kemboiZack.dao.EmployeeDAOImpl;



public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //creating it globally
	RequestDispatcher dispatcher = null;
     
	 //Create a reference variable for employee DAO 
	EmployeeDAO employeeDAO = null;
	
	//Create constructor and initialize employee DAO
	public EmployeeController() {
		employeeDAO = new EmployeeDAOImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String action = request.getParameter("action");
	
	if (action== null) {
		action = "LIST";
		
	}
	switch (action) {
	case "LIST":
		ListEmployees(request, response);
		break;
	
	case "EDIT":
		getSingleEmployee(request,response);
        break;
    
	case "DELETE":
    	deleteEmployee(request,response);
    	break;
	
	default:
		ListEmployees(request, response);
		break;
	}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getting values from JSP page employee-add.jsp
		String id = request.getParameter("id");
		String name = request.getParameter("firstname");
		String dob = request.getParameter("dob");
		String department = request.getParameter("department");
	
		//creating employee object
		Employee e = new Employee();
		//passing parameter to employee e
		
		e.setName(name);
		e.setDepartment(department);
		e.setDob(dob);
		//using employeeDAO method to call a save() method
		 
		if(id.isEmpty()|| id== null) {
			//save operation
			if(employeeDAO.save(e)) {
				request.setAttribute("message", "Saved successfully!");
			}
		}else {
			//update operation
			e.setId(Integer.parseInt(id));
			if(employeeDAO.update(e)) {
				request.setAttribute("message", "Update successfully!");
			}
		}
		
		
		//Call after saving database record
		ListEmployees(request, response);
		
	}
	// creating new method which is listEmployess which perform list operations
	public void ListEmployees(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		//Call dao method to get list of employees
				List<Employee> list = employeeDAO.get();
				
				//Add the employees to the request object
				request.setAttribute("list", list);
				
				//get the request dispatcher
		 		dispatcher = request.getRequestDispatcher("Views/employee-list.jsp");
				
				//forward the request and the response
				dispatcher.forward(request, response);
	}
	
	public void getSingleEmployee(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		//System.out.println("id:"+id);
		Employee employee = employeeDAO.get(Integer.parseInt(id));
		request.setAttribute("employee", employee);
		//get the request dispatcher
		dispatcher = request.getRequestDispatcher("/Views/employee-add.jsp");
		
		//foward the req and res objects
		dispatcher.forward(request, response);
		
	}
	
	public void deleteEmployee(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String id = request.getParameter("id");
		if (employeeDAO.delete(Integer.parseInt(id))) {
			request.setAttribute("message", "Record has been deleted!");
			
		}
		//Call after saving database record
		ListEmployees(request, response);
		
	}
	

}
