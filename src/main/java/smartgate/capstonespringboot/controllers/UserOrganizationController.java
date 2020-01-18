package smartgate.capstonespringboot.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import smartgate.capstonespringboot.assembler.OrganizationResourceAssembler;
import smartgate.capstonespringboot.assembler.UserResourceAssembler;
import smartgate.capstonespringboot.exception.AppException;
import smartgate.capstonespringboot.exception.MeetingNotFoundException;
import smartgate.capstonespringboot.exception.ResourceNotFoundException;
import smartgate.capstonespringboot.models.Organization;
import smartgate.capstonespringboot.models.Role;
import smartgate.capstonespringboot.models.RoleName;
import smartgate.capstonespringboot.models.User;
import smartgate.capstonespringboot.models.WeatherForecastResponse;
import smartgate.capstonespringboot.payloads.SignUpRequest;
import smartgate.capstonespringboot.repository.OrganizationRepository;
import smartgate.capstonespringboot.repository.RoleRepository;
import smartgate.capstonespringboot.repository.UserRepository;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;

@RequestMapping("/api/organizations")
@RestController
public class UserOrganizationController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	OrganizationRepository organizationRepository;

	@Autowired
	UserResourceAssembler assembler;

	// remove duplicate getMapping once mobile app is updated
	@PostMapping("/{organization_name}/users")
	ResponseEntity<?> createUser(@Valid @RequestBody SignUpRequest signUpRequest,
			@PathVariable String organization_name) {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body("Email Address already in use!");
		}

		// Creating user's account
		User user = new User(signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.getFirstName(),
				signUpRequest.getLastName());

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Role userRole = roleRepository.findByName(RoleName.USER)
				.orElseThrow(() -> new AppException("User Role not set."));

		user.setRoles(Collections.singleton(userRole));

		Organization org = organizationRepository.findByName(organization_name);
		user.setOrganization(org);
		User result = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{id}")
				.buildAndExpand(result.getId()).toUri();

		return ResponseEntity.created(location).body("User registered successfully");

	}

	
	@GetMapping("/{organization_name}/users")
	public CollectionModel<EntityModel<User>> all(@PathVariable String organization_name) {
		List<EntityModel<User>> users = userRepository.findAllByOrganizationName(organization_name).stream()
				.map(assembler::toModel).collect(Collectors.toList());

		return new CollectionModel<>(users, linkTo(methodOn(OrganizationController.class).all()).withSelfRel());

	}
	
	@GetMapping("/{organization_name}/users/{user_id}")
	public EntityModel<User> one(@PathVariable String organization_name, @PathVariable Long user_id) {
		return assembler
				.toModel(userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException(organization_name, organization_name, user_id)));
	}
}
