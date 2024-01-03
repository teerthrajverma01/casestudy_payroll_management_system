package dao;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Date;

import org.junit.jupiter.api.Test;

import entity.model.Payroll;
import exception.PayrollGenerationException;
import util.DBUtil;

class PayrollServiceTest {

	 @Test
	    void processPayrollForEmployee() {
		 DBUtil.getConnection();
		 
	        PayrollService payrollService = new PayrollService();
	        int payrollId = 10;
	        int employeeId = 2; 
	        Date startDate = new Date(); 
	        Date endDate = new Date();
	        double basicSalary = 5000.0; 
	        double overtimePay = 1000.0;
	        double deductions = 500.0;
	        double netSalary = basicSalary + overtimePay - deductions;

	        Payroll payroll = new Payroll(payrollId, employeeId, startDate, endDate, basicSalary, overtimePay, deductions, netSalary);
	        assertNotNull(payroll);
	        try {
	            payrollService.GeneratePayroll(payroll);
	            
	        } catch (Exception e ) {
	            fail("Exception occurred: "+ e.getMessage());
	        }
	    }

}
