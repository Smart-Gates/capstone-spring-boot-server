package smartgate.capstonespringboot.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;
import smartgate.capstonespringboot.models.Rfid;
import smartgate.capstonespringboot.models.RoleName;
import smartgate.capstonespringboot.models.User;
import smartgate.capstonespringboot.payloads.GeneralResponse;
import smartgate.capstonespringboot.payloads.JwtAuthenticationResponse;
import smartgate.capstonespringboot.payloads.RfidLoginRequest;
import smartgate.capstonespringboot.payloads.RfidRequest;
import smartgate.capstonespringboot.payloads.UserAuthResponse;
import smartgate.capstonespringboot.repository.RfidRepository;
import smartgate.capstonespringboot.repository.UserRepository;
import smartgate.capstonespringboot.security.CurrentUser;
import smartgate.capstonespringboot.security.CustomUserDetailsService;
import smartgate.capstonespringboot.security.JwtTokenProvider;
import smartgate.capstonespringboot.security.UserPrincipal;

@RestController
@RequestMapping("/api")
public class RfidController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RfidRepository rfidRepository;

	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	Logger logger = LoggerFactory.getLogger(RfidController.class);

	// RFID SECTION
	// Returns JWT from the users RFID tag
	@PostMapping("/rfid/signin")
	public ResponseEntity<?> authenticateUserRfid(@RequestBody RfidLoginRequest loginRequest) {
		// check if rfid is present
		logger.info("Begin RFID login request");

		Optional<Rfid> dbRfid= rfidRepository.findByTag(loginRequest.getTag());
		if (!dbRfid.isPresent()) {
			return ResponseEntity.badRequest().body("RFID unknown");
		}
		logger.info("Known RFID");

		// get the userID from the rfid
		Long userId = dbRfid.get().getId();
		UserDetails userDetails = customUserDetailsService.loadUserById(userId);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.generateToken(authentication);
		UserAuthResponse user = new UserAuthResponse((UserPrincipal) authentication.getPrincipal());
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, user));
	}
	
	
	@PostMapping("/rfid")
	public ResponseEntity<?> updateUserRfid(@Valid @RequestBody RfidRequest signUpRequest, @CurrentUser UserPrincipal currentUser) {
		
		Optional<User> dbUser = userRepository.findById(signUpRequest.getUserId());
		// checks to see if the user principal has a user in the database
		if (!dbUser.isPresent()) {
			return ResponseEntity.badRequest().body("User unknown");
		}
		User user = dbUser.get();
		User managerUser = userRepository.findById(currentUser.getId()).get();
		
		if(!managerUser.hasRole(RoleName.MANAGER)) {
			return ResponseEntity.badRequest().body("User is not a manager");
		} else if( !user.getOrganization().equals(managerUser.getOrganization())) {
			return ResponseEntity.badRequest().body("User is not in the same organization");
		}
		
		// check if exists
		Optional<Rfid> rfid = rfidRepository.findById(user.getId());
		if (rfid.isPresent()) {
			Rfid newRfid = rfid.get();
			newRfid.setTag(signUpRequest.getTag());
			rfidRepository.save(newRfid);
		}
		else {
			Rfid newRfid = new Rfid(user, signUpRequest.getTag());
			rfidRepository.save(newRfid);
		}

		return new ResponseEntity<>(
				new GeneralResponse(HttpStatus.CREATED.value(), "RFID has been updated."),
				HttpStatus.CREATED);
	}
	
	//returns list of users in managers org
	@GetMapping("/rfid/users")
	public ResponseEntity<?> getListOfRfid(@CurrentUser UserPrincipal currentUser) {
		User managerUser = userRepository.findById(currentUser.getId()).get();
		
		if(!managerUser.hasRole(RoleName.MANAGER)) {
			return ResponseEntity.badRequest().body("User is not a manager");
		} 
		
		List<User> userList = userRepository.findAllByOrganizationName(managerUser.getOrganization().getName());
		List<User> userHasRfid = new ArrayList<>();

		for (User user : userList) {
		   if(user.getRfid() != null) {
			   userHasRfid.add(user);
		   }
		}
		JSONObject json = new JSONObject();
		json.put("rfidUserList", userHasRfid);

		return new ResponseEntity<>(json,
				HttpStatus.OK);
	}
}

