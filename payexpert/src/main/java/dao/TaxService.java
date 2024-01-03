package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;

import entity.model.Tax;
import exception.EmployeeNotFoundException;
//import exception.InvalidInputException;
import exception.TaxCalculationException;
import util.DBUtil;

public class TaxService implements ITaxService {

	@Override
	public  double CalculateTax(int employeeId, Year taxYear) {
		try {
			String q1 = "SELECT 1 FROM Employee WHERE EmployeeID = ?";
			PreparedStatement pstmt1 = DBUtil.connection.prepareStatement(q1);
			pstmt1.setInt(1, employeeId);
			ResultSet rs1 = pstmt1.executeQuery(); 
		    if(! rs1.next()) { // check for employee
		    	throw(new EmployeeNotFoundException("No employee with id:"+employeeId+" exists"));
	    	}
		   
			double taxableIncome =0;
			String q2 = "SELECT SUM(NetSalary)  FROM payroll WHERE EmployeeID = ? AND YEAR(PayPeriodEndDate) = ?";
			PreparedStatement pstmt2 = DBUtil.connection.prepareStatement(q2);
			pstmt2.setInt(1, employeeId);
	        pstmt2.setInt(2, taxYear.getValue());
	        ResultSet rs2 = pstmt2.executeQuery();
	            if (rs2.next()) {//if found for that year
	            	
	                taxableIncome = rs2.getDouble(1);
	                
	                String q3 = "INSERT INTO Tax ( EmployeeID, TaxYear, TaxableIncome, TaxAmount) VALUES (?, ?, ?, ?)";
	                PreparedStatement pstmt3 = DBUtil.connection.prepareStatement(q3);
	                    
	                    pstmt3.setInt(1, employeeId);
	                    pstmt3.setInt(2,  taxYear.getValue());
	                    pstmt3.setDouble(3, taxableIncome);
	                    Double taxamount = taxableIncome * 0.84;
	                    pstmt3.setDouble(4, taxamount);

	                    int affectedRows = pstmt3.executeUpdate();
	                    if (affectedRows > 0) {
	                        System.out.println("Tax record added successfully.");
	                    } else {
	                        throw new SQLException("Failed to insert tax record.");
	                    }
	                
	                
	                return taxableIncome;
	            }
	            else {//
	            	throw (new TaxCalculationException("No record found to calculate tax for eid:"+employeeId+" for taxyear"+taxYear) );
	            }      
		}catch(EmployeeNotFoundException enfe) {
			enfe.printStackTrace();
		}catch(TaxCalculationException tce) {
			tce.printStackTrace();
		}catch (SQLException se) {
			se.printStackTrace();
		}
		return 0;
	}

	@Override
	public Tax GetTaxById(int taxId) {
		try {
			String q1 = "SELECT * FROM Tax WHERE TaxID = ?";
			PreparedStatement pstmt1 = DBUtil.connection.prepareStatement(q1);
			pstmt1.setInt(1, taxId);
			ResultSet rs1 = pstmt1.executeQuery();
	        if (rs1.next()) {
				int taxid= rs1.getInt("TaxID");
				int empid= rs1.getInt("EmployeeID");
				Year year = Year.of(rs1.getInt("TaxYear"));
				double taxableincome = rs1.getDouble("TaxableIncome");
				double taxamount= rs1.getDouble("TaxAmount");
				Tax tx = new Tax(taxid, empid, year, taxableincome, taxamount);
				return tx;
	        }else {
	        	throw (new TaxCalculationException("No record found for taxid:"+taxId) );
            }      
		}catch(TaxCalculationException tce) {
			tce.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
	    }
		return null;
	}

	@Override
	public ArrayList<Tax> GetTaxesForEmployee(int employeeId) {
		try {
			String q1 = "SELECT 1 FROM Employee WHERE EmployeeID = ?";
			PreparedStatement pstmt1 = DBUtil.connection.prepareStatement(q1);
			pstmt1.setInt(1, employeeId);
			ResultSet rs1 = pstmt1.executeQuery(); 
		    if(! rs1.next()) { // check for employee
		    	throw(new EmployeeNotFoundException("No employee with id:"+employeeId+" exists"));
	    	}
		    
		    ArrayList<Tax> txsofeid =new ArrayList<Tax>();
		    
			String q2 = "SELECT * FROM Tax WHERE employeeId = ?";
			PreparedStatement pstmt2 = DBUtil.connection.prepareStatement(q2);
			pstmt2.setInt(1, employeeId);
			ResultSet rs2 = pstmt2.executeQuery();
	        while (rs2.next()) {
				int taxid= rs2.getInt("TaxID");
				int empid= rs2.getInt("EmployeeID");
				Year year = Year.of(rs2.getInt("TaxYear"));
				double taxableincome = rs2.getDouble("TaxableIncome");
				double taxamount= rs2.getDouble("TaxAmount");
				Tax tx = new Tax(taxid, empid, year, taxableincome, taxamount);
				txsofeid.add(tx);
	        }
		    if(txsofeid.isEmpty()) {
		    	throw (new TaxCalculationException("No record found for employeeId:"+employeeId));
		    }
		    return txsofeid; 
		}catch(EmployeeNotFoundException enfe) {
			enfe.printStackTrace();
		}catch(TaxCalculationException tce) {
			tce.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
	    }
		return null;
	}

	@Override
	public ArrayList<Tax> GetTaxesForYear(Year taxYear) {
		try {
		    String q1 = "SELECT * FROM Tax WHERE TaxYear = ?";
			PreparedStatement pstmt1 = DBUtil.connection.prepareStatement(q1);
			pstmt1.setInt(1,taxYear.getValue() );
			ResultSet rs1 = pstmt1.executeQuery();
			ArrayList<Tax> txsforty =new ArrayList<Tax>();
	        while (rs1.next()) {
				int taxid= rs1.getInt("TaxID");
				int empid= rs1.getInt("EmployeeID");
				Year year = Year.of(rs1.getInt("TaxYear"));
				double taxableincome = rs1.getDouble("TaxableIncome");
				double taxamount= rs1.getDouble("TaxAmount");
				Tax tx = new Tax(taxid, empid, year, taxableincome, taxamount);
				txsforty.add(tx);
	        }
		    if(txsforty.isEmpty()) {
		    	throw (new TaxCalculationException("No record found for taxyear:"+taxYear));
		    }
		    return txsforty; 
		}catch(TaxCalculationException tce) {
			tce.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
	    }
		return null;
	}

}
