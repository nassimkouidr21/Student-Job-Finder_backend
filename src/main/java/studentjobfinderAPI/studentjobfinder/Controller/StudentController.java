package studentjobfinderAPI.studentjobfinder.Controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import studentjobfinderAPI.studentjobfinder.Configuration.JwtTokenUtil;
import studentjobfinderAPI.studentjobfinder.Model.Company;
import studentjobfinderAPI.studentjobfinder.Model.Role;
import studentjobfinderAPI.studentjobfinder.Model.Student;
import studentjobfinderAPI.studentjobfinder.Payload.Request.LoginRequest;
import studentjobfinderAPI.studentjobfinder.Payload.Request.RegisterRequest;
import studentjobfinderAPI.studentjobfinder.Repository.CompanyRepository;
import studentjobfinderAPI.studentjobfinder.Repository.StudentRepository;
import studentjobfinderAPI.studentjobfinder.Service.StudentService;
@RestController
@RequestMapping("/api/public")
public class StudentController {


	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	Logger logger = LoggerFactory.getLogger(StudentController.class);
	@Autowired
	StudentService studentService;
	@Autowired
	StudentRepository studentRepository;
	@Autowired 
	CompanyRepository companyRepository;


	@PostMapping("/register/student")

	public String register(@RequestBody RegisterRequest registerRequest ) {
		return studentService.register(registerRequest.getEmail(), registerRequest.getPassword(),  (int) studentRepository.count() , Role.STUDENT , registerRequest.getName(),registerRequest.getFname(),registerRequest.getBirthDate(),registerRequest.getPhoneNumber());
	}






	@PostMapping("/login")
	public String login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
		Role userRole = null;
		List<Student> students = studentRepository.findAll();
		List<Company> companies = companyRepository.findAll();
		BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
		for (int i=0;i<students.size();i++) {
			if(students.get(i).getEmail().equals(loginRequest.getEmail())) {
				userRole= Role.STUDENT;
			}
		}
		for (int i=0;i<companies.size();i++) {
			if(companies.get(i).getEmail().equals(loginRequest.getEmail())) {
				userRole= Role.COMPANY;
			}
		}

		if(userRole==Role.STUDENT){
			if(encoder.matches(loginRequest.getPassword(), studentRepository.findStudentByEmail(loginRequest.getEmail()).getPassword())) {
				String token = jwtTokenUtil.generateAccessToken(studentRepository.findStudentByEmail(loginRequest.getEmail()));
				logger.info("Token is : " + token);
				return token;
			}
			else {
				return "Wrong email or password";
			}
		}
		if(userRole==Role.COMPANY){
			if(encoder.matches(loginRequest.getPassword(), companyRepository.findCompanyByEmail(loginRequest.getEmail()).getPassword())) {
				String token = jwtTokenUtil.generateAccessToken(companyRepository.findCompanyByEmail(loginRequest.getEmail()));
				logger.info("Token is : " + token);
				return (token);
			}
		}
		System.out.println(userRole);
		return "Wrong email or password";
	}


}
