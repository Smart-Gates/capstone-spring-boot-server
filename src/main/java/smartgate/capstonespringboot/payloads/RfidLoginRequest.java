package smartgate.capstonespringboot.payloads;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RfidLoginRequest {
	
	@NotBlank
    private String tag;

}
