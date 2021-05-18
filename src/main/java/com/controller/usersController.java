package com.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.entities.*;
import com.requests.AddUserRequest;
import com.requests.UpdateUserRequest;
import com.services.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/")
public class usersController {

	Logger logger = LoggerFactory.getLogger(usersController.class);
	private UserService userService;

	@GetMapping("/log")
	public String testLogging(){
		// Error > Warn > Info > Debug > Trace
		logger.trace("Nice TRACE Logging");
		logger.debug("Nice DEBUG Logging");
		logger.info("Nice INFO Logging");
		logger.warn("Nice WARN logging");
		logger.error("Nice ERROR Logging");

		return "Logging works super!";
	}

	@GetMapping("/")
	@ApiOperation(value = "Get all users", notes = "Fetches all users in Users API", response = User.class, responseContainer = "List")
	public Collection<User> getAll() {
		return userService.getAll();
	}

	@GetMapping("/{id}")
	public User getById(@ApiParam(value = "Id of the user", required = true) @PathVariable Long id) {
		var response = userService.getById(id);

		if (response == null) {
			logger.warn("Couldnt find user with id: " + id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldnt find any user with provided ID");
		}

		return response;
	}

	@GetMapping("/name/{firstName}")
	public Collection<User> getByFirstName(@PathVariable String firstName) {
		return userService.getByFirstName(firstName);
	}

	@GetMapping("/location/{location}")
	public Collection<User> getByLocation(@PathVariable String location){
		return userService.getByLocation(location);
	}

	@PostMapping("/")
	public User addNewUser(@Valid @RequestBody AddUserRequest request) {
		return userService.addUser(request);
	}

	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
		return userService.updateUser(id, request);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}

}