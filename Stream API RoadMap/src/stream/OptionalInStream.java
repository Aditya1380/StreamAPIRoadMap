package stream;

import java.util.List;
import java.util.Optional;

import util.City;
import util.Department;
import util.EmployeeforOptionalExercise;
import util.Office;

public class OptionalInStream {

	public static void main(String[] args) {
		
		//Create an Optional from a nullable string and print it safely.
		Optional<String> name = Optional.of("Aditya");		
		name.ifPresent(System.out::println);
		
		//Use orElse() to return a default value when Optional is empty.
		List<String> items = List.of("Laptop", "Mouse", "Keyboard");

        Optional<String> foundItem = items.stream()
            .filter(item -> item.equals("Mouse"))
            .findFirst();

        String result1 = foundItem.orElse("Default Utility Item");
        System.out.println("Result 1: " + result1);
		
		
		//Use map() on Optional to transform its value if present.
        Optional<String> presentUser = Optional.of("aditya_tech");

        Optional<String> presentEmail = presentUser.map(username -> username + "@company.com");
        
        System.out.println(presentEmail);
        
        //Use orElseThrow() to throw a custom exception when Optional is empty.
        List<String> employees = List.of("Aditya", "Tharun", "Dipak");

        //  Match found (Executes cleanly)
        String foundEmp = employees.stream()
            .filter(emp -> emp.equals("Aditya"))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Employee not found"));

        System.out.println("Found: " + foundEmp);
        
        
        //Chain map() calls on Optional to safely navigate a nested object graph.
        
        
        City mumbai = new City("Mumbai");
        Office headOffice = new Office(mumbai);
        Department techDept = new Department(headOffice);
        EmployeeforOptionalExercise employee = new EmployeeforOptionalExercise(techDept);

        // Scenario 1: Successful Safe Navigation
        String city1 = Optional.ofNullable(employee)
            .map(EmployeeforOptionalExercise::getDepartment)  // Extracts Department
            .map(Department::getOffice)    // Extracts Office
            .map(Office::getCity)          // Extracts City
            .map(City::getCityName)        // Extracts String "Mumbai"
            .orElse("Unknown City");

        System.out.println("Employee 1 City: " + city1);

        
        
        //Use filter() on Optional to discard values not matching a condition.
        Optional<Double> largeTransaction = Optional.of(1500.0);
        
        Optional<Double> processed1 = largeTransaction.filter(amount -> amount > 1000.0);
        
        System.out.println("Result: " + processed1.orElse(0.0));
        
        
        //Use ifPresentOrElse() (Java 9) to handle both present and empty cases.
        List<String> activeUsers = List.of("Aditya","Dipak","Tharun");
        Optional<String> match1 = activeUsers.stream()
        				.filter(user -> user.equals("Aditya"))
        				.findFirst();
        
        System.out.print("Search : ");
        match1.ifPresentOrElse(
            name1 -> System.out.println("Access GRANTED for user: " + name1), // Runs if present
            () -> System.out.println("Access DENIED: User profile not found") // Runs if empty
        );
        
        //Parse a string to Integer safely using Optional and return -1 on failure.
        String validRawData = "  42  ";
        String corruptedData = "42G";

        // Scenario 1: Successful Parse
        int result01 = tryParseInt(validRawData).orElse(-1);
        System.out.println("Result 1: " + result01); // Output: 42

        // Scenario 2: Corrupted String (Triggers Fallback)
        int result02 = tryParseInt(corruptedData).orElse(-1);
        System.out.println("Result 2: " + result02); // Output: -1

        //Given a list of Optional, filter present values and collect to a List.
        List<Optional<String>> optionalList = List.of(
                Optional.of("Aditya"),
                Optional.empty(),
                Optional.of("Dipak"),
                Optional.empty(),
                Optional.of("Tharun")
            );
            
            List<String> presentValues = optionalList.stream()
                .flatMap(Optional::stream) 
                .toList();

            System.out.println(presentValues);
       
	}
	public static Optional<Integer> tryParseInt(String input) {
        try {
            return Optional.of(Integer.parseInt(input.trim()));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
