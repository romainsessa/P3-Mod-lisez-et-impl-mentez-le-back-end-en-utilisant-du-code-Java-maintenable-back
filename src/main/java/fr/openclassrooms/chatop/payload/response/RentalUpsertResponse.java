package fr.openclassrooms.chatop.payload.response;

public class RentalUpsertResponse {

	private String message;

	public RentalUpsertResponse() {
	}

	public RentalUpsertResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
