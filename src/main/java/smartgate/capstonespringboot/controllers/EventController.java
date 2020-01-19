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
import smartgate.capstonespringboot.models.EventAttendee;
import smartgate.capstonespringboot.models.User;
import smartgate.capstonespringboot.payloads.EventRequest;
import smartgate.capstonespringboot.repository.EventAttendeeRepository;
import smartgate.capstonespringboot.repository.EventRepository;
import smartgate.capstonespringboot.repository.UserRepository;
import smartgate.capstonespringboot.security.CurrentUser;
import smartgate.capstonespringboot.security.UserPrincipal;
import smartgate.capstonespringboot.assembler.EventResourceAssembler;
import smartgate.capstonespringboot.exception.EventNotFoundException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@RequestMapping("/api")
@RestController
public class EventController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	EventRepository eventRepository;
	@Autowired
	EventAttendeeRepository eventAttendeeRepository;
	@Autowired
	EventResourceAssembler assembler;

	// return events from the user
	@GetMapping("/events")
	public CollectionModel<EntityModel<Event>> all(@CurrentUser UserPrincipal currentUser) {
		// get logged in user

		User user = userRepository.findById(currentUser.getId()).get();
		// events where user is creator
		List<EntityModel<Event>> events = eventRepository.findAllByCreator(user).stream().map(assembler::toModel)
				.collect(Collectors.toList());

		// events where user is listed as a attendee
		List<Event> attendEvents = eventAttendeeRepository.findAllByUserId(currentUser.getId());

		attendEvents.forEach(event -> {
			// find all the events by given event id
			List<EntityModel<Event>> attendingEventsById = eventRepository.findAllById(event.getId()).stream()
					.map(assembler::toModel).collect(Collectors.toList());
			events.addAll(attendingEventsById);

		});

		return new CollectionModel<>(events, linkTo(methodOn(EventController.class).all(currentUser)).withSelfRel());
	}

	@GetMapping("/events/{id}")
	public EntityModel<Event> one(@PathVariable Long id) {
		return assembler.toModel(eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id)));
	}

	@PostMapping("/events")
	public ResponseEntity<?> newEvent(@RequestBody EventRequest eventRequest, @CurrentUser UserPrincipal currentUser) {
		// optional refers to that it may not be there
		Optional<User> dbUser = userRepository.findById(currentUser.getId());
		// checks to see if the user principal has a user in the database
		if (!dbUser.isPresent()) {
			return ResponseEntity.badRequest().body("User unknown");
		}
		User user = dbUser.get();

		Event newEvent = new Event(eventRequest.getTitle(), eventRequest.getDescription(), eventRequest.getLocation(),
				eventRequest.getStart_time(), eventRequest.getEnd_time(), user);
		eventRepository.save(newEvent);

		// check to see if there are any attached attendees
		if (eventRequest.getAttendee_id().isEmpty()) {
			return ResponseEntity.created(linkTo(methodOn(EventController.class).one(newEvent.getId())).toUri())
					.body(assembler.toModel(newEvent));
		}

		eventRequest.getAttendee_id().forEach(user_id -> {
			Optional<User> dbAttendee = userRepository.findById(user_id);
			// checks to see if the user principal has a user in the database
			if (!dbAttendee.isPresent()) {
				return;
			}
			User attendee = dbAttendee.get();
			EventAttendee eventAttendee = new EventAttendee(attendee, newEvent);
			eventAttendeeRepository.save(eventAttendee);
		});

		return ResponseEntity.created(linkTo(methodOn(EventController.class).one(newEvent.getId())).toUri())
				.body(assembler.toModel(newEvent));

	}

}