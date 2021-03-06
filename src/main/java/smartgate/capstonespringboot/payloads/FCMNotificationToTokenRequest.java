package smartgate.capstonespringboot.payloads;

import lombok.Data;

@Data
public class FCMNotificationToTokenRequest {

	private String title;
	private String message;
	private String topic;
	private String token;
	private String imageUrl;

	public FCMNotificationToTokenRequest() {
	}

	public FCMNotificationToTokenRequest(String title, String messageBody, String topic, String token, String imageUrl) {
		this.title = title;
		this.message = messageBody;
		this.topic = topic;
		this.token = token;
		this.imageUrl = imageUrl;
	}
}