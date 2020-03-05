package smartgate.capstonespringboot.firebase;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.messaging.*;

import smartgate.capstonespringboot.models.PushNotificationParameter;
import smartgate.capstonespringboot.payloads.FCMNotificationToTokenRequest;

@Configuration
@PropertySource("classpath:application.properties")
@Service
public class FCMService {
	@Autowired
	private Environment env;

	Logger logger = LoggerFactory.getLogger(FCMInitializer.class);

	public void sendMessage(Map<String, String> data, FCMNotificationToTokenRequest request)
			throws InterruptedException, ExecutionException {
		Message message = getPreconfiguredMessageWithData(data, request);
		String response = sendAndGetResponse(message);
		logger.info("Sent message with data. Topic: " + request.getTopic() + ", " + response);
	}

	public void sendMessageWithoutData(FCMNotificationToTokenRequest request)
			throws InterruptedException, ExecutionException {
		// Message message = getPreconfiguredMessageWithoutData(request);

		String registrationToken = "";

		// See documentation on defining a message payload.
		Message message = Message.builder()
				.setNotification(new Notification("Push Reminder!", "This is some push reminder text!"))
				.setToken(registrationToken).build();

		String response = sendAndGetResponse(message);
		logger.info("Sent message without data. Topic: " + request.getTopic() + ", " + response);
	}

	public void sendMessageToToken(FCMNotificationToTokenRequest request)
			throws InterruptedException, ExecutionException {
		Message message = getPreconfiguredMessageToToken(request);
		String response = sendAndGetResponse(message);
		logger.info("Sent message to token. Device token: " + request.getToken() + ", " + response);
	}

	public void sendMessageToEmail(FCMNotificationToTokenRequest request)
			throws InterruptedException, ExecutionException {
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

	private Message getPreconfiguredMessageToToken(FCMNotificationToTokenRequest request) {
		return getPreconfiguredMessageBuilder(request).setToken(request.getToken()).build();
	}

	private Message getPreconfiguredMessageWithData(Map<String, String> data, FCMNotificationToTokenRequest request) {
		return getPreconfiguredMessageBuilder(request).putAllData(data).setTopic(request.getTopic()).build();
	}

	private Message.Builder getPreconfiguredMessageBuilder(FCMNotificationToTokenRequest request) {
		AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
		ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
		return Message.builder().setApnsConfig(apnsConfig).setAndroidConfig(androidConfig)
				.setNotification(new Notification(request.getTitle(), request.getMessage()));
	}

	public String uploadImageToFirebase(String imageData) {
		// create name for the file based on current time
		String fileName = Long.toString(new Date().getTime());
		// FirebaseApp fireApp = FirebaseApp.initializeApp();
		StorageClient storageClient = StorageClient.getInstance();
		String blobString = "notification_images/" + fileName + ".jpg";
		byte[] content = Base64.getDecoder().decode(new String(imageData).getBytes());
		storageClient.bucket(env.getProperty("app.firebase-bucket")).create(blobString, content,
				Bucket.BlobTargetOption.userProject(env.getProperty("app.firebase-project-id")));
		logger.info("Uploaded notification image to: " + blobString);
		return blobString;
	}

}