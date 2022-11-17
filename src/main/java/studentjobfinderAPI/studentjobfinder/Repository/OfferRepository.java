package studentjobfinderAPI.studentjobfinder.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import studentjobfinderAPI.studentjobfinder.Model.Company;
import studentjobfinderAPI.studentjobfinder.Model.Offer;

public interface OfferRepository extends MongoRepository<Offer, Integer> {

	
}
