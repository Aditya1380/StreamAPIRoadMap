package stream;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import util.Employee;

public class ReduceAndCollectorsInStream {

	public static void main(String[] args) {

		List<Integer> nums = List.of(1, 2, 3, 4, 5);
		//Use reduce() to find the sum of a list of integers.
		
		int sum = nums.stream()
//				.reduce(0, Integer::sum);//both line have same logic
				.reduce(0, (a,b)->a+b);

		System.out.println(sum);
		
		
		//Use reduce() to find the product of all numbers in a list.
		int mul = nums.stream()
				.reduce(1, (a,b)->a*b);
		
		System.out.println(mul);
		
		
		//Join a List into a comma-separated string using Collectors.joining().
		List<String> sentences = List.of("Java"," is great");
		String completeSentence = sentences.stream()
				.collect(Collectors.joining(","));
		System.out.println(completeSentence);
		
		
		//Collect a stream of strings to a Set to eliminate duplicates.
		List<String> words = List.of("apple", "banana", "apple", "cherry", "banana");
		Set<String> uniquewords = words.stream()
				.collect(Collectors.toSet());
		
		System.out.println(uniquewords);
		
		
		
		//Convert a List to a Map of name→salary.
		List<Employee> employees = List.of(
				new Employee("Aditya", 60000),
	            new Employee("Tharun", 45000),
	            new Employee("Dipak", 75000),
	            new Employee("Nilu", 48000)
				);
		
		Map<String, Integer> map = employees.stream()
				.collect(Collectors.toMap(Employee::getName, Employee::getSalary));
		
		System.out.println(map);
		
		
		
		//Use Collectors.counting() to count how many elements match a condition.
		List<String> names = List.of("Aditya", "Tharun", "Dipak", "Amit");

		long countWithA = names.stream()
				.filter(name -> name.startsWith("A"))
				.collect(Collectors.counting());
		
		System.out.println(countWithA);
		
		
		//Find the maximum element in a list using reduce() without using max().
		List<Integer> randomnums = List.of(1, 2, 3, 4, 5,7,3,2,1,16);
		
		int maxnumber = randomnums.stream()
				.reduce(Integer.MIN_VALUE,(currenMax,nextNumber)->currenMax>nextNumber?currenMax:nextNumber);
		
		System.out.println(maxnumber);
		
		
		//Collect to an unmodifiable list using Collectors.toUnmodifiableList().
		List<String> mutableSource = List.of("Red", "Green", "Blue");

        // 1. Collect into a strictly unmodifiable list
        List<String> immutableList = mutableSource.stream()
                                                  .filter(color -> !color.equals("Green"))
                                                  .collect(Collectors.toUnmodifiableList());

        // 2. Print the list: [Red, Blue]
        System.out.println("Collected list: " + immutableList);

        // 3. Attempting to modify this list will cause a runtime crash
        try {
            immutableList.add("Yellow"); // This line will fail!
        } catch (UnsupportedOperationException e) {
            System.out.println("Success: Caught exception! Cannot modify an unmodifiable list.");
        }

        
        
        //Use reduce() to concatenate all strings in a list with a space separator.
        List<String> wordsforconcatenation = List.of("java","stream","are","awesome");
        
        String combinedwords = wordsforconcatenation.stream()
//        		.reduce((assembled,nextword)->assembled+" "+nextword);
        		.collect(Collectors.joining(" "));
        
        System.out.println(combinedwords);
        
        
        
        //Build a frequency map (word→count) from a list of strings using Collectors.toMap().
        List<String> fruitlist = List.of("apple", "banana", "apple", "cherry", "banana", "apple");

        // Build a frequency map using Collectors.toMap()
        Map<String, Integer> frequencyMap = fruitlist.stream()
            .collect(Collectors.toMap(
                word -> word,       // 1. Key Mapper: The word itself is the key
                word -> 1,          // 2. Value Mapper: Start the count at 1 for the first encounter
                (existingCount, newCount) -> existingCount + newCount // 3. Merge Function: Add them up on duplicate
            ));

        // Print the result: {banana=2, cherry=1, apple=3}
        System.out.println(frequencyMap);
        
        
        
        
        
        
	}
}
