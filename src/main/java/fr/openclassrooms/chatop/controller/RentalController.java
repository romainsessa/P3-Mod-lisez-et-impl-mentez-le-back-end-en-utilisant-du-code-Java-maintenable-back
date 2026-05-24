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

	@Operation(summary = "Get all rentals", description = "Returns a list of rentals")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rentals successfully retrieved")
    })
	@GetMapping
	public ResponseEntity<RentalsResponse> getRentals() {
		List<RentalDTO> rentals = rentalService.getRentals();
		return ResponseEntity.ok(new RentalsResponse(rentals));
	}

	@Operation(summary = "Get a rental by id", description = "Returns a rental as per the id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"), 
        @ApiResponse(responseCode = "404", description = "Not found - The rental was not found")
    })
	@GetMapping("/{id}")
	public ResponseEntity<RentalDTO> getRental(@PathVariable Long id) {
		try {
			RentalDTO rental = rentalService.getRentalById(id);
			return ResponseEntity.ok(rental);
		} catch (Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Create a new rental", description = "Create a rental with all required information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully created")
    })
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

	@Operation(summary = "Update a rental by id", description = "Update an existing rental")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated"), 
        @ApiResponse(responseCode = "404", description = "Not found - The rental was not found")
    })
	@PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateRental(
        @PathVariable Long id,
        @RequestParam String name,
        @RequestParam Long surface,
        @RequestParam Long price,
        @RequestParam String description) {
	    
		RentalDTO rental = new RentalDTO();
		rental.setId(id);
        rental.setName(name);
        rental.setSurface(surface);
        rental.setPrice(price);
        rental.setDescription(description);
	    rentalService.updateRental(rental);
	
	    return ResponseEntity.ok(new RentalUpsertResponse("Rental updated !"));
    }

}
