# Payroll Manager

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-11%2B-orange)](https://www.oracle.com/java/)

A comprehensive Java application for employee payroll processing with tax calculation and detailed reporting features. Built as a practical exercise to apply Java programming concepts in a real-world scenario.

## Project Overview

Payroll Manager is a console-based application that helps businesses process employee payroll data. The system reads employee information from text files, validates the data according to business rules, calculates various Canadian tax deductions, and generates comprehensive reports with gross pay, itemized deductions, and net pay.

## Features

### Data Processing

- Read employee data from structured text files
- Validate input data against business rules
- Process multiple employees in a single batch
- Handle malformed data gracefully through error logging

### Validation System

- Verify employee IDs are valid numerical values
- Ensure both first and last names are present
- Check that hours worked is a positive number
- Validate hourly rates meet minimum wage requirements ($15.75)
- Enforce proper data formatting for all fields

### Tax Calculation

- Calculate Federal tax based on income brackets
- Determine Provincial tax deductions
- Compute Employment Insurance contributions
- Process Quebec Pension Plan deductions
- Calculate Quebec Parental Insurance Plan contributions

### Reporting System

- Generate formatted payroll reports with:
  - Employee identification details
  - Gross salary calculations
  - Itemized tax deductions
  - Final net salary amounts
- Produce error logs with invalid data entries
- Display processing statistics in console output

### Error Handling

- Detect and log specific validation errors
- Continue processing valid entries when errors are encountered
- Generate detailed error reports for troubleshooting
- Handle common error types:
  - Invalid numeric formats
  - Missing required fields
  - Negative values
  - Minimum wage violations

## How the System Works

### Data Input Format

The program expects employee data in a specific format:

```
EmployeeID FirstName LastName HoursWorked HourlyRate
```

Example:

```
101 John Doe 40 20.50
102 Jane Smith 35 18.75
103 Michael Johnson 45 22.00
```

### Processing Workflow

1. Read employee data from the input file line by line
2. For each line:
   - Parse and validate all fields
   - If valid, create an Employee object and add to processing list
   - If invalid, log the error and continue to next line
3. For each valid employee:
   - Calculate gross income based on hours and hourly rate
   - Compute all applicable tax deductions
   - Calculate net income after deductions
4. Generate formatted payroll report with results
5. Output error log for any invalid entries

### Report Generation

The system produces a formatted payroll report like this:

```
                             Final Summary Report
-------------------------------------------------------------------------------------
| ID  | First Name | Last Name  | Gross Salary | Deductions |   Net Salary |
-------------------------------------------------------------------------------------
| 101 | John       | Doe        |     42640.00 |   17880.66 |     24759.34 |
| 102 | Jane       | Smith      |     34125.00 |   14309.98 |     19815.02 |
...
```

## Technical Implementation

### Class Structure

- **PayrollManager**: Main class that coordinates file I/O, validation, and reporting
- **Employee**: Data model for employee information with annual gross income calculation
- **Tax Deduction Classes**:
  - `FederalTax`: Calculates federal income tax
  - `ProvincialTax`: Calculates provincial taxes
  - `EmploymentInsurance`: Calculates EI contributions
  - `QCParentalPlan`: Handles Quebec Parental Insurance Plan deductions
  - `QCPensionPlan`: Processes Quebec Pension Plan contributions
- **Custom Exceptions**:
  - `InvalidFileFormatException`: For format errors in input data
  - `MinimumWageException`: For hourly rates below legal minimum wage
  - `NegativeValueException`: For negative numerical values

### Key Programming Concepts Applied

- **Object-Oriented Design**
  - **Inheritance**: Abstract `Deduction` base with concrete tax/benefit subclasses
  - **Encapsulation**: Private Employee state with controlled getters/setters
  - **Polymorphism**: Common calculation interface across deduction types
  - **Single Responsibility Principle**: Focused purpose for each class

- **Exception Handling**
  - Custom exception hierarchy for domain-specific errors
  - Try-catch-finally blocks for robust error management
  - Proper resource cleanup with close operations in finally blocks

- **File I/O Operations**
  - Reading input data from text files
  - Writing formatted reports to output files
  - Creating error logs for invalid data entries

- **Data Validation**
  - Input type checking (numeric formats, text fields)
  - Business rule validation (minimum wage, positive values)
  - Format verification for all required fields

### Usage

1. Place your input file in the `data` directory as `payroll.txt`


2. Run the application


3. The program will generate:
   - `payrollReport.txt` - Formatted report with employee payroll details
   - `payrollError.txt` - Log of any invalid entries with original data

## Limitations and Areas for Improvement

- Console-based interface with limited user interaction
- Fixed input and output file paths
- No graphical visualization of payroll data
- Limited customization of tax calculation parameters
- No database integration for persistent storage
- Single-threaded processing model

## Learning Goals Achieved

This project was developed to practice applying Java programming concepts in a realistic business scenario:

- Implementing robust file I/O operations with proper error handling
- Designing a flexible object-oriented architecture for tax calculations
- Creating custom exception hierarchies for domain-specific error handling
- Applying validation patterns to ensure data integrity
- Building formatted reporting capabilities for business outputs

The focus was on writing clean, maintainable code that demonstrates core OOP principles while solving a practical business problem in the payroll domain.

---
Developed by Ahmed Gara Ali
