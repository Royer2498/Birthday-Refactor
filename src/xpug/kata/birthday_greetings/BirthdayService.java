package xpug.kata.birthday_greetings;
import static java.util.Arrays.asList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class BirthdayService {
	
	int numberOfGreetingsSent;
	EmployeeRepository employeeRepository;
	EmailService mail;
	
	public BirthdayService(EmployeeRepository employeeRepository,EmailService mail) {
		this.employeeRepository = employeeRepository;
		this.mail = mail;
	}

	public void sendGreetings(OurDate ourDate) throws IOException, ParseException, AddressException, MessagingException {
		numberOfGreetingsSent = 0;
		ArrayList<Employee> employees = this.employeeRepository.getEmployees();
		for(int i=0;i<employees.size();i++) {
			if (employees.get(i).isBirthday(ourDate)) {
				this.mail.sendMessage("sender@here.com", employees.get(i));
				numberOfGreetingsSent++;
			}	
		}
	}
	
	public static void main(String[] args) {
		EmailService mail = new SMTPMailService("localhost", 25); 
		EmployeeRepository employeeRepository = new FileEmployeeRepository("employee_data.txt");
		BirthdayService service = new BirthdayService(employeeRepository,mail);
		try {
			service.sendGreetings(new OurDate("2008/10/08"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int quantityOfGreetingsSent() {
		return numberOfGreetingsSent;
	}
}
