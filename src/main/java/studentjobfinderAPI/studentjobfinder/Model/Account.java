package studentjobfinderAPI.studentjobfinder.Model;


    import org.springframework.data.annotation.Id;
    import org.springframework.data.mongodb.core.index.Indexed;
    import org.springframework.data.mongodb.core.mapping.DBRef;
    import org.springframework.data.mongodb.core.mapping.Document;
    import org.springframework.format.annotation.DateTimeFormat;


@Document("Account")
public class Account {

    @Id
    private Integer id;
    @Indexed(unique = true)
    private String email;
    private String password;
    
    private Role role;



    public Account(String email, String password, int id , Role role) {
        this.email = email;
        this.password = password;
        this.id = id;
        this.role = role;
    }


    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public int getId() {
        return id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setId(int id) {
        this.id = id;
    }
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
