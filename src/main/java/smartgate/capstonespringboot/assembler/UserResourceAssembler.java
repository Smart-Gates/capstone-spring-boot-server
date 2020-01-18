package smartgate.capstonespringboot.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import smartgate.capstonespringboot.models.*;
import smartgate.capstonespringboot.controllers.UserOrganizationController;
@Component
public class UserResourceAssembler implements RepresentationModelAssembler <User, EntityModel<User>> {

  @Override
  public EntityModel<User> toModel(User user) {

    // Unconditional links to single-item resource and aggregate root

	  EntityModel<User> organizationResource = new EntityModel<>(user,
      linkTo(methodOn(UserOrganizationController.class).one(user.getOrganization().getName(), user.getId())).withSelfRel(),
      linkTo(methodOn(UserOrganizationController.class).all(user.getOrganization().getName())).withRel("user")
    );

   

    return organizationResource;
  }
}