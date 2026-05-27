package stream;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import util.Employee;
import util.EmployeeForMapAndFlatMapExercise;
import util.EmployeeGroupingByAndPartioningByExercise;
import util.EmployeeGroupingByAndPartioningByExercise2;
import util.OrderGroupingByAndPartioningByExercise;

public class GroupingByAndPartioningByInStream {

	public static void main(String[] args) {
		
		//Group a list of strings by their first character.
		List<String> names = List.of("Aditya","alpha","beta","gamma");
		
		Map<Object, List<String>> combinedfirstcharacter = names.stream()
//				.collect(Collectors.groupingBy(String::length));//just check what this line will return
				.collect(Collectors.groupingBy(ch->ch.charAt(0)));
		
		System.out.println(combinedfirstcharacter);
		
		
		//Group a List into even and odd using partitioningBy().
		
		List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9);
		
		Predicate<Integer> evenodd = num -> num % 2 == 0;
		Map<Boolean, List<Integer>> evenoddnumber = numbers.stream()
				.collect(Collectors.partitioningBy(evenodd));
		
		System.out.println(evenoddnumber);
		
		//Group employees by department and collect to Map.
		List<EmployeeForMapAndFlatMapExercise> employees = List.of(
	            new EmployeeForMapAndFlatMapExercise("Aditya", "Technology"),
	            new EmployeeForMapAndFlatMapExercise("Tharun", "Marketing"),
	            new EmployeeForMapAndFlatMapExercise("Dipak", "Technology"),
	            new EmployeeForMapAndFlatMapExercise("Nilu", "Finance"),
	            new EmployeeForMapAndFlatMapExercise("Amit", "Marketing")
	        );
		
		
		Map<String, List<String>> namesByDept = employees.stream()
				.collect(Collectors.groupingBy(
							EmployeeForMapAndFlatMapExercise::getDepartment,
							Collectors.mapping(EmployeeForMapAndFlatMapExercise::getName, Collectors.toList())
						));
				
		System.out.println(namesByDept);
		
		
		//Count employees per department using groupingBy + counting().		
		Map<String, Long> countemployeeByDept = employees.stream()
				.collect(Collectors.groupingBy(
							EmployeeForMapAndFlatMapExercise::getDepartment,
							Collectors.mapping(EmployeeForMapAndFlatMapExercise::getName, Collectors.counting())
						));
				
		System.out.println(countemployeeByDept);
		
		
		//Find the average salary per department using groupingBy + averagingDouble().
		List<EmployeeGroupingByAndPartioningByExercise> employeeGroupingByAndPartioning = List.of(
	            new EmployeeGroupingByAndPartioningByExercise("Aditya", "Technology", 60000),
	            new EmployeeGroupingByAndPartioningByExercise("Tharun", "Marketing", 45000),
	            new EmployeeGroupingByAndPartioningByExercise("Dipak", "Technology", 80000),
	            new EmployeeGroupingByAndPartioningByExercise("Neha", "Finance", 55000),
	            new EmployeeGroupingByAndPartioningByExercise("Amit", "Marketing", 55000)
	        );
		
		Map<String, Double> avgSalaryByDept = employeeGroupingByAndPartioning.stream()
	            .collect(Collectors.groupingBy(
	            		EmployeeGroupingByAndPartioningByExercise::getDepartment,            // Grouping Key
	                Collectors.averagingDouble(EmployeeGroupingByAndPartioningByExercise::getSalary) // Downstream Action: average salaries
	            ));
				
		System.out.println(avgSalaryByDept);
		
		
		//Group words by length and collect each group as a joined string.
		List<Employee> employeeswithsalary = List.of(
	            new Employee("Aditya", 60000),
	            new Employee("Tharun", 45000),
	            new Employee("Dipak", 75000),
	            new Employee("Neha", 48000),
	            new Employee("Amit", 52000)
	        );
		
		Map<Boolean, Long> employeeCountWithSalarySplit = employeeswithsalary.stream()
				.collect(Collectors.partitioningBy(
						emp -> emp.getSalary()>50000,
						Collectors.counting()
						));
		
		
		System.out.println("Employees with salary > 50k (true): " + employeeCountWithSalarySplit.get(true));
        System.out.println("Employees with salary <= 50k (false): " + employeeCountWithSalarySplit.get(false));
		
		
        
        //Group employees by dept, then by city (nested groupingBy).
        List<EmployeeGroupingByAndPartioningByExercise2> employeeswithcity = List.of(
                new EmployeeGroupingByAndPartioningByExercise2("Aditya", "Technology", "Mumbai"),
                new EmployeeGroupingByAndPartioningByExercise2("Tharun", "Marketing", "Chennai"),
                new EmployeeGroupingByAndPartioningByExercise2("Dipak", "Finance", "Delhi"),                
                new EmployeeGroupingByAndPartioningByExercise2("Amit", "Marketing", "Bengaluru")
                
            );

            // 1. Group by Department, then by City
            Map<String, Map<String, List<EmployeeGroupingByAndPartioningByExercise2>>> nestedGroup = employeeswithcity.stream()
                .collect(Collectors.groupingBy(
                		EmployeeGroupingByAndPartioningByExercise2::getDepartment, // Primary classification (Outer Map Key)
                    Collectors.groupingBy(EmployeeGroupingByAndPartioningByExercise2::getCity) // Secondary classification (Inner Map Key)
                ));

            // 2. Pretty-print the nested map structure
            nestedGroup.forEach((dept, cityMap) -> {
                System.out.println("Department: " + dept);
                cityMap.forEach((city, empList) -> {
                    System.out.println("  └── City: " + city + " -> " + empList);
                });
            });
		
		
		//Find the highest-paid employee per department using groupingBy + maxBy().
            List<EmployeeGroupingByAndPartioningByExercise> employeesmaxpaidsalary = List.of(
                    new EmployeeGroupingByAndPartioningByExercise("Aditya", "Technology", 80000),
                    new EmployeeGroupingByAndPartioningByExercise("Tharun", "Marketing", 70000),
                    new EmployeeGroupingByAndPartioningByExercise("Dipak", "Finance", 50000),                
                    new EmployeeGroupingByAndPartioningByExercise("Amit", "Marketing", 60000)
                );
		
            Map<String, Optional<EmployeeGroupingByAndPartioningByExercise>> maxsalaryByDept = employeesmaxpaidsalary.stream()
            		.collect(Collectors.groupingBy(
            				EmployeeGroupingByAndPartioningByExercise::getDepartment,
            				Collectors.maxBy(Comparator.comparingDouble(EmployeeGroupingByAndPartioningByExercise::getSalary))
            				));
//            
//            Map<String, Optional<EmployeeGroupingByAndPartioningByExercise>> maxSalaryByDept = employeesmaxpaidsalary.stream()
//                    .collect(Collectors.groupingBy(
//                    		EmployeeGroupingByAndPartioningByExercise::getDepartment,
//                        Collectors.maxBy(Comparator.comparingDouble(EmployeeGroupingByAndPartioningByExercise::getSalary))
//                    ));
		
            

            
            System.out.println("Highest paid employee per department:");
            maxsalaryByDept.forEach((dept, empOptional) -> 
                System.out.println(dept + " -> " + empOptional.orElse(null))
            );
		
            
            List<OrderGroupingByAndPartioningByExercise> orders = List.of(
                    new OrderGroupingByAndPartioningByExercise("ORD01", LocalDate.of(2026, 3, 15), 1200.50),
                    new OrderGroupingByAndPartioningByExercise("ORD02", LocalDate.of(2026, 4, 22), 450.00),
                    new OrderGroupingByAndPartioningByExercise("ORD03", LocalDate.of(2026, 3, 5),  800.00),
                    new OrderGroupingByAndPartioningByExercise("ORD04", LocalDate.of(2026, 5, 12), 3100.00),
                    new OrderGroupingByAndPartioningByExercise("ORD05", LocalDate.of(2026, 4, 1),  150.25)
                );
            
            Map<Month, Double> totalAmountByMonth = orders.stream()
                    .collect(Collectors.groupingBy(
                        order -> order.getOrderDate().getMonth(), // Grouping Key (Month Enum)
                        Collectors.summingDouble(OrderGroupingByAndPartioningByExercise::getAmount) // Downstream Action: Sum the amounts
                    ));
            
            totalAmountByMonth.forEach((month, total) -> 
            	System.out.printf("%s: $%.2f%n", month, total)
            		);
	}
}
