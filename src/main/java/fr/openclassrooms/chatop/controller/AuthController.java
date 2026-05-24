package fr.openclassrooms.chatop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.openclassrooms.chatop.dto.UserDTO;
import fr.openclassrooms.chatop.payload.request.LoginRequest;
import fr.openclassrooms.chatop.payload.request.RegisterRequest;
import fr.openclassrooms.chatop.payload.response.TokenResponse;
import fr.openclassrooms.chatop.service.JwtService;
import fr.openclassrooms.chatop.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private UserService userService;
	private JwtService jwtService;
	private AuthenticationManager authenticationManager;

	public AuthController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
		this.userService = userService;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	@Operation(summary = "Register a new user", description = "Create a user, generate and return a token")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully registred"), 
        @ApiResponse(responseCode = "400", description = "Bad Request - The name or email already exists")
    })
	@PostMapping("/register")
	public ResponseEntity<TokenResponse> register(@RequestBody RegisterRequest request) {
		UserDTO userDTO = userService.findByNameOrEmail(request.getName(), request.getEmail());
		if (userDTO != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		UserDTO savedUser = userService.register(request.getEmail(), request.getName(), request.getPassword());
		String token = jwtService.generateToken(savedUser.getName());
		return ResponseEntity.ok(new TokenResponse(token));
	}

	@Operation(summary = "Log in an existing user", description = "Returns a token for the user logged")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully logged"), 
        @ApiResponse(responseCode = "404", description = "Unauthorized - The credentials are invalid")
    })
	@PostMapping("/login")
	public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
		String email = request.getEmail();
		String password = request.getPassword();
		try {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					email, password)	;
			Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			String token = jwtService.generateToken(authentication.getName());
			return ResponseEntity.ok(new TokenResponse(token));
		} catch (BadCredentialsException ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@Operation(summary = "Get information of the logged user", description = "Returns user details of the logged user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User information retrieved"), 
        @ApiResponse(responseCode = "404", description = "Not found - The user was not found")
    })
	@GetMapping("/me")
    public ResponseEntity<UserDTO> me(Authentication authentication) {        
        UserDTO user = userService.findByNameOrEmail(null, authentication.getName());
        if (user == null) {
        		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }
	
}
