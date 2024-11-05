// Base class Employee
class Employee {
    protected int employeeId;
    protected String employeeName;
    protected String designation;

    // Constructor
    public Employee(int employeeId, String employeeName, String designation) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.designation = designation;
    }

    // Setters and Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    // Display employee details
    public void displayInfo() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + employeeName);
        System.out.println("Designation: " + designation);
    }

    // Calculate bonus (default method for base class)
    public double calculateBonus() {
        System.out.println("Base bonus method in Employee class");
        return 0;
    }
}

// Derived class HourlyEmployee
class HourlyEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public HourlyEmployee(int employeeId, String employeeName, String designation, double hourlyRate, int hoursWorked) {
        super(employeeId, employeeName, designation);
        setHourlyRate(hourlyRate);
        setHoursWorked(hoursWorked);
    }

    // Setters with validation
    public void setHourlyRate(double hourlyRate) {
        if (hourlyRate < 0) {
            throw new IllegalArgumentException("Hourly rate must be non-negative.");
        }
        this.hourlyRate = hourlyRate;
    }

    public void setHoursWorked(int hoursWorked) {
        if (hoursWorked < 0 || hoursWorked > 168) { // max hours in a week
            throw new IllegalArgumentException("Hours worked must be between 0 and 168.");
        }
        this.hoursWorked = hoursWorked;
    }

    // Calculate weekly salary
    public double calculateWeeklySalary() {
        return hourlyRate * hoursWorked;
    }

    @Override
    public double calculateBonus() {
        double bonus = calculateWeeklySalary() * 0.10;
        System.out.println("Hourly Employee Bonus: " + bonus);
        return bonus;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Hourly Rate: $" + hourlyRate);
        System.out.println("Hours Worked: " + hoursWorked);
        System.out.println("Weekly Salary: $" + calculateWeeklySalary());
    }
}

// Derived class SalariedEmployee
class SalariedEmployee extends Employee {
    protected double monthlySalary;

    public SalariedEmployee(int employeeId, String employeeName, String designation, double monthlySalary) {
        super(employeeId, employeeName, designation);
        setMonthlySalary(monthlySalary);
    }

    // Setter with validation
    public void setMonthlySalary(double monthlySalary) {
        if (monthlySalary < 0) {
            throw new IllegalArgumentException("Monthly salary must be non-negative.");
        }
        this.monthlySalary = monthlySalary;
    }

    // Calculate weekly salary
    public double calculateWeeklySalary() {
        return monthlySalary / 4;
    }

    @Override
    public double calculateBonus() {
        double bonus = monthlySalary * 0.05;
        System.out.println("Salaried Employee Bonus: " + bonus);
        return bonus;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Monthly Salary: $" + monthlySalary);
        System.out.println("Weekly Salary: $" + calculateWeeklySalary());
    }
}

// Derived class ExecutiveEmployee
class ExecutiveEmployee extends SalariedEmployee {
    private double bonusPercentage;

    public ExecutiveEmployee(int employeeId, String employeeName, String designation, double monthlySalary, double bonusPercentage) {
        super(employeeId, employeeName, designation, monthlySalary);
        setBonusPercentage(bonusPercentage);
    }

    // Setter with validation
    public void setBonusPercentage(double bonusPercentage) {
        if (bonusPercentage < 0 || bonusPercentage > 100) {
            throw new IllegalArgumentException("Bonus percentage must be between 0 and 100.");
        }
        this.bonusPercentage = bonusPercentage;
    }

    @Override
    public double calculateBonus() {
        double annualSalary = monthlySalary * 12;
        double bonus = (annualSalary * bonusPercentage) / 100;
        System.out.println("Executive Employee Bonus: " + bonus);
        return bonus;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Bonus Percentage: " + bonusPercentage + "%");
    }
}

// Main class with the main method to test the program
class Employeepayrollsystem {
    public static void main(String[] args) {
        // Instantiate HourlyEmployee
        HourlyEmployee hourlyEmployee = new HourlyEmployee(1, "Alice", "Lab Assistant", 20, 35);
        System.out.println("Hourly Employee Details:");
        hourlyEmployee.displayInfo();
        hourlyEmployee.calculateBonus();
        System.out.println();

        // Instantiate SalariedEmployee
        SalariedEmployee salariedEmployee = new SalariedEmployee(2, "Bob", "Professor", 5000);
        System.out.println("Salaried Employee Details:");
        salariedEmployee.displayInfo();
        salariedEmployee.calculateBonus();
        System.out.println();

        // Instantiate ExecutiveEmployee
        ExecutiveEmployee executiveEmployee = new ExecutiveEmployee(3, "Carol", "Dean", 10000, 20);
        System.out.println("Executive Employee Details:");
        executiveEmployee.displayInfo();
        executiveEmployee.calculateBonus();
        System.out.println();
    }
}