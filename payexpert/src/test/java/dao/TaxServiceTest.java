package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.Year;

import org.junit.jupiter.api.Test;

import exception.EmployeeNotFoundException;
import exception.TaxCalculationException;
import util.DBUtil;

import org.junit.jupiter.api.Test;
class TaxServiceTest {
	@Test
    void verifyTaxCalculationForHighIncomeEmployee() {
		
		DBUtil.getConnection();
	 TaxService taxService = new TaxService();
     int employeeId = 1; // Replace with a valid employee ID in your database
     Year taxYear = Year.of(2022); // Replace with the desired tax year

     // Act
     try {
         double tax = taxService.CalculateTax(employeeId, taxYear);
         System.out.println(tax);
         double expectedValue = 5100.0; 
         assertEquals(expectedValue, tax, 0.01); 
     }
     catch(Exception e) {
    	 fail("Exception occurred: " + e.getMessage()); 
     }
     
 }
}