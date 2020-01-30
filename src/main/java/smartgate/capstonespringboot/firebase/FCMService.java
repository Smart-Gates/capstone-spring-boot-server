package smartgate.capstonespringboot.firebase;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.*;

import smartgate.capstonespringboot.models.PushNotificationParameter;
import smartgate.capstonespringboot.payloads.FCMPushNotificationRequest;

@Service
public class FCMService {

	Logger logger = LoggerFactory.getLogger(FCMInitializer.class);

	public void sendMessage(Map<String, String> data, FCMPushNotificationRequest request)
			throws InterruptedException, ExecutionException {
		Message message = getPreconfiguredMessageWithData(data, request);
		String response = sendAndGetResponse(message);
		logger.info("Sent message with data. Topic: " + request.getTopic() + ", " + response);
	}

	public void sendMessageWithoutData(FCMPushNotificationRequest request)
			throws InterruptedException, ExecutionException {
		//Message message = getPreconfiguredMessageWithoutData(request);
		
		
		String registrationToken = "";

		// See documentation on defining a message payload.
		Message message = Message.builder()
				.setNotification(new Notification(
				        "Push Reminder!",
				        "This is some push reminder text!"))
		    .setToken(registrationToken)
		    .build();

		
		String response = sendAndGetResponse(message);
		logger.info("Sent message without data. Topic: " + request.getTopic() + ", " + response);
	}

	public void sendMessageToToken(FCMPushNotificationRequest request) throws InterruptedException, ExecutionException {
		Message message = getPreconfiguredMessageToToken(request);
		String response = sendAndGetResponse(message);
		logger.info("Sent message to token. Device token: " + request.getToken() + ", " + response);
	}

	private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
		return FirebaseMessaging.getInstance().sendAsync(message).get();
	}

	private AndroidConfig getAndroidConfig(String topic) {
		return AndroidConfig.builder().setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
				.setPriority(AndroidConfig.Priority.HIGH)
				.setNotification(AndroidNotification.builder().setSound(PushNotificationParameter.SOUND.getValue())
						.setColor(PushNotificationParameter.COLOR.getValue()).setTag(topic).build())
				.build();
	}

	private ApnsConfig getApnsConfig(String topic) {
		return ApnsConfig.builder().setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
	}

	private Message getPreconfiguredMessageToToken(FCMPushNotificationRequest request) {
		return getPreconfiguredMessageBuilder(request).setToken(request.getToken()).build();
	}

	private Message getPreconfiguredMessageWithoutData(FCMPushNotificationRequest request) {
		return getPreconfiguredMessageBuilder(request).setTopic(request.getTopic()).build();
	}

	private Message getPreconfiguredMessageWithData(Map<String, String> data, FCMPushNotificationRequest request) {
		return getPreconfiguredMessageBuilder(request).putAllData(data).setTopic(request.getTopic()).build();
	}

	private Message.Builder getPreconfiguredMessageBuilder(FCMPushNotificationRequest request) {
		AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
		ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
		return Message.builder().setApnsConfig(apnsConfig).setAndroidConfig(androidConfig)
				.setNotification(new Notification(request.getTitle(), request.getMessage()));
	}

}