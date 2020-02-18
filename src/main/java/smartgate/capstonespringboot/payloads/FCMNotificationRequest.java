package smartgate.capstonespringboot.payloads;

import java.util.List;

import lombok.Data;

@Data
public class FCMNotificationRequest {

	private String title;
	private String message;
	private String topic;
	private List<String> emails;

	public FCMNotificationRequest() {
	}

	public FCMNotificationRequest(String title, String messageBody, String topic, List<String> emails) {
		this.title = title;
		this.message = messageBody;
		this.topic = topic;
		this.emails = emails;
	}
}