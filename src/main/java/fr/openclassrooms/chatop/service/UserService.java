package fr.openclassrooms.chatop.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.openclassrooms.chatop.dto.UserDTO;
import fr.openclassrooms.chatop.entity.User;
import fr.openclassrooms.chatop.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public UserDTO findByNameOrEmail(String name, String email) {
		Optional<User> userOptional = userRepository.findByName(name);
		if (userOptional.isEmpty()) {
			userOptional = userRepository.findByEmail(email);
			if (userOptional.isEmpty()) {
				return null;
			}
		}
		return new UserDTO(userOptional.get());
	}

	public UserDTO register(String email, String name, String password) {
		User entity = new User();
		entity.setEmail(email);
		entity.setName(name);
		entity.setPassword(passwordEncoder.encode(password));
		entity.setCreatedAt(LocalDateTime.now());
		entity.setUpdatedAt(LocalDateTime.now());
		return new UserDTO(userRepository.save(entity));
	}

	public UserDTO getById(Long id) {
		return new UserDTO(userRepository.getReferenceById(id));
	}

}
