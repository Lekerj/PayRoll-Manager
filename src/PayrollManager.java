import exceptions.InvalidFileFormatException;
import exceptions.MinimumWageException;
import exceptions.NegativeValueException;

import deductions.FederalTax;
import deductions.ProvincialTax;
import deductions.EmploymentInsurance;
import deductions.QCParentalPlan;
import deductions.QCPensionPlan;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * This program reads employee data from a file, calculates various tax deductions,
 * and generates both a payroll report and an error log report
 * The program handles various data validation errors including:
 * >Invalid employee IDs
 * >Missing employee names
 * >Invalid or negative hours worked
 * >Invalid or insufficient hourly rates
 *
 * Written by: Ahmed Gara Ali
 */
public class PayrollManager {
    /**
     *
     * Reads employee data from a file, validates it, calculates tax deductions,
     * and generates payroll and error reports.
     *
     * @param args Command line arguments (not used in this application)
     */

    // Define file names for input and output operations
    private static final String PAYROLL_IN    = "data/payroll.txt";
    private static final String REPORT_OUT    = "data/payrollReport.txt";
    private static final String ERROR_OUT     = "data/payrollError.txt";

    private static final double MINIMUM_WAGE  = 15.75;      // Minimum wage constant for validation
    private static final int    MAX_EMPLOYEES = 150;    // Maximum number of employees to process

    public static void main(String[] args){

        // Display welcome message
        System.out.println("\n******************************************************************************" + "\n"
                + "|           Welcome to the Employee Payroll Calculator                       |\n"
                + "******************************************************************************");

        // Initialize file handling objects as null for proper try-catch-finally structure
        Scanner FileScanner = null;       // For reading the input file
        PrintWriter ReportWriter =null;   // For writing to the payroll report
        PrintWriter ErrorWriter = null;   // For writing to the error log

        // Array to store employee objects. Max employees for testing
        Employee[] EmployeeList = new Employee[MAX_EMPLOYEES];

        try {
            // Initialize file objects for reading and writing
            FileScanner = new Scanner(new FileReader(PayrollManager.PAYROLL_IN));     // Opens input file for reading
            ReportWriter = new PrintWriter(new BufferedWriter(new FileWriter(PayrollManager.REPORT_OUT))); // Opens report file for writing
            ErrorWriter = new PrintWriter(new BufferedWriter(new FileWriter(PayrollManager.ERROR_OUT)));    // Opens error log for writing

            // Display status messages to console
            System.out.println("> Opening file payroll...");
            System.out.println("> Reading file payroll...");

            // Initialize counters for tracking processed lines and errors
            int LineCounter = 0;          // Tracks valid employee entries
            int ErrorLineCounter =0;      // Tracks invalid employee entries

            // Checker to ensure the error message header prints only once
            boolean isPrintedOnce = true;

            /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // While loop to process and read each line from the input file
            while (FileScanner.hasNextLine()) {

                String Line = FileScanner.nextLine(); // Read the current line from file and stores it in a String each time an iteration occurs

                if (Line.trim().isEmpty()) continue; // Skips line if any empty lines in the file

                Scanner LineDecomposer = new Scanner(Line); // Create scanner to scan a line. This will treat the line read as an input
                // By spliting line by line, we scan piece by piece what we want.

                boolean isValid = true;   // Flag to track if current line has valid data

                // Variables to store employee data
                long ID;                  // Employee ID
                String FirstName;         // Employee first name
                String LastName;          // Employee last name
                double HoursWorked;       // Hours worked
                double HourRate;          // Hourly wage rate

                try {
                    // Checks and read Employee ID
                    if (!LineDecomposer.hasNextLong())
                    {throw new InvalidFileFormatException("Invalid Employee ID!");} //Checks if the format is right (starts with long)
                    ID = LineDecomposer.nextLong();
                    if (ID < 0)
                    {throw new NegativeValueException("Employee ID cannot be negative!");} //Checks if ID is a negative number

                    // Checks and read First Name
                    if (!LineDecomposer.hasNext())
                    {throw new InvalidFileFormatException("Missing First Name!");} // Check if first name exists otherwise error
                    FirstName = LineDecomposer.next();

                    // Validate and read Last Name
                    if (!LineDecomposer.hasNext())
                    {throw new InvalidFileFormatException("Missing Last Name!");} // Check if last name exists otherwise error
                    LastName = LineDecomposer.next();

                    // Validate and read Hours Worked
                    if (!LineDecomposer.hasNextDouble())
                    {throw new InvalidFileFormatException("Invalid Hours Worked!");} //Checks if the number hours worked is a double otherwise error
                    HoursWorked = LineDecomposer.nextDouble();
                    if (HoursWorked< 0)
                    {throw new NegativeValueException("Hours Worked cannot be negative!");} //Checks if the number of hours scanner is negative

                    // Validate and read Hourly Rate
                    if (!LineDecomposer.hasNextDouble())
                    {throw new InvalidFileFormatException("Invalid Hour Rate!");} //Checks if the hourly rate is a double otherwise error
                    HourRate = LineDecomposer.nextDouble();
                    if (HourRate< 0)
                    {throw new NegativeValueException("Hour Rate cannot be negative!");} //Checks if the rate is negative or not
                    if (HourRate<MINIMUM_WAGE)
                    {throw new MinimumWageException("Minimum Wage cannot be less than "+MINIMUM_WAGE);} //Checks if the rate is below minimum wage

                    if (LineCounter >= EmployeeList.length) {
                        System.out.println("Warning: maximum employees reached (" + MAX_EMPLOYEES + "); extra lines ignored.");
                        break; // stop processing further lines
                    }
                    // Create and store new Employee object if all validations pass and increments the LineCounter (for correct employees)
                    EmployeeList[LineCounter++] = new Employee(ID,FirstName,LastName,HoursWorked,HourRate);

                } catch(InvalidFileFormatException | InputMismatchException | NegativeValueException | MinimumWageException e){
                    // Handle all validation exceptions (since we just need to catch them without really outputing any sort of message)
                    isValid=false;            // Mark current line as invalid (so that after the line read, we write it into the error logs)
                    ErrorLineCounter++;       // Increment error counter to mention the amount of error lines later

                } finally {
                    // Close LineDecomposer scanner to prevent resource leaks
                    if (LineDecomposer != null) {
                        LineDecomposer.close();
                    }
                }

                // Write to error log and display a message that says that it has encountered at least 1 error line
                if (!isValid) {
                    if (isPrintedOnce){
                        // Print error header (only once)
                        System.out.println("\n>Error lines found in the payroll");
                        isPrintedOnce=false;
                    }
                    ErrorWriter.println(Line);    // Write invalid line to error file
                    System.out.println(Line);     // Display invalid line to console
                    continue; // Skip to next line in file
                }
            }
/// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // Display summary of processed lines
            System.out.println("\n");
            System.out.println("> "+LineCounter+" employee information lines read from payroll document");
            System.out.println("> "+ErrorLineCounter+" lines are logged into the error report");

            System.out.println("> Calculating Deductions");
            System.out.println("> Writing report file");

            System.out.println();

            // Output header for console display
            System.out.println("                              Final Summary Report");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.printf("| %-3s | %-10s | %-10s | %12s | %10s | %12s |\n",
                    "ID", "First Name", "Last Name", "Gross Salary", "Deductions", "Net Salary");
            System.out.println("-------------------------------------------------------------------------------------");


            // Write header to report file
            ReportWriter.println("                              Final Summary Report");
            ReportWriter.println("-------------------------------------------------------------------------------------");
            ReportWriter.printf("| %-3s | %-10s | %-10s | %12s | %10s | %12s |\n",
                    "ID", "First Name", "Last Name", "Gross Salary", "Deductions", "Net Salary");
            ReportWriter.println("-------------------------------------------------------------------------------------");

            // Process each employee record to calculate taxes and output results
            for (int i = 0; i< LineCounter; i++){

                // Calculate different tax components using appropriate tax classes
                ProvincialTax ProventialTaxCal = new ProvincialTax(EmployeeList[i].getGrossIncome());

                FederalTax FederalTaxCal = new FederalTax(EmployeeList[i].getGrossIncome());

                EmploymentInsurance EmployementInsuranceCal = new EmploymentInsurance(EmployeeList[i].getGrossIncome());

                QCParentalPlan QCParentalPlanCal = new QCParentalPlan(EmployeeList[i].getGrossIncome());

                QCPensionPlan QCPensionPlanCal = new QCPensionPlan(EmployeeList[i].getGrossIncome());

                // Calculate total deductions, rounding to 2 decimal places
                double TotalDeductible = Math.round((ProventialTaxCal.calculateTax() + FederalTaxCal.calculateTax() +EmployementInsuranceCal.calculateTax() + QCParentalPlanCal.calculateTax() + QCPensionPlanCal.calculateTax())*100.00)/100.00;

                // Calculate net income after deductions, rounding to 2 decimal places
                double NetIncome = Math.round((EmployeeList[i].getGrossIncome() - TotalDeductible)*100.00)/100.00;

                // Output employee data and calculations to console
                System.out.printf("| %-3d | %-10s | %-10s | %12.2f | %10.2f | %12.2f |\n",
                        EmployeeList[i].getID(),
                        EmployeeList[i].getFirstName(),
                        EmployeeList[i].getLastName(),
                        EmployeeList[i].getGrossIncome(),
                        TotalDeductible,
                        NetIncome);

                // Write employee data and calculations to report file
                ReportWriter.printf("| %-3d | %-10s | %-10s | %12.2f | %10.2f | %12.2f |\n\n",
                        EmployeeList[i].getID(),
                        EmployeeList[i].getFirstName(),
                        EmployeeList[i].getLastName(),
                        EmployeeList[i].getGrossIncome(),
                        TotalDeductible,
                        NetIncome);

            }

        } catch (FileNotFoundException e) {
            // Handle case where input file cannot be found
            System.out.println("Error... Payroll file could not be found, Please check the path of the file!");
        } catch (IOException e){
            // Handle general input/output errors
            System.out.println("Error... Could not read or write to a file!");
        } finally {
            // Clean up resources regardless of success or failure
            // Close input file scanner
            if (FileScanner != null) {
                try {
                    FileScanner.close();
                    System.out.println();
                    System.out.println("Payroll data file closed successfully.");
                } catch (Exception e) {
                    System.out.println("Error: Could not close "+PayrollManager.PAYROLL_IN+".");
                }
            }
            // Close payroll report writer
            if (ReportWriter != null) {
                try {
                    ReportWriter.close();
                    System.out.println("Report text file closed successfully.");
                } catch (Exception e) {
                    System.out.println("Error: Could not close "+PayrollManager.REPORT_OUT+".");
                }
            }
            // Close error report writer
            if (ErrorWriter != null) {
                try {
                    ErrorWriter.close();
                    System.out.println("Error text file closed successfully.");
                } catch (Exception e) {
                    System.out.println("Error: Could not close "+PayrollManager.ERROR_OUT+".");
                }
            }
        }
    }
}
