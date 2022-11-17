package studentjobfinderAPI.studentjobfinder.Model;


import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Sector {

    @org.springframework.data.annotation.Id
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}