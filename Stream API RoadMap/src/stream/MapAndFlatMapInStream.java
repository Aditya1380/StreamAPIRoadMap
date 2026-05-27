package stream;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import util.Employee;
import util.EmployeeForMapAndFlatMapExercise;

public class MapAndFlatMapInStream {
	//filter() removes element from list depending on condition
	//map() transform the list
	//flatMap() it collapse nested stream   -> List<List<String>>  -> List<String>
		
	
	public static void main(String[] args) {
		
		//Convert a List of names to uppercase using map().
		List<String> somenames = List.of("alice","bob","charlie");
		
		List<String> uppercasesomenames = somenames.stream()
//				.map(name -> name.toUpperCase()) //Same logic
				.map(String::toUpperCase)//With Method reference
				.toList();
		System.out.println(uppercasesomenames);
		
		//Extract the lengths of each string in a list using map().
		List<String> names = List.of("Aditya","alpha","beta","gamma");
		
		List<Integer> lengthofnames = names.stream()
				.map(String::length)
				.toList();
		
		System.out.println(lengthofnames);
		
		//Convert a List of number strings to List.
		List<String> numberinstring = List.of("1","2","3","4","5","6","7","8","9");
		
		List<Integer> turningstringintonumber = numberinstring.stream()
				.map(Integer::parseInt)
				.toList();
		System.out.println(turningstringintonumber);
				
		
		//Given a List, extract a List of their names.
		List<Employee> employee = List.of(
				new Employee("Aditya", 60000),
	            new Employee("Tharun", 45000),
	            new Employee("Dipak", 75000),
	            new Employee("Nilu", 48000)
				);
		
		List<String> empnames = employee.stream()
				.map(Employee::getName)
				.toList();
		System.out.println(empnames);
		
		
		
		//Use mapToInt() to compute the sum of string lengths without boxing.
		List<String> namestofindlen = List.of("Aditya","alpha","beta","gamma");
		int lenofname = namestofindlen.stream()
				.mapToInt(String::length)
				.sum();
		
		System.out.println(lenofname);
		
		
		//Flatten a List> into a single List using flatMap().
		List<List<Integer>> nestedlist = List.of(List.of(1,2),List.of(3,4));
		List<Integer> flat = nestedlist.stream()
				.flatMap(Collection::stream)
				.toList();
		
		System.out.println(flat);
		
		
		
		//Given a list of sentences, extract all individual words into one list.
		List<String> sentences = List.of(
	            "Java streams are powerful",
	            "flatMap flattens structures",
	            "coding is fun"
	        );		

		List<String> allwords = sentences.stream()
				.flatMap(sentence -> Stream.of(sentence.split("//s+")))
				.toList();
		System.out.println(allwords);
		
		
		//Build a pipeline: given employees, get unique department names sorted alphabetically.
		List<EmployeeForMapAndFlatMapExercise> employeesAndDepartment = List.of(
	            new EmployeeForMapAndFlatMapExercise("Aditya", "Technology"),
	            new EmployeeForMapAndFlatMapExercise("Tharun", "Marketing"),
	            new EmployeeForMapAndFlatMapExercise("Dipak", "Technology"),
	            new EmployeeForMapAndFlatMapExercise("Nilu", "Finance")
	        );
		
		List<String> departments = employeesAndDepartment.stream()
				.map(EmployeeForMapAndFlatMapExercise::getDepartment)
				.distinct()
				.sorted()
				.toList();
	
		System.out.println(departments);
		
		
		
		
		
		
	}
}
