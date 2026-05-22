package fr.openclassrooms.chatop.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.openclassrooms.chatop.dto.RentalDTO;
import fr.openclassrooms.chatop.payload.response.RentalsResponse;
import fr.openclassrooms.chatop.service.RentalService;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

	private RentalService rentalService;

	public RentalController(RentalService rentalService) {
		this.rentalService = rentalService;
	}

	@GetMapping
	public ResponseEntity<RentalsResponse> getRentals() {
		List<RentalDTO> rentals = rentalService.getRentals();
		return ResponseEntity.ok(new RentalsResponse(rentals));
	}

	@GetMapping("/{id}")
	public ResponseEntity<RentalDTO> getRental(@PathVariable Long id) {
		try {
			RentalDTO rental = rentalService.getRentalById(id);
			return ResponseEntity.ok(rental);
		} catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}

}
