package smartgate.capstonespringboot.models;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "organization")
public class Organization {

	private @Id @GeneratedValue Long id;

	@NotBlank
	private String name;
	
	@NotBlank
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "head_id", referencedColumnName = "id")
	private User user;
	
	@NotBlank
	private String street_address;
	@NotBlank
	private String zip;
	@NotBlank
	private String city;
	@NotBlank
	private String province_state;
	@NotBlank
	private String country;

	Organization() {
	}

	public Organization(String name, User user, String street_address, String zip, 
			String city, String province_state, String country) {
		this.name = name;
		this.user = user;
		this.street_address = street_address;
		this.zip = zip;
		this.city = city;
		this.province_state = province_state;
		this.country = country;

	}
}