package smartgate.capstonespringboot.payloads;

import lombok.Data;

@Data
public class FCMImageNotificationRequest {

	private String title;
	private String message;
	private String topic;
	private String imageData;
	private String email;

	public FCMImageNotificationRequest() {
	}

	public FCMImageNotificationRequest(String title, String messageBody, String topic, String imageData, String email) {
		this.title = title;
		this.message = messageBody;
		this.topic = topic;
		this.imageData = imageData;
		this.email = email;
	}
}