package smartgate.capstonespringboot.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import lombok.Data;
@Data
@Entity
@Table(name = "users", uniqueConstraints = {
	@UniqueConstraint(columnNames = {
	    "email"
	})
})

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NaturalId
	@NotBlank
	@Size(max = 64)
	@Email
	private String email;
	
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
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
   
	private Set<Role> roles = new HashSet<>();
	
	
	 @OneToOne(mappedBy = "organization")
	    private User user;

    public User() {

    }

    public User(String email, String password, String firstName, String lastName) {
       
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
}
