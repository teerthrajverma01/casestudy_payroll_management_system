package dao;

import java.util.*;

import entity.model.Employee;

public interface IEmployeeService {
	Employee GetEmployeeById(int employeeId);
	ArrayList<Employee> GetAllEmployees();
	void AddEmployee(Employee employeeData);
	Employee UpdateEmployee(Employee employeeData);
	void RemoveEmployee(int employeeId);
	
}