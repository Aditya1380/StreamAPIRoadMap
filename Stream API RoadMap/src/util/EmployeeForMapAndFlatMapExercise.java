package util;

public class EmployeeForMapAndFlatMapExercise {
	private String name;
	private String department;

	public EmployeeForMapAndFlatMapExercise(String name, String department) {
		this.name = name;
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	public String getName() {
		return name;
	}
	
	@Override
    public String toString() {
        return name + " (" + department + ")";
    }
}
