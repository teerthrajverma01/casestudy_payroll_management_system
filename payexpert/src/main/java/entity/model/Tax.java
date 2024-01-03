package entity.model;

import java.time.Year;

public class Tax {
	int TaxID, EmployeeID;
	Year TaxYear;
	double TaxableIncome, TaxAmount;
	
	public Tax() {};
	
	public Tax(int taxID, int employeeID, Year taxYear, double taxableIncome, double taxAmount) {
		super();
		TaxID = taxID;
		EmployeeID = employeeID;
		TaxYear = taxYear;
		TaxableIncome = taxableIncome;
		TaxAmount = taxAmount;
	}
	
	public int getTaxID() {
		return TaxID;
	}
	public void setTaxID(int taxID) {
		TaxID = taxID;
	}
	
	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}
	
	public Year getTaxYear() {
		return TaxYear;
	}
	public void setTaxYear(Year taxYear) {
		TaxYear = taxYear;
	}
	
	public double getTaxableIncome() {
		return TaxableIncome;
	}
	public void setTaxableIncome(double taxableIncome) {
		TaxableIncome = taxableIncome;
	}
	
	public double getTaxAmount() {
		return TaxAmount;
	}
	public void setTaxAmount(double taxAmount) {
		TaxAmount = taxAmount;
	}
	
	@Override
    public String toString() {
        return "Tax {" +
                "taxID=" + TaxID +
                ", employeeID=" + EmployeeID +
                ", taxYear=" + TaxYear +
                ", taxableIncome=" + TaxableIncome +
                ", taxAmount=" + TaxAmount +" }";}

}
