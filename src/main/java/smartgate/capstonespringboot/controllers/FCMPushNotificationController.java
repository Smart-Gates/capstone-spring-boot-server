package smartgate.capstonespringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import smartgate.capstonespringboot.firebase.FCMPushNotificationService;
import smartgate.capstonespringboot.models.FCMToken;
import smartgate.capstonespringboot.models.User;
import smartgate.capstonespringboot.payloads.FCMNotificationRequest;
import smartgate.capstonespringboot.payloads.FCMPushNotificationRequest;
import smartgate.capstonespringboot.payloads.FCMPushNotificationResponse;
import smartgate.capstonespringboot.payloads.FCMTokenRequest;
import smartgate.capstonespringboot.repository.FCMTokenRepository;
import smartgate.capstonespringboot.repository.UserRepository;
import smartgate.capstonespringboot.security.CurrentUser;
import smartgate.capstonespringboot.security.UserPrincipal;

@RequestMapping("/api")
@RestController
public class FCMPushNotificationController {
	@Autowired
	private FCMPushNotificationService pushNotificationService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	FCMTokenRepository fcmTokenRepository;

	@PostMapping("/notification/topic")
	public ResponseEntity<?> sendNotification(@RequestBody FCMPushNotificationRequest request) {
		//pushNotificationService.sendPushNotificationWithoutData(request);
		return new ResponseEntity<>(
				new FCMPushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
	}

	// update the users FCM token
	@PostMapping("/notification/token")
	public ResponseEntity<?> fcmTokenGet(@RequestBody FCMTokenRequest request, @CurrentUser UserPrincipal currentUser) {
		// get logged in user

		User user = userRepository.findById(currentUser.getId()).get();
		FCMToken fcm = user.getFcm_token();
		// if there is no token then do this
		if (fcm == null) {
			user.setFcm_token(new FCMToken(request.getToken()));
			userRepository.save(user);
		} else {
			fcm.setFcm_token(request.getToken());
			fcmTokenRepository.save(fcm);
		}

		return new ResponseEntity<>(
				new FCMPushNotificationResponse(HttpStatus.CREATED.value(), "FCM token has been updated."),
				HttpStatus.CREATED);
	}
	
	@PostMapping("/notification")
	public ResponseEntity<?> sendNotification(@RequestBody FCMNotificationRequest request, @CurrentUser UserPrincipal currentUser) {
		// for each email send a push notification to the user
		pushNotificationService.sendPushNotificationEmails(request);
		return new ResponseEntity<>(
				new FCMPushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
	}
	

}