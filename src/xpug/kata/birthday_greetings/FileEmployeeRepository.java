package xpug.kata.birthday_greetings;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class FileEmployeeRepository implements EmployeeRepository {

	String fileName;
	public FileEmployeeRepository(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public ArrayList<Employee> getEmployees() throws IOException, ParseException {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		BufferedReader buffer = new BufferedReader(new FileReader(this.fileName));
		String str = "";
		str = buffer.readLine();
		while ((str = buffer.readLine()) != null) {
			String[] employeeData = str.split(", ");
			Employee employee = new Employee(employeeData[1], employeeData[0],employeeData[2], employeeData[3]);
			employees.add(employee);
		}
		return employees;
	}
	
}

