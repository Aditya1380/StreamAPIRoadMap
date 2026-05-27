package stream;

import java.util.Comparator;
import java.util.List;

import util.EmployeeGroupingByAndPartioningByExercise;
import util.Transaction;

public class SortDistinctLimitAndSkipInStream {

	/* These are your stream pipeline controls.
	 * After groupingBy() you often need to sort results. 
	 * Combining Comparator.comparing().thenComparing() is essential for multi-field sorting — used everywhere in real projects.*/
	public static void main(String[] args) {
		
		List<Integer> nums = List.of(9,8,7,6,5,4,3,2,1);
		
		//Sort a list of integers in ascending order using sorted().
		List<Integer> sortednums = nums.stream()
				.sorted()
				.toList();
		
		System.out.println(sortednums);
		
		
		//Sort a list of strings alphabetically and collect to a list.		
		List<String> names = List.of("beta","gamma","Aditya","alpha");
		List<String> sortednames = names.stream()
				.sorted()
				.toList();
		System.out.println(sortednames);
		
		
		//Remove duplicate integers from a list using distinct().
		List<Integer> duplicatenums = List.of(9,9,6,6,4,4,3,1,1);
		
		List<Integer> distinctnums = duplicatenums.stream()
				.sorted()
				.distinct()
				.toList();
		System.out.println(distinctnums);
		
		
		//Get the first 5 elements of a stream using limit().
		List<Integer> firstfiveelement = nums.stream()
				.limit(5)
				.toList();
		System.out.println(firstfiveelement);
		
		
		//Skip the first 3 elements and collect the rest using skip().
		List<Integer> skip3element = nums.stream()
				.skip(3)
				.toList();
		
		System.out.println(skip3element);
		
		
		//Sort a List by salary in descending order.
		List<EmployeeGroupingByAndPartioningByExercise> employeewithsalary = List.of(
	            new EmployeeGroupingByAndPartioningByExercise("Dipak", "Technology", 60000),
	            new EmployeeGroupingByAndPartioningByExercise("Tharun", "Marketing", 45000),
	            new EmployeeGroupingByAndPartioningByExercise("Aditya", "Technology", 80000),
	            new EmployeeGroupingByAndPartioningByExercise("Nilu", "Finance", 55000),
	            new EmployeeGroupingByAndPartioningByExercise("Amit", "Marketing", 55000)
	        );
		
		List<EmployeeGroupingByAndPartioningByExercise> employeesalaryinreverse = employeewithsalary.stream()
				.sorted(Comparator.comparingDouble(EmployeeGroupingByAndPartioningByExercise::getSalary).reversed())
				.toList();
		
		System.out.println(employeesalaryinreverse);
				
		
		
		//Sort employees by department name, then by salary descending within each department.
		Comparator<EmployeeGroupingByAndPartioningByExercise> employeesalaryinnameandsalary = Comparator.comparing(EmployeeGroupingByAndPartioningByExercise::getDepartment)
				.thenComparing(Comparator.comparingDouble(EmployeeGroupingByAndPartioningByExercise::getSalary)).reversed();
		
		List<EmployeeGroupingByAndPartioningByExercise> sortedEmployees = employeewithsalary.stream()
	            .sorted(employeesalaryinnameandsalary)
	            .toList();

	        sortedEmployees.forEach(System.out::println);
		
		// Implement pagination: given page number and size, return the correct slice.
	        List<String> dataset = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");

	        int size = 3;
	        System.out.println("Page 1: " + getPageSlice(dataset, 1, size));
	        System.out.println("Page 2: " + getPageSlice(dataset, 2, size));
	        System.out.println("Page 3: " + getPageSlice(dataset, 3, size));
	        System.out.println("Page 4: " + getPageSlice(dataset, 4, size));
	        
	        
	        //Find the top 3 highest-paid employees using sorted + limit.
	        
	        List<EmployeeGroupingByAndPartioningByExercise> employeesalarytop3 = employeewithsalary.stream()
					.sorted(Comparator.comparingDouble(EmployeeGroupingByAndPartioningByExercise::getSalary).reversed())
					.limit(3)
					.toList();
	        
	        
	        System.out.println(employeesalarytop3);
	        
	        
	        
	        //Given a stream of transactions, get distinct customers sorted by name, skip first 2.	       
	        List<Transaction> transactions = List.of(
	                new Transaction("Aditya", 150.0),
	                new Transaction("Tharun", 200.0),
	                new Transaction("Aditya", 50.0),  // Duplicate customer
	                new Transaction("Dipak", 350.0),
	                new Transaction("Neha", 120.0),
	                new Transaction("Amit", 90.0)
	            );
	        
	        List<String> customername = transactions.stream()
	        		.map(Transaction::getCustomerName)
	        		.distinct()
	        		.sorted()
	        		.skip(2)
	        		.toList();
	        
	        
	        System.out.println(customername);
        
	}
	
	public static <T> List<T> getPageSlice(List<T> sourceList, int pageNumber, int pageSize) {
        // Defensive checks: return empty list if input is null, empty, or parameters are invalid
        if (sourceList == null || sourceList.isEmpty() || pageNumber < 1 || pageSize < 1) {
            return List.of();
        }

        // Calculate the starting offset using your hint formula
        long offset = (long) (pageNumber - 1) * pageSize;

        return sourceList.stream()
                         .skip(offset)    // Jump past the records from previous pages
                         .limit(pageSize) // Truncate the stream to match the page size
                         .toList();
    }
}
