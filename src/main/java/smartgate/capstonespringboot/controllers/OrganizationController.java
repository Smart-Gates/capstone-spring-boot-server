package smartgate.capstonespringboot.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import smartgate.capstonespringboot.models.Organization;
import smartgate.capstonespringboot.models.User;
import smartgate.capstonespringboot.payloads.OrganizationRequest;
import smartgate.capstonespringboot.repository.OrganizationRepository;
import smartgate.capstonespringboot.repository.UserRepository;
import smartgate.capstonespringboot.security.CurrentUser;
import smartgate.capstonespringboot.security.UserPrincipal;
import smartgate.capstonespringboot.assembler.OrganizationResourceAssembler;
import smartgate.capstonespringboot.exception.OrganizationNotFoundException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@RequestMapping("/api")
@RestController
public class OrganizationController {

	private final OrganizationResourceAssembler assembler;

	private final OrganizationRepository organizationRepository;
	private final UserRepository userRepository;

	public OrganizationController(OrganizationRepository organizationRepository, UserRepository userRepository,
			OrganizationResourceAssembler assembler) {

		this.organizationRepository = organizationRepository;
		this.userRepository = userRepository;

		this.assembler = assembler;
	}

	@GetMapping("/organizations/list")
	public CollectionModel<EntityModel<Organization>> all() { // ADMIN use

		//return only organizations that the user belongs to
		
		List<EntityModel<Organization>> organizations = organizationRepository.findAll().stream()
				.map(assembler::toModel).collect(Collectors.toList());

		return new CollectionModel<>(organizations, linkTo(methodOn(OrganizationController.class).all()).withSelfRel());
	}
	
	@GetMapping("/organizations")
	public EntityModel<?> getUserOrganizations(@CurrentUser UserPrincipal currentUser) {
		User user = userRepository.findById(currentUser.getId()).get();

		Long id = user.getOrganization().getId();
		//return only organizations that the user belongs to
		return assembler
				.toModel(organizationRepository.findById(id).orElseThrow(() -> new OrganizationNotFoundException()));
	
	}

	@PostMapping("/organizations")
	public ResponseEntity<?> newOrg(@Valid @RequestBody OrganizationRequest organizationReq) {
		if (organizationRepository.existsByName(organizationReq.getName())) {
			return ResponseEntity.badRequest().body("Name already in use!");
		}
		Long userId = organizationReq.getAcct_mngr_id();

		User user = userRepository.findById(userId).orElseThrow(null);
		Organization newOrg = new Organization(organizationReq.getName(), user, organizationReq.getStreet_address(),
				organizationReq.getZip(), organizationReq.getCity(), organizationReq.getProvince_state(),
				organizationReq.getCountry());

		organizationRepository.save(newOrg);
		// updates user org
		user.setOrganization(newOrg);
		userRepository.save(user);

		return ResponseEntity.created(linkTo(methodOn(OrganizationController.class).one(newOrg.getId())).toUri())
				.body(assembler.toModel(newOrg));
	}

	@GetMapping("/organizations/{id}")
	public EntityModel<Organization> one(@PathVariable Long id) {
		return assembler
				.toModel(organizationRepository.findById(id).orElseThrow(() -> new OrganizationNotFoundException(id)));
	}
	
	@GetMapping("/organizations/{organization_name}")
	public EntityModel<Organization> getByName(@PathVariable String name) {
		return assembler
				.toModel(organizationRepository.findByName(name).orElseThrow(() -> new OrganizationNotFoundException(name)));
	}

}
