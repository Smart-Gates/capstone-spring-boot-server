package smartgate.capstonespringboot.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NaturalId
	@NotBlank
	@Size(max = 64)
	@Email
	private String email;

	@JsonIgnore
	@NotBlank
	@Size(max = 128)
	private String password;

	@NotBlank
	@Size(max = 64)
	private String firstName;

	@NotBlank
	@Size(max = 64)
	private String lastName;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))

	private Set<Role> roles = new HashSet<>();

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "acct_mngr")
	private Organization acctManager;

	// OnDeleteAction will delete all the users with the associated organization if
	// the organization is deleted
	@Nullable
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Organization organization;
	
	@ManyToMany( mappedBy="attendees")
	@JsonBackReference
	private List<Event> events;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fcm_token_id", referencedColumnName = "id")
    private FCMToken fcm_token;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Rfid rfid;

	public User() {

	}

	public User(String email, String password, String firstName, String lastName) {

		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public boolean hasRole(RoleName roleName) {
		boolean hasRole = false;
		Set<Role> myRoles = getRoles();
		List<Role> list = new ArrayList<>(myRoles);
		
		for (Role r : list) {
			if(r.getName().equals(roleName)) {
				hasRole = true;
				break;
			}
		}			
		
		return hasRole;	
	}

}
