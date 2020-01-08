package smartgate.capstonespringboot.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import smartgate.capstonespringboot.models.*;
import smartgate.capstonespringboot.controllers.MeetingController;
@Component
public class MeetingResourceAssembler implements RepresentationModelAssembler <Meeting, EntityModel<Meeting>> {

  @Override
  public EntityModel<Meeting> toModel(Meeting meeting) {

    // Unconditional links to single-item resource and aggregate root

	  EntityModel<Meeting> meetingResource = new EntityModel<>(meeting,
      linkTo(methodOn(MeetingController.class).one(meeting.getId())).withSelfRel(),
      linkTo(methodOn(MeetingController.class).all()).withRel("meetings")
    );

    // Conditional links based on state of the meeting

    if (meeting.getStatus() == Status.IN_PROGRESS) {
      meetingResource.add(
        linkTo(methodOn(MeetingController.class)
          .cancel(meeting.getId())).withRel("cancel"));
      meetingResource.add(
        linkTo(methodOn(MeetingController.class)
          .complete(meeting.getId())).withRel("complete"));
    }

    return meetingResource;
  }
}