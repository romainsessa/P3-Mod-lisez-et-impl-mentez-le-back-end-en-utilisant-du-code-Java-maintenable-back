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

	@Operation(summary = "Get a user by id", description = "Returns a user as per the id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"), 
        @ApiResponse(responseCode = "404", description = "Not found - The user was not found")
    })
	@GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {        
        try {
        		UserDTO user = userService.getById(id);
			return ResponseEntity.ok(user);
		} catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}
    }

}
