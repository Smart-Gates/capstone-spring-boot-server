package smartgate.capstonespringboot.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.NaturalId;

@Entity
@Data
@Table(name = "organization")
public class Organization {

	private @Id @GeneratedValue Long id;

	@NaturalId
	@NotBlank
	private String name;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "acct_mngr_id", nullable = false)
	private User acct_mngr;

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

	public Organization(String name, User user, String street_address, String zip, String city, String province_state,
			String country) {
		this.name = name;
		this.acct_mngr = user;
		this.street_address = street_address;
		this.zip = zip;
		this.city = city;
		this.province_state = province_state;
		this.country = country;

	}
}