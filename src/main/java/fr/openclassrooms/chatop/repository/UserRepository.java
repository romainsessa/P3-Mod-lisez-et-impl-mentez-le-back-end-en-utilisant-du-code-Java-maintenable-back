package fr.openclassrooms.chatop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.openclassrooms.chatop.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByName(String username);

	Optional<User> findByEmail(String usernameOrEmail);

}
