package smartgate.capstonespringboot.payloads;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

	private String accessToken;
	private String tokenType = "Bearer";
	private UserAuthResponse user;
	
	public JwtAuthenticationResponse(String accessToken, UserAuthResponse user) {
	    this.accessToken = accessToken;
	    this.user = user;
	}
}
