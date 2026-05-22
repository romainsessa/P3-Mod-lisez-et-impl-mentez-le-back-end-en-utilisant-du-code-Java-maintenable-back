package fr.openclassrooms.chatop.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.openclassrooms.chatop.dto.RentalDTO;
import fr.openclassrooms.chatop.dto.UserDTO;
import fr.openclassrooms.chatop.payload.response.RentalUpsertResponse;
import fr.openclassrooms.chatop.payload.response.RentalsResponse;
import fr.openclassrooms.chatop.service.FileService;
import fr.openclassrooms.chatop.service.RentalService;
import fr.openclassrooms.chatop.service.UserService;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

	private RentalService rentalService;
	private FileService fileService;
	private UserService userService;

	public RentalController(RentalService rentalService, FileService fileService, UserService userService) {
		this.rentalService = rentalService;
		this.fileService = fileService;
		this.userService = userService;
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
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RentalUpsertResponse> addRental(
        @RequestParam String name,
        @RequestParam Long surface,
        @RequestParam Long price,
        @RequestParam String description,
        @RequestParam MultipartFile picture,
        Authentication authentication) {      
        		String email = authentication.getName();
        		UserDTO user = userService.findByNameOrEmail(null, email);
        		
            String pictureUrl = fileService.saveFile(picture);
            
            RentalDTO rental = new RentalDTO();
            rental.setName(name);
            rental.setSurface(surface);
            rental.setPrice(price);
            rental.setDescription(description);
            rental.setPicture(pictureUrl);
            rental.setOwner_id(user.getId());
            
            rentalService.saveRental(rental);

            return ResponseEntity.ok(new RentalUpsertResponse("Rental created !"));            
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<?> updateRental(
        @PathVariable Long id,
        @RequestBody RentalDTO rental) {
	    
		rental.setId(id);		
	    rentalService.updateRental(rental);
	
	    return ResponseEntity.ok(new RentalUpsertResponse("Rental updated !"));
    }

}
