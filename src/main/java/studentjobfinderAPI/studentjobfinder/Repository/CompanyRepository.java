package studentjobfinderAPI.studentjobfinder.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import studentjobfinderAPI.studentjobfinder.Model.Company;

public interface CompanyRepository extends MongoRepository<Company, Integer> {

 @Query("{email: '?0'}")
    Company findCompanyByEmail(String email);
}
