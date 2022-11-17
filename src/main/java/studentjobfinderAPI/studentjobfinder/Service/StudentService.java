package studentjobfinderAPI.studentjobfinder.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import studentjobfinderAPI.studentjobfinder.Model.*;
import studentjobfinderAPI.studentjobfinder.Repository.CompanyRepository;
import studentjobfinderAPI.studentjobfinder.Repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	StudentRepository studentRepository;

	public String register(String email, String password, int id , Role role, String name, String fname, Date birthDate, Long phoneNumber) {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
		Pattern pattern = Pattern.compile(regex);
		java.util.regex.Matcher matcher = pattern.matcher(email);
		if (matcher.matches() && !isEmailUsed(email) && !isEmailUsedS(email) ) {
		if( role == Role.STUDENT) {

				Student newStudent = new Student(email, password, id,role,name,fname,birthDate,phoneNumber);
				newStudent.setPassword(new BCryptPasswordEncoder().encode(newStudent.getPassword()));
				studentRepository.save(newStudent);
				return "Student created successfully";
			}
		}
		if (matcher.matches()) {
			if(isEmailUsedS(email) || isEmailUsed(email) ) {
				return "The email you entered is already linked to an account";
			}
		}
		return "Please enter a valid email address";
	}




	public boolean isEmailUsed(String email) {
		List<Company> companies = companyRepository.findAll();
		for (int i=0; i<companies.size(); i++ ) {
			if (companies.get(i).getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
	public boolean isEmailUsedS(String email) {
		List <Student> students = studentRepository.findAll();
		for (int i=0; i<students.size(); i++ ) {
			if (students.get(i).getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkCredentials(String email, String password) {
		Account account = studentRepository.findStudentByEmail(email);
		if(account.getPassword().equals(password)){
			return true;
		}
		return false;
	}



}