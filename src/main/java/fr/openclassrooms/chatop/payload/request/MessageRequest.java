package fr.openclassrooms.chatop.payload.request;

public class MessageRequest {

	private Long rental_id;
	private Long owner_id;
	private String message;

	public Long getRental_id() {
		return rental_id;
	}

	public void setRental_id(Long rental_id) {
		this.rental_id = rental_id;
	}

	public Long getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(Long owner_id) {
		this.owner_id = owner_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
