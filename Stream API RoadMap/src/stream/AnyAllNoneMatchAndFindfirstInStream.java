package stream;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import util.Employee;
import util.EmployeeForMapAndFlatMapExercise;
import util.EmployeeGroupingByAndPartioningByExercise;

public class AnyAllNoneMatchAndFindfirstInStream {

	public static void main(String[] args) {
		
		List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,101,102,103,104,105,106,107,108,109);
		//Check if any number in a list is greater than 100 using anyMatch().
		Predicate<Integer> checknumgreaterthan100 = num -> num > 100;
		
		boolean greaterthan100 = numbers.stream()
				.anyMatch(checknumgreaterthan100);
		
		System.out.println(greaterthan100);
		
		//Check if all strings in a list are non-empty using allMatch().
		Predicate<String> emptyornot = name -> !name.isEmpty();
		List<String> names = List.of("alpha","beta","gamma");
		
		boolean checkemptyornot = names.stream()
				.allMatch(emptyornot);
		System.out.println(checkemptyornot);
		
		
		//Check if none of the numbers in a list is negative using noneMatch().
		Predicate<Integer> numnotnegative = number -> number > 0;
		boolean checknonenegativeornot = numbers.stream()
				.noneMatch(numnotnegative);
		System.out.println(checknonenegativeornot);
		
		
		//Find the first string longer than 5 characters using findFirst().
		Predicate<String> greaterthan5 = name-> name.length()>5;
		List<String> bigandsmallnames = List.of("alpha","beta","gamma","aditya");
		Optional<String> checkbigname = bigandsmallnames.stream()
				.filter(greaterthan5)
				.findFirst();
		
		checkbigname.ifPresent(System.out::println);
		
		
		
		//Use findFirst() safely with orElse() to avoid NoSuchElementException.
		List<String> smallnames = List.of("alpha","beta","gamma");
		String checkbignameifexist = smallnames.stream()
				.filter(greaterthan5)
				.findFirst()
				.orElse("Unknown Employee");;
		
		System.out.println(checkbignameifexist);
		
		
		//Check if a list of employees all have salary > 30000.
		List<Employee> employee = List.of(
				new Employee("Aditya",50000),
				new Employee("Dipak",40000),
				new Employee("Tharun",300000),
				new Employee("Nilu",20000)
				);
		
		boolean checkemployeegreatersalary = employee.stream()
				.allMatch(emp -> emp.getSalary() > 30000);
		
		System.out.println(checkemployeegreatersalary);
		
		
		
		//Find the first employee in a dept using filter() + findFirst() + Optional.
		List<EmployeeForMapAndFlatMapExercise> employees = List.of(
	            new EmployeeForMapAndFlatMapExercise("Aditya", "Technology"),
	            new EmployeeForMapAndFlatMapExercise("Tharun", "Marketing"),
	            new EmployeeForMapAndFlatMapExercise("Dipak", "Technology"),
	            new EmployeeForMapAndFlatMapExercise("Nilu", "Finance")
	        );
		
		Optional<EmployeeForMapAndFlatMapExercise> firstTechEmp = employees.stream()				
				.filter(emp -> emp.getDepartment().equals("Technology"))				
				.findFirst();
		
		firstTechEmp.ifPresent(emp -> System.out.println("First in Tech: " + emp));
		
		
		//Use anyMatch() to check if a list contains a duplicate (hint: use a Set side-effect).
		List<String> items = List.of("apple", "banana", "orange", "apple", "grape");
		
		Set<String> seenElements = new HashSet<>();
		
		boolean hasduplicate = items.stream()
				.anyMatch(item -> !seenElements.add(item));
		
		System.out.println("has duplicates? "+hasduplicate);
		
		
		
		//Given a nested list of lists, check if any inner list contains a null using flatMap+anyMatch.
		
		List<List<String>> nestedList = List.of(
	            List.of("apple", "banana"),
	            List.of("cherry"),
	            Arrays.asList("orange", null, "grape") // Using Arrays.asList because List.of throws NPE on nulls
	        );
		
		
		boolean hasnull = nestedList.stream()
				.flatMap(innerList -> innerList.stream())
				.anyMatch(Objects::isNull);
		
		System.out.println("Does any inner list contain a null? " + hasnull);
		
		
		
		
		//Chain findFirst() result through Optional.map() and orElseGet() to transform safely.
		
		List<Employee> employeechaining = List.of(
				new Employee("Aditya",50000),
				new Employee("Dipak",40000),
				new Employee("Tharun",30000),
				new Employee("Nilu",20000)
				);
		
		String highest = employeechaining.stream()
				.filter(emp -> emp.getSalary() > 100000)				
				.findFirst()
				.map(emp -> emp.getName().toUpperCase())
				.orElseGet(()->"No match found".toUpperCase());
		
		System.out.println(highest);
		
		
	}
}
