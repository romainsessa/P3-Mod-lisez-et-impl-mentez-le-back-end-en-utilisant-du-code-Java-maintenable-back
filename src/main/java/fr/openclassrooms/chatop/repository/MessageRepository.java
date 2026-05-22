package fr.openclassrooms.chatop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.openclassrooms.chatop.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
