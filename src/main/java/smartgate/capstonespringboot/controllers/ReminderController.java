package smartgate.capstonespringboot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import smartgate.capstonespringboot.models.Event;
import smartgate.capstonespringboot.models.Reminder;
import smartgate.capstonespringboot.models.User;
import smartgate.capstonespringboot.payloads.ReminderRequest;
import smartgate.capstonespringboot.repository.EventRepository;
import smartgate.capstonespringboot.repository.ReminderRepository;
import smartgate.capstonespringboot.repository.UserRepository;
import smartgate.capstonespringboot.security.CurrentUser;
import smartgate.capstonespringboot.security.UserPrincipal;
import smartgate.capstonespringboot.assembler.ReminderResourceAssembler;
import smartgate.capstonespringboot.exception.ReminderNotFoundException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@RequestMapping("/api")
@RestController
public class ReminderController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ReminderRepository reminderRepository;
	@Autowired
	EventRepository eventRepository;
	@Autowired
	ReminderResourceAssembler assembler;

	// return reminders from the user
	@GetMapping("/reminders")
	public CollectionModel<EntityModel<Reminder>> all(@CurrentUser UserPrincipal currentUser) {
		// get logged in user

		User user = userRepository.findById(currentUser.getId()).get();
		// reminders where user is creator
		List<EntityModel<Reminder>> reminders = reminderRepository.findAllByCreator(user).stream()
				.map(assembler::toModel).collect(Collectors.toList());

	

		return new CollectionModel<>(reminders,
				linkTo(methodOn(ReminderController.class).all(currentUser)).withSelfRel());
	}

	@GetMapping("/reminders/{id}")
	public EntityModel<Reminder> one(@PathVariable Long id) {
		return assembler.toModel(reminderRepository.findById(id).orElseThrow(() -> new ReminderNotFoundException(id)));
	}

	@PostMapping("/reminders")
	public ResponseEntity<?> newReminder(@RequestBody ReminderRequest reminderRequest,
			@CurrentUser UserPrincipal currentUser) {
		// optional refers to that it may not be there
		Optional<User> dbUser = userRepository.findById(currentUser.getId());
		// checks to see if the user principal has a user in the database
		if (!dbUser.isPresent()) {
			return ResponseEntity.badRequest().body("User unknown");
		}
		User user = dbUser.get();

		Event event = null;
		Long event_id = reminderRequest.getEvent_id();
		if (event_id != null) {
			event = eventRepository.findById(event_id).get();
		}

		Reminder newReminder = new Reminder(reminderRequest.getTitle(), reminderRequest.getDescription(),
				reminderRequest.getStart_time(), event, user);
		reminderRepository.save(newReminder);

		return ResponseEntity.created(linkTo(methodOn(ReminderController.class).one(newReminder.getId())).toUri())
				.body(assembler.toModel(newReminder));

	}

}
