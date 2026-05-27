package util;

public class EmployeeGroupingByAndPartioningByExercise {
	private String name;
	private String department;
	private double salary;

	public EmployeeGroupingByAndPartioningByExercise(String name, String department, double salary) {
		this.name = name;
		this.department = department;
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public double getSalary() {
		return salary;
	}

	@Override
	public String toString() {
		return "Employee -> " + department + "-> {name='" + name + "', salary=" + salary + "}";
	}
}
