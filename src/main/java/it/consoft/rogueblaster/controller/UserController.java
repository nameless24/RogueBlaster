package it.consoft.rogueblaster.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.consoft.rogueblaster.model.UserModel;
import it.consoft.rogueblaster.repository.UserRepository;
import it.consoft.rogueblaster.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/getUserModel")
	public UserModel getModel() {
		return new UserModel();
	}

	@PostMapping("/saveUser")
	public ResponseEntity<UserModel> saveUser(@RequestBody UserModel user) {

		try {
			UserModel saved = userRepository.save(user);
			logger.info("Saved : " + saved);
			return new ResponseEntity<UserModel>(saved, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error : " + e);
			return new ResponseEntity<UserModel>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
