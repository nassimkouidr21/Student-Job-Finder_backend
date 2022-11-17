package studentjobfinderAPI.studentjobfinder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import studentjobfinderAPI.studentjobfinder.Model.*;
import studentjobfinderAPI.studentjobfinder.Repository.CompanyRepository;
import studentjobfinderAPI.studentjobfinder.Repository.OfferRepository;
import studentjobfinderAPI.studentjobfinder.Repository.StudentRepository;

import java.time.Year;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    StudentService studentService;
    @Autowired
    StudentRepository studentRepository;
    @Autowired 
	OfferRepository offerRepository;

    public String register(String email, String password, int id , Role role, String name , Date creationDate, Sector sector, String description , String website , Address address  ) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(email);
        if (matcher.matches() && !isEmailUsed(email) && !isEmailUsedS(email) ) {
            if( role == Role.COMPANY) {
                Company newCompany = new Company(email, password,id , role,name,creationDate,sector,description , website,address);
                newCompany.setPassword(new BCryptPasswordEncoder().encode(newCompany.getPassword()));
                companyRepository.save(newCompany);
                return "Company created successfully";
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
        Account account = companyRepository.findCompanyByEmail(email);
        if(account.getPassword().equals(password)){
            return true;
        }
        return false;
    }
    public void addOffer(Offer offer, Company company) {
        List <Offer> offers = company.getOffers();
        offers.add(offer);
        company.setOffers(offers);
    }

    public void createOffer(String token, Offer offer) {
        Company company = getCompanyFromToken(token);
        addOffer(offer, company);
        companyRepository.save(company);
        offerRepository.save(offer);
    }



    public Company getCompanyFromToken(String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        int endEmail = payload.indexOf(",");
        String companyEmail = payload.substring(8, endEmail-1);
        return companyRepository.findCompanyByEmail(companyEmail);
    }



}



