-- Create the database
CREATE DATABASE IF NOT EXISTS CompanyDB;
USE CompanyDB;

-- Create the Employee Table
CREATE TABLE IF NOT EXISTS Employee  (
    EmployeeID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    DateOfBirth DATE,
    Gender CHAR(1),	
    Email VARCHAR(100),
    PhoneNumber VARCHAR(15),
    Address VARCHAR(250),
    Position VARCHAR(100),
    JoiningDate DATE,
    TerminationDate DATE 
);

-- Create the Payroll Table
CREATE TABLE IF NOT EXISTS Payroll (
    PayrollID INT PRIMARY KEY AUTO_INCREMENT,
    EmployeeID INT,
    PayPeriodStartDate DATE,
    PayPeriodEndDate DATE,
    BasicSalary DECIMAL(10, 2),
    OvertimePay DECIMAL(10, 2),
    Deductions DECIMAL(10, 2),
    NetSalary DECIMAL(10, 2),
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)
);

-- Create the Tax Table
CREATE TABLE IF NOT EXISTS Tax (
    TaxID INT PRIMARY KEY AUTO_INCREMENT,
    EmployeeID INT,
    TaxYear YEAR,
    TaxableIncome DECIMAL(10, 2),
    TaxAmount DECIMAL(10, 2),
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)
);

-- Create the FinancialRecord Table
CREATE TABLE IF NOT EXISTS FinancialRecord (
    RecordID INT PRIMARY KEY AUTO_INCREMENT,
    EmployeeID INT,
    RecordDate DATE,
    Description VARCHAR(250),
    Amount DECIMAL(10, 2),
    RecordType VARCHAR(50),
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)
);
