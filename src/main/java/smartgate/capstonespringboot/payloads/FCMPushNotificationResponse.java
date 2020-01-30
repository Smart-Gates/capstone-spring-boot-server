package smartgate.capstonespringboot.payloads;

import lombok.Data;

@Data
public class FCMPushNotificationResponse {

	private int status;
	private String message;

	public FCMPushNotificationResponse() {
	}

	public FCMPushNotificationResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
