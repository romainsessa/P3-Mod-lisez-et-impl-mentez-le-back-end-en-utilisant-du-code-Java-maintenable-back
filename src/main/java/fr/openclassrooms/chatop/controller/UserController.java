package fr.openclassrooms.chatop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.openclassrooms.chatop.dto.UserDTO;
import fr.openclassrooms.chatop.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {        
        try {
        		UserDTO user = userService.getById(id);
			return ResponseEntity.ok(user);
		} catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}
    }

}
