package stream;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import util.Employee;
import util.Orders;

public class FilterAndPredicateInStream {

	public static void main(String[] args) {
		
		//Filter a list of integers to keep only even numbers.
		List<Integer> nums = List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14);
		
		//Basic filter function
		List<Integer> even =  nums.stream()
				.filter(n->n%2==0)
				.collect(Collectors.toList());
		System.out.println(even);
		
		
		//Filter a list of strings to keep only those with length > 4.
		List<String> names = List.of("Aditya","alpha","beta","gamma");
		List<String> justbignames = names.stream()
				.filter(n->n.length()>4)
				.toList();
		System.out.println(justbignames);
		
		
		//Filter a list of names to find those starting with "A".
		List<String> differentnames = List.of("Aditya","alpha","beta","gamma");
		List<String> justnamesWithA = differentnames.stream()
				.filter(n->n.startsWith("A"))
				.toList();
		System.out.println(justnamesWithA);
		
		
		//Filter a list of integers and keep numbers between 10 and 50 (inclusive).		
		List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14);
		List<Integer> justnumberbetween10and50 = numbers.stream()
				.filter(n-> n>10 && n<50)
				.toList();
		System.out.println(justnumberbetween10and50);
		
		//Filter a list of Employee objects to keep only those with salary > 50000.
		List<Employee> employee = List.of(
				new Employee("Aditya", 60000),
	            new Employee("Tharun", 45000),
	            new Employee("Dipak", 75000),
	            new Employee("Nilu", 48000)
				);
		
		List<Employee> justhighearner = employee.stream()
				.filter(emp->emp.getSalary()>50000)
				.toList();
		justhighearner.forEach(System.out::println);
		
		
		//Use Predicate.negate() to filter out null values from a list of strings.
		List<Integer> manynumber = List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21);
		
		Predicate<Integer> isevennum = evnum -> evnum % 2== 0;
		Predicate<Integer> isGreaterThanFive = igtf -> igtf > 5;
		
		List<Integer> filteredNumbers = manynumber.stream()
				.filter(isevennum.and(isGreaterThanFive))
				.toList();
		
		System.out.println(filteredNumbers);
		
		//Use Predicate.negate() to filter out null values from a list of strings.
		List<String> fruitswithnullvalues = Arrays.asList("Apple", null, "Banana", null, "Cherry");
		
		Predicate<String> isNull = Objects::isNull;
		List<String> notnull = fruitswithnullvalues.stream()
				.filter(isNull)
				.toList();
		System.out.println(notnull);
		
		
		List<String> namesWithnonnullvalues = Arrays.asList("Apple", "", "Banana", "", "Cherry",null);
		Predicate<String> nonNull = Objects::nonNull;
		
		List<String> nonEmptyAndNull = namesWithnonnullvalues.stream()
				.filter(nonNull)
				.filter(s->!s.isEmpty())
				.toList();
		System.out.println(nonEmptyAndNull);
		
		
		//Given a list of orders, filter those placed in 2024 AND with amount > 1000.
		List<Orders> orders = List.of(
	            new Orders("ORD01", LocalDate.of(2024, 3, 15), 1250.00), 
	            new Orders("ORD02", LocalDate.of(2024, 7, 22), 450.00),  
	            new Orders("ORD03", LocalDate.of(2023, 11, 5), 2100.00), 
	            new Orders("ORD04", LocalDate.of(2024, 12, 1), 3400.00)
	        );
		
		Predicate<Orders> justyear = orderdate -> orderdate.getOrderDate().getYear()==2024;
		
		Predicate<Orders> justamountmorethan100 = orderamount -> orderamount.getAmount() > 1000;
		
		List<Orders> filteredOrderlist = orders.stream()
				.filter(justyear.and(justamountmorethan100))
				.toList();
		
		filteredOrderlist.forEach(System.out::println);
		
		
		//Build a generic filterBy(List, Predicate) utility method using streams.
		List<Integer> numbers1 = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> evens = filterBy(numbers, n -> n % 2 == 0);
        System.out.println("Even numbers: " + evens); // [2, 4, 6]

        // Example 2: Filtering Strings (Keep only names starting with 'A')
        List<String> names1 = List.of("Aditya", "Tharun", "Dipak");
        List<String> aNames = filterBy(names, name -> name.startsWith("A"));
        System.out.println("Names starting with A: " + aNames);
	}
	
	public static <T> List<T> filterBy(List<T> list, Predicate<T> predicate) {
        // Handle potential null bounds safely
        if (list == null || predicate == null) {
            return List.of();
        }
        
        return list.stream()
                   .filter(predicate)
                   .toList(); // Returns an unmodifiable list
    }
}
