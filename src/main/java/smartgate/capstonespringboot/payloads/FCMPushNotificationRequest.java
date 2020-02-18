package smartgate.capstonespringboot.payloads;

import lombok.Data;

@Data
public class FCMPushNotificationRequest {

	private String title;
	private String message;
	private String topic;
	private String token;

	public FCMPushNotificationRequest() {
	}

	public FCMPushNotificationRequest(String title, String messageBody, String topic, String token) {
		this.title = title;
		this.message = messageBody;
		this.topic = topic;
		this.token = token;
	}
}