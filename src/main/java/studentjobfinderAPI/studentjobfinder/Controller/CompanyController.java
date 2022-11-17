package studentjobfinderAPI.studentjobfinder.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import studentjobfinderAPI.studentjobfinder.Model.Company;
import studentjobfinderAPI.studentjobfinder.Model.Offer;
import studentjobfinderAPI.studentjobfinder.Model.Role;
import studentjobfinderAPI.studentjobfinder.Payload.Request.RegisterRequest;
import studentjobfinderAPI.studentjobfinder.Repository.CompanyRepository;
import studentjobfinderAPI.studentjobfinder.Repository.OfferRepository;
import studentjobfinderAPI.studentjobfinder.Service.StudentService;
import studentjobfinderAPI.studentjobfinder.Service.CompanyService;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/public")
public class CompanyController {
	@Autowired
	CompanyService companyService;
	@Autowired
	CompanyRepository companyRepository;


	@Autowired
	StudentService studentService;


	@GetMapping("/find")

	public List<Company> companies() {
		return   companyRepository.findAll();
	}

	@PostMapping("/register/company")

	public String registerCompany(@RequestBody RegisterRequest registerRequest ) {
		return companyService.register(registerRequest.getEmail(), registerRequest.getPassword(),(int) companyRepository.count() , Role.COMPANY, registerRequest.getName() , registerRequest.getCreationDate(), registerRequest.getSector() , registerRequest.getDescription(), registerRequest.getWebsite(), registerRequest.getAddress());
	}

	@PostMapping("/offers/create")
	public void createOffer(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, Offer offer) {
		companyService.createOffer(token, offer);
	}



	@GetMapping("/getCompany")
	public Company getCompany(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, HttpServletResponse response) {
		return companyService.getCompanyFromToken(token);
	}


}
