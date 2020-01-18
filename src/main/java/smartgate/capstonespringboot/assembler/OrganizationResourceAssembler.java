package smartgate.capstonespringboot.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import smartgate.capstonespringboot.models.*;
import smartgate.capstonespringboot.controllers.MeetingController;
import smartgate.capstonespringboot.controllers.OrganizationController;
@Component
public class OrganizationResourceAssembler implements RepresentationModelAssembler <Organization, EntityModel<Organization>> {

  @Override
  public EntityModel<Organization> toModel(Organization organization) {

    // Unconditional links to single-item resource and aggregate root

	  EntityModel<Organization> organizationResource = new EntityModel<>(organization,
      linkTo(methodOn(OrganizationController.class).one(organization.getId())).withSelfRel(),
      linkTo(methodOn(OrganizationController.class).all()).withRel("organization")
    );

   

    return organizationResource;
  }
}