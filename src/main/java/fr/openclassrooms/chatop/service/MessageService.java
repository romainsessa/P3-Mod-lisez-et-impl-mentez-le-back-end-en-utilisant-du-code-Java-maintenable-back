package fr.openclassrooms.chatop.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import fr.openclassrooms.chatop.entity.Message;
import fr.openclassrooms.chatop.payload.request.MessageRequest;
import fr.openclassrooms.chatop.repository.MessageRepository;

@Service
public class MessageService {

	private MessageRepository messageRepository;

	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	public void saveMessage(MessageRequest message) {

		Message entity = new Message();
		entity.setRental_id(message.getRental_id());
		entity.setUser_id(message.getOwner_id());
		entity.setMessage(message.getMessage());
		entity.setCreatedAt(LocalDateTime.now());
		entity.setUpdatedAt(LocalDateTime.now());

		this.messageRepository.save(entity);
	}

}
