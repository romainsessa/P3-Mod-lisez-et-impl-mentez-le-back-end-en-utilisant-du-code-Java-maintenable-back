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

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
		UserDTO userDTO = userService.findByNameOrEmail(request.getName(), request.getEmail());
		if (userDTO != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Username or email already exists\"}");
		}
		UserDTO savedUser = userService.register(request.getEmail(), request.getName(), request.getPassword());
		String token = jwtService.generateToken(savedUser.getName());
		return ResponseEntity.ok(new TokenResponse(token));
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
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
	
	@GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {        
        UserDTO user = userService.findByNameOrEmail(null, authentication.getName());
        if (user == null) {
        		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }
	
}
