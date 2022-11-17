package studentjobfinderAPI.studentjobfinder.Model;
import lombok.Getter;

import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Document("Company")

@Getter
@Setter


public class Company extends Account
{

    public Company(String email, String password, int id, Role role,String name,Date creationDate,Sector sector, String description,String website,Address address) {
        super(email, password, id, role);
        this.name = name;
        this.creationDate = creationDate;
        this.sector = sector;
        this.description = description;
        this.address = address;
        this.website = website;
     }


    private String name;
    private String description;
    private Address address;
    private Binary logo;
    private Binary banner;
    private Date creationDate;
    private  String website;
    private ArrayList<String> socialMedia;
    private Sector sector;
     @DBRef
    private List<Offer> offers;
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public Address getAddress() {
		return address;
	}
	public Binary getLogo() {
		return logo;
	}
	public Binary getBanner() {
		return banner;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public String getWebsite() {
		return website;
	}
	public ArrayList<String> getSocialMedia() {
		return socialMedia;
	}
	public Sector getSector() {
		return sector;
	}
	public List<Offer> getOffers() {
		return offers;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public void setLogo(Binary logo) {
		this.logo = logo;
	}
	public void setBanner(Binary banner) {
		this.banner = banner;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public void setSocialMedia(ArrayList<String> socialMedia) {
		this.socialMedia = socialMedia;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
     





}