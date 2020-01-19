package smartgate.capstonespringboot.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import smartgate.capstonespringboot.models.*;
import smartgate.capstonespringboot.controllers.EventController;

@Component
public class EventResourceAssembler implements RepresentationModelAssembler<Event, EntityModel<Event>> {

	@Override
	public EntityModel<Event> toModel(Event event) {

		// Unconditional links to single-item resource and aggregate root

		EntityModel<Event> eventResource = new EntityModel<>(event,
				linkTo(methodOn(EventController.class).one(event.getId())).withSelfRel(),
				linkTo(methodOn(EventController.class).all(null)).withRel("event"));

		return eventResource;
	}
}