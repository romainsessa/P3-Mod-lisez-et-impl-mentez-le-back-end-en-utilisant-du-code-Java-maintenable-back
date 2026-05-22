package fr.openclassrooms.chatop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.openclassrooms.chatop.payload.request.MessageRequest;
import fr.openclassrooms.chatop.payload.response.MessageResponse;
import fr.openclassrooms.chatop.service.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
	
	private MessageService messageService;
	
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}

	@PostMapping
    public ResponseEntity<MessageResponse> addMessage(@RequestBody MessageRequest message){
        if (message.getRental_id() == null || message.getOwner_id() == null || message.getMessage() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        messageService.saveMessage(message);
        return ResponseEntity.ok(new MessageResponse("Message send with success"));
    }
	
}
