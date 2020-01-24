package smartgate.capstonespringboot.payloads;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import smartgate.capstonespringboot.security.UserPrincipal;
@Data
public class UserAuthResponse {
	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	private Collection<? extends GrantedAuthority> roles;
	

	public  UserAuthResponse(UserPrincipal userPrincipal) {
		this.id = userPrincipal.getId();
		this.email = userPrincipal.getEmail();
		this.firstName = userPrincipal.getFirstName();
		this.lastName = userPrincipal.getLastName();
		this.roles = userPrincipal.getAuthorities();

	}
}
