package util;

public class EmployeeGroupingByAndPartioningByExercise2 {
	private String name;
	private String department;
	private String city;

	public EmployeeGroupingByAndPartioningByExercise2(String name, String department, String city) {
		this.name = name;
		this.department = department;
		this.city = city;
	}

	public String getDepartment() {
		return department;
	}

	public String getCity() {
		return city;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name + " (" + city + ")";
	}
}
