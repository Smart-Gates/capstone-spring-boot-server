package smartgate.capstonespringboot.controllers;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import smartgate.capstonespringboot.models.Meeting;
import smartgate.capstonespringboot.models.Status;
import smartgate.capstonespringboot.repository.MeetingRepository;
import smartgate.capstonespringboot.assembler.MeetingResourceAssembler;
import smartgate.capstonespringboot.exception.MeetingNotFoundException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.vnderrors.VndErrors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class MeetingController {

	  private final MeetingRepository meetingRepository;
	  private final MeetingResourceAssembler assembler;

	  public MeetingController(MeetingRepository meetingRepository,
	          MeetingResourceAssembler assembler) {

	    this.meetingRepository = meetingRepository;
	    this.assembler = assembler;
	  }

	  @GetMapping("/meetings")
	  public CollectionModel<EntityModel<Meeting>> all() {

	    List<EntityModel<Meeting>> meetings = meetingRepository.findAll().stream()
	      .map(assembler::toModel)
	      .collect(Collectors.toList());

	    return new CollectionModel<>(meetings,
	      linkTo(methodOn(MeetingController.class).all()).withSelfRel());
	  }

	  @GetMapping("/meetings/{id}")
	  public EntityModel<Meeting> one(@PathVariable Long id) {
	    return assembler.toModel(
	      meetingRepository.findById(id)
	        .orElseThrow(() -> new MeetingNotFoundException(id)));
	  }

	  @PostMapping("/meetings")
	  public  ResponseEntity<EntityModel<Meeting>> newMeeting(@RequestBody Meeting meeting) {

	    meeting.setStatus(Status.IN_PROGRESS);
	    Meeting newMeeting = meetingRepository.save(meeting);

	    return ResponseEntity
	      .created(linkTo(methodOn(MeetingController.class).one(newMeeting.getId())).toUri())
	      .body(assembler.toModel(newMeeting));
	  }
	  
	  @DeleteMapping("/meetings/{id}/cancel")
	  public ResponseEntity<RepresentationModel> cancel(@PathVariable Long id) {

	    Meeting meeting = meetingRepository.findById(id).orElseThrow(() -> new MeetingNotFoundException(id));

	    if (meeting.getStatus() == Status.IN_PROGRESS) {
	      meeting.setStatus(Status.CANCELLED);
	      return ResponseEntity.ok(assembler.toModel(meetingRepository.save(meeting)));
	    }

	    return ResponseEntity
	      .status(HttpStatus.METHOD_NOT_ALLOWED)
	      .body(new VndErrors.VndError("Method not allowed", "You can't cancel an meeting that is in the " + meeting.getStatus() + " status"));
	  }
	  
	  @PutMapping("/meetings/{id}/complete")
	  public ResponseEntity<RepresentationModel> complete(@PathVariable Long id) {

	      Meeting meeting = meetingRepository.findById(id).orElseThrow(() -> new MeetingNotFoundException(id));

	      if (meeting.getStatus() == Status.IN_PROGRESS) {
	        meeting.setStatus(Status.COMPLETED);
	        return ResponseEntity.ok(assembler.toModel(meetingRepository.save(meeting)));
	      }

	      return ResponseEntity
	        .status(HttpStatus.METHOD_NOT_ALLOWED)
	        .body(new VndErrors.VndError("Method not allowed", "You can't complete an meeting that is in the " + meeting.getStatus() + " status"));
	  }
	}
