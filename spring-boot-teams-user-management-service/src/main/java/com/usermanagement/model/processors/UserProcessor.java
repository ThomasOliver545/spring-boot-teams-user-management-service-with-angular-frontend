package com.usermanagement.model.processors;

import com.usermanagement.controller.UserController;
import com.usermanagement.model.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// Gets triggered when we use e.g. the EntityModel.of() in our UserController.get()
// add aditionall Links (e.g. in addition to the links generated by Spring Data
@Component
public class UserProcessor implements RepresentationModelProcessor<EntityModel<User>> {

    @Override
    public EntityModel<User> process(EntityModel<User> model) {

        // remove automatic generated links if necessary
        model.removeLinks();

        // add our custom links
        model.add(Link.of(linkTo(methodOn(UserController.class).get(model.getContent().getId(), null)) + "?projection=profile").withRel("profile"));

        return model;
    }
}
