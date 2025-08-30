# PayrollManager

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-11%2B-orange)](https://www.oracle.com/java/)

A comprehensive Java application for employee payroll processing with tax calculation and detailed reporting features. This application handles various employee data validation scenarios, calculates accurate tax deductions according to Canadian standards, and generates detailed reports.

## Key Features

- **Robust Data Processing**: Reads and validates employee payroll data from text files
- **Comprehensive Error Handling**: 
  - Validates employee IDs, names, work hours, and hourly rates
  - Identifies and reports specific errors (minimum wage violations, negative values, etc.)
  - Continues processing despite encountering errors
- **Canadian Tax Calculation System**:
  - Federal tax calculation
  - Provincial tax calculation 
  - Employment Insurance contributions
  - Quebec Pension Plan contributions
  - Quebec Parental Insurance Plan contributions
- **Detailed Report Generation**:
  - Produces formatted payroll reports with gross pay, itemized deductions, and net pay
  - Generates error logs with invalid data entries for review

## Getting Started

### Requirements

- Java 11 or higher
- Text editor or IDE (Eclipse, IntelliJ IDEA, etc.)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/PayrollManager.git
   ```

2. Navigate to the project directory:
   ```bash
   cd PayrollManager
   ```

3. Compile the project:
   ```bash
   javac -d out src/*.java src/deductions/*.java src/exceptions/*.java
   ```

### Usage

1. Create an input file with employee data in this format:
   ```
   EmployeeID FirstName LastName HoursWorked HourlyRate
   ```

2. Place your input file in the `data` directory as `payroll.txt`

3. Run the application:
   ```bash
   java -cp out PayrollManager
   ```

4. The program will generate:
   - `payrollReport.txt` - Formatted report with employee payroll details
   - `payrollError.txt` - Log of any invalid entries with original data

### Input Format Example

```
101 John Doe 40 20.50
102 Jane Smith 35 18.75
103 Michael Johnson 45 22.00
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

### Validation Features

The program implements comprehensive validation, including:
- Ensuring employee IDs are valid numerical values
- Verifying both first and last names are present
- Checking that hours worked is a positive number
- Ensuring hourly rates meet or exceed minimum wage requirements (set at $15.75)
- Proper data formatting for all fields

### Output Example

The program generates formatted payroll reports like this:

```
                             Final Result Summary
-------------------------------------------------------------------------------------
| ID  | First Name | Last Name  | Gross Salary | Deductions |   Net Salary |
-------------------------------------------------------------------------------------
| 101 | John       | Doe        |     42640.00 |   17880.66 |     24759.34 |
| 102 | Jane       | Smith      |     34125.00 |   14309.98 |     19815.02 |
...
```

## Error Handling

The application maintains integrity by:
1. Detecting and logging specific validation errors
2. Continuing to process valid entries even when errors are encountered
3. Providing detailed error reports for troubleshooting

Common error types handled:
- Invalid numeric formats
- Missing required fields
- Negative values
- Minimum wage violations

## Personal Practice Objectives

This project was intentionally designed as a hands-on exercise to practice core Object-Oriented Programming concepts in Java:

- **Inheritance**: Abstract `Deduction` base with concrete tax/benefit subclasses; custom exception subclasses for granular errors.
- **Polymorphism**: Iterate over mixed deduction objects via a common calculation contract (no type-specific branching).
- **Encapsulation**: Private Employee state + controlled setters centralize validation and protect integrity.
- **Custom Exceptions**: Domain-specific error signaling (`MinimumWageException`, etc.) clarifies validation paths.
- **Single Responsability Principle**: Each class has a focused purpose (data model, tax calculation, error handling).

---
Developed by Ahmed Gara Ali
