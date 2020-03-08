package smartgate.capstonespringboot.payloads;

import lombok.Data;

@Data
public class GeneralResponse {

	private int status;
	private String message;

	public GeneralResponse() {
	}

	public GeneralResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
