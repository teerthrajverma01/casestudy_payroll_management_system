package dao;

import java.time.Year;
import java.util.ArrayList;

import entity.model.Tax;

public interface ITaxService {
	
	double  CalculateTax(int employeeId,Year taxYear);
	
	Tax GetTaxById(int taxId);
	
	ArrayList<Tax> GetTaxesForEmployee(int employeeId);
	
	ArrayList<Tax> GetTaxesForYear(Year taxYear);
	
}
