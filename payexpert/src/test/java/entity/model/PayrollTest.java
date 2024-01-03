package entity.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PayrollTest {

	@Test
	void CalcutateNetSalaryAfterDeduction() {
		
		Payroll p1 = new Payroll();
		p1.setBasicSalary(1000.0);
		p1.setDeductions(100.0);
		p1.setOvertimePay(300.0);
		p1.setNetSalary(1200.0);
		
		double actualNetSalary = p1.getBasicSalary()-p1.getDeductions()+p1.getOvertimePay();
		assertEquals(p1.getNetSalary(),actualNetSalary);
		
	}

}
