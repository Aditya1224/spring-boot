package com.rest.webservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class UserController {

	@Autowired
	private UserDaoService userDaoService;

	// Retrieve all user
	@GetMapping(path = "/users")
	public List<User> retireveAllUser() {
		return userDaoService.findAll();
	}

	// Retrieve user(int id)
	@GetMapping(path = "/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id)
			throws UserNotFoundException  {
		User user = Optional.ofNullable(userDaoService.findOne(id))
					.orElseThrow(() -> new UserNotFoundException("id-"+id));
		
		/*HEATOAS(hyper media as the engine of application state)
		 * Spring Boot HATEOAS starter enables us to easily add links using the methods.
		 * What we'll do is we'll say we would want to add the link to this method. So I
		 * would want to add the link to the resource which is exposed by retrieve all users.
		 * Thereby whenever something changes in the URL, we are not really affected because 
		 * that is the method and URL from that is picked up and returned back.
		 * 
		 * This resource enables us to say this is the data I want to use. And in addition to
		 *  the data, I would be able to add links to the resource.I would want to use the link
		 *   to retrieve all users and what name I should use to refer to it?
		 *   It's called All hyphen users.
		 */
		//"all-users", SERVER_PATH + "/users"
		//retrieveAllUsers
		EntityModel<User> resource = EntityModel.of(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass())
				.retireveAllUser());
		resource.add(linkTo.withRel("all-user"));
		return resource;
	}

	// createUser
	// output: CREATED & Return the created URI
	/*
	 * @RequestBody(indicating a method parameter should be bound to the body of the
	 * web request.) So, when I type, when I put @RequestBody on a parameter, what
	 * will happen is, whatever I would pass in the body of the request would be
	 * mapped to this parameter. I am passing in the name Adam. So, that gets mapped
	 * to name here. what we have created is a simple method which is mapped to a
	 * Post request to /save/user. I would get the request details into the request
	 * body, user, and then, I'm saving it in. So, I'll just go into the save
	 * method.
	 * 
	 * @Valid
	 * enable validation on this specific user.This is an annotation which is present in JavaX 
	 * validation API. This is defined by the Java validation API. 
	 * We already have the Java validation in API and its 
	 * default implementation in the classpath because of something called spring boot starter web.
	 * 
	 */
	@PostMapping("/save/user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User saveUser = userDaoService.saveUser(user);

		/*
		 * how do I create the URI for the location? How do I create the URI of the
		 * location of the resource that is created? There is something called
		 * ServletUriComponentsBuilder. I would import.
		 * In the ServletUriComponentsBuilder, there is something called -
		 * from current requests.
		 */
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(saveUser.getId())
				.toUri();

		/*
		 * I would want to set the return status of created. How do I do that?
		 * There is something called ResponseEntity. That's the one which we can use to return a created status.
		 * Response entity is something which is already part of the Spring Framework. 
		 * If you look at the response entity, its basically a extension of HTTP entity.
		 * We can actually return a state code back And thats what we would want to return back, right?
		 *  A status code of created. When I use the created method in response and
		 *  entity I would be able to pass in what was the location of the resource which was created.
		 */
		return ResponseEntity.created(location).build();
	}
	
	// Delete user(int id)
		@DeleteMapping(path = "/deleteUsers/{id}")
		public User deleteUser(@PathVariable int id) 
				throws UserNotFoundException  {
			return Optional.ofNullable(userDaoService.deleteById(id))
					.orElseThrow(() -> new UserNotFoundException("id-"+id));
		}
}
