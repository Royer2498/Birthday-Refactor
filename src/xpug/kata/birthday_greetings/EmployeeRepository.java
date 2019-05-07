package xpug.kata.birthday_greetings;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public interface EmployeeRepository {
	
	ArrayList<Employee> getEmployees() throws FileNotFoundException, IOException, ParseException;

}
