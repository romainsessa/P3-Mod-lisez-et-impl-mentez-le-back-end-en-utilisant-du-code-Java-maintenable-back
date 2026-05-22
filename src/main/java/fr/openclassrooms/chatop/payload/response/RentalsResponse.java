package fr.openclassrooms.chatop.payload.response;

import java.util.List;

import fr.openclassrooms.chatop.dto.RentalDTO;

public class RentalsResponse {

	private List<RentalDTO> rentals;

	public RentalsResponse() {
	}

	public RentalsResponse(List<RentalDTO> rentals) {
		this.rentals = rentals;
	}

	public List<RentalDTO> getRentals() {
		return rentals;
	}

	public void setRentals(List<RentalDTO> rentals) {
		this.rentals = rentals;
	}

}
