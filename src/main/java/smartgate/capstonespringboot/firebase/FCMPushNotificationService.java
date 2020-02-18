package smartgate.capstonespringboot.firebase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import smartgate.capstonespringboot.models.User;
import smartgate.capstonespringboot.payloads.FCMNotificationRequest;
import smartgate.capstonespringboot.payloads.FCMPushNotificationRequest;
import smartgate.capstonespringboot.repository.UserRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class FCMPushNotificationService {

	@Autowired
	UserRepository userRepository;
	@Value("#{${app.notifications.defaults}}")
	private Map<String, String> defaults;

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
				FCMPushNotificationRequest fcmRequest = new FCMPushNotificationRequest(request.getTitle(),
						request.getMessage(), request.getTopic(), token);
				try {
					fcmService.sendMessageToToken(fcmRequest);
				} catch (InterruptedException | ExecutionException e) {
					logger.error(e.getMessage());
				}
			}

		});
	}

	public void sendPushNotificationToToken(FCMPushNotificationRequest request) {
		try {
			fcmService.sendMessageToToken(request);
		} catch (InterruptedException | ExecutionException e) {
			logger.error(e.getMessage());
		}
	}

}