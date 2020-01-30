package smartgate.capstonespringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import smartgate.capstonespringboot.firebase.FCMPushNotificationService;
import smartgate.capstonespringboot.payloads.FCMPushNotificationRequest;
import smartgate.capstonespringboot.payloads.FCMPushNotificationResponse;
@RequestMapping("/api")
@RestController
public class FCMPushNotificationController {
	@Autowired
	private FCMPushNotificationService pushNotificationService;



    @PostMapping("/notification/topic")
    public ResponseEntity<?> sendNotification(@RequestBody FCMPushNotificationRequest request) {
        pushNotificationService.sendPushNotificationWithoutData(request);
        return new ResponseEntity<>(new FCMPushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }



    @GetMapping("/notification")
    public ResponseEntity<?> sendSampleNotification() {
        pushNotificationService.sendSamplePushNotification();
        return new ResponseEntity<>(new FCMPushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }
}