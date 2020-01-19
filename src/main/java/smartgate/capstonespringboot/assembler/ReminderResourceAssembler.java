package smartgate.capstonespringboot.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import smartgate.capstonespringboot.models.*;
import smartgate.capstonespringboot.controllers.ReminderController;

@Component
public class ReminderResourceAssembler implements RepresentationModelAssembler<Reminder, EntityModel<Reminder>> {

	@Override
	public EntityModel<Reminder> toModel(Reminder reminder) {

		// Unconditional links to single-item resource and aggregate root

		EntityModel<Reminder> reminderResource = new EntityModel<>(reminder,
				linkTo(methodOn(ReminderController.class).one(reminder.getId())).withSelfRel(),
				linkTo(methodOn(ReminderController.class).all(null)).withRel("reminder"));

		return reminderResource;
	}
}