package studentjobfinderAPI.studentjobfinder.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import studentjobfinderAPI.studentjobfinder.Model.Company;
import studentjobfinderAPI.studentjobfinder.Model.Student;

public interface StudentRepository extends MongoRepository<Student,Integer>{

    @Query("{email: '?0'}")
   Student findStudentByEmail(String email);
}
