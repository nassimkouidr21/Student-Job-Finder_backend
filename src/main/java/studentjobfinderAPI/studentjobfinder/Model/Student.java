package studentjobfinderAPI.studentjobfinder.Model;


import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Document("Student")
public class Student extends Account {
public Student(String email, String password, int id, Role role,String name, String fname, Date birthDate, Long phoneNumber) {
    super(email, password, id,role);
    this.name = name;
    this.fname = fname;
    this.birthDate = birthDate;
    this.phoneNumber = phoneNumber;
}



    private String name;
    private String fname;
    private Date birthDate;
    private String biogrpahy;
    private Binary photo;
    private Long phoneNumber;
    private String area;
    private Level level;
    private  ArrayList<Long> note;

    @DBRef
    private Sector sector;


    //add fav offers
}

