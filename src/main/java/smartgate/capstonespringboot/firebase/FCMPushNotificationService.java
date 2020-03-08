package smartgate.capstonespringboot.firebase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import smartgate.capstonespringboot.models.User;
import smartgate.capstonespringboot.payloads.FCMImageNotificationRequest;
import smartgate.capstonespringboot.payloads.FCMNotificationRequest;
import smartgate.capstonespringboot.payloads.FCMNotificationToTokenRequest;
import smartgate.capstonespringboot.payloads.GeneralResponse;
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

	public ResponseEntity<?> sendPushNotificationToToken(FCMNotificationToTokenRequest request) {
		try {
			fcmService.sendMessageToToken(request);
		} catch (InterruptedException | ExecutionException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(
					new GeneralResponse(HttpStatus.NOT_FOUND.value(), "Could not send notificaiton."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(
				new GeneralResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
	}

	public ResponseEntity<?> sendPushNotificationImageToEmail(FCMImageNotificationRequest request) {
		String downloadUrl = fcmService.uploadImageToFirebase(request.getImageData());
		Optional<User> user = userRepository.findByEmail(request.getEmail());
		if (user.isPresent()) {
			String token = user.get().getFcm_token().getFcm_token();
			FCMNotificationToTokenRequest fcmRequest = new FCMNotificationToTokenRequest(request.getTitle(),
					request.getMessage(), request.getTopic(), token, downloadUrl);
			sendPushNotificationToToken(fcmRequest);
			return new ResponseEntity<>(
					new GeneralResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
		}
		return new ResponseEntity<>(
				new GeneralResponse(HttpStatus.NOT_FOUND.value(), "Could not find user."), HttpStatus.NOT_FOUND);
		
	}

}