package studentjobfinderAPI.studentjobfinder.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("Offers")
public class Offer {
       @Id
    private Integer id;
       private String title;
        private offerStatus status;
        private Integer nbrPlaces;
        private String context;
        private String mission;
        private String searchedProfile;
        private Address address;
        private Language language;
        @DBRef
        private List<Student> candidates;
        private double salary;
        private contratType contract;
        private boolean remoteWork;
        private  Level level;
        private Date startDate;
        private Date endDate;
        private String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        @DBRef
        private List<Question> questions;
        @DBRef
         private Company company;
        @DBRef
         private  Sector sector;
}
