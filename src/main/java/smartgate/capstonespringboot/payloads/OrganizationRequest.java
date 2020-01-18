package smartgate.capstonespringboot.payloads;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class OrganizationRequest {

	@NotBlank
	private String name;

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

	private Long acct_mngr_id;
}
