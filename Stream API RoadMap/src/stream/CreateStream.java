package stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreateStream {

	public static void main(String[] args) {

		// Print List of String using stream API
		List<String> names = List.of("Aditya", "Tharun", "Dipak");

		Stream<String> s = names.stream();
		s.forEach(name -> System.out.println(name));

		// Print List of Integer using stream API
		List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
		Stream<Integer> s1 = nums.stream();

		s1.forEach(num -> System.out.println(num));

		// Find the sum of Array using IntStream
		int[] nums1 = { 5, 12, 3, 8, 21 };
		IntStream numStream = Arrays.stream(nums1);

		int totalSum = numStream.sum();

		System.out.println("The Sum of the arrays is " + totalSum);

		// Use Stream.of() to create a stream of 5 strings and collect to a List.
		Stream<String> fruitStream = Stream.of("Apple", "Banana", "Cherry", "Date", "Elderberry");

		List<String> fruitList = fruitStream.collect(Collectors.toList());
		System.out.println(fruitList);

		Stream s2 = Stream.iterate(0, n -> n + 2).limit(10);
		List<Integer> evenStream = s2.toList();
		System.out.println(evenStream);

		// Use IntStream.range() to print numbers 1 to 20.
		IntStream.range(1, 21).forEach(System.out::println);

		// Generate 5 random doubles using Stream.generate() and collect them.
		Stream s3 = Stream.generate(Math::random).limit(5);
		List<Double> randomdoubles = (List<Double>) s3.collect(Collectors.toList());
		System.out.println(randomdoubles);

		// Create a stream from a Map's entry set and print key=value pairs.
		Map<String, Integer> inventory = new HashMap<String, Integer>();
		inventory.put("Apple", 50);
		inventory.put("Banana", 100);
		inventory.put("Grapes", 70);
		inventory.put("pineapple", 30);

		inventory.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + "=" + entry.getValue()));

		// Use Stream.builder() to dynamically add elements and build a stream.
		Stream.Builder<String> cityBuilder = Stream.builder();
		cityBuilder.add("Mumbai");
		cityBuilder.add("New York");
		cityBuilder.add("London");

		cityBuilder.add("Tokyo").add("Paris");

		Stream<String> cityStream = cityBuilder.build();

		cityStream.forEach(System.out::println);

		// Create an infinite Fibonacci stream using Stream.iterate() with two seeds
		Stream.iterate(new int[] { 0, 1 }, pair -> new int[] { pair[1],pair[0] + pair[1] }).map(pair -> pair[0]).limit(10)
				.forEach(System.out::println);

		
		//Create a stream from lines of a file using Files.lines() and count total words.
		Path filePath = Paths.get("C:\\Users\\adityat\\Desktop\\sample.txt");
		
		try(Stream<String> lines = Files.lines(filePath)){
			
			long totalWords = lines
					.filter(line-> !line.trim().isEmpty())
					.flatMap(line->Stream.of(line.split("//s+")))
					.count();
			
			System.out.println("Total words in the file: " + totalWords);
			
		}catch(IOException e) {
			System.err.println("Error reading the files: "+e.getMessage());
		}
		
	}
}
