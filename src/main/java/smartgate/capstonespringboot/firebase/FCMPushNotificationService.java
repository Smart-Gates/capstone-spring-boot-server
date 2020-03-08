package smartgate.capstonespringboot.firebase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smartgate.capstonespringboot.models.User;
import smartgate.capstonespringboot.payloads.FCMImageNotificationRequest;
import smartgate.capstonespringboot.payloads.FCMNotificationRequest;
import smartgate.capstonespringboot.payloads.FCMNotificationToTokenRequest;
import smartgate.capstonespringboot.repository.UserRepository;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class FCMPushNotificationService {

	@Autowired
	UserRepository userRepository;

	private Logger logger = LoggerFactory.getLogger(FCMPushNotificationService.class);
	private FCMService fcmService;

	public FCMPushNotificationService(FCMService fcmService) {
		this.fcmService = fcmService;
	}

	public void sendPushNotificationEmails(FCMNotificationRequest request) {
		request.getEmails().forEach(email -> {
			Optional<User> user = userRepository.findByEmail(email);

			if (user.isPresent()) {
				String token = user.get().getFcm_token().getFcm_token();
				FCMNotificationToTokenRequest fcmRequest = new FCMNotificationToTokenRequest(request.getTitle(),
						request.getMessage(), request.getTopic(), token, null);
				sendPushNotificationToToken(fcmRequest);
			}

		});
	}

	public void sendPushNotificationToToken(FCMNotificationToTokenRequest request) {
		try {
			fcmService.sendMessageToToken(request);
		} catch (InterruptedException | ExecutionException e) {
			logger.error(e.getMessage());
		}
	}

	public void sendPushNotificationImageToEmail(FCMImageNotificationRequest request) {
		String downloadUrl = fcmService.uploadImageToFirebase(request.getImageData());
		Optional<User> user = userRepository.findByEmail(request.getEmail());
		if (user.isPresent()) {
			String token = user.get().getFcm_token().getFcm_token();
			FCMNotificationToTokenRequest fcmRequest = new FCMNotificationToTokenRequest(request.getTitle(),
					request.getMessage(), request.getTopic(), token, downloadUrl);
			sendPushNotificationToToken(fcmRequest);
		}
	}

}