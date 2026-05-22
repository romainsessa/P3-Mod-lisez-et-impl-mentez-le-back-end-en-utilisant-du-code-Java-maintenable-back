package fr.openclassrooms.chatop.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import fr.openclassrooms.chatop.dto.RentalDTO;
import fr.openclassrooms.chatop.entity.Rental;
import fr.openclassrooms.chatop.repository.RentalRepository;

@Service
public class RentalService {

	private RentalRepository rentalRepository;
	
	public RentalService(RentalRepository rentalRepository) {
		this.rentalRepository = rentalRepository;
	}

	public List<RentalDTO> getRentals() {
		List<Rental> rentals = rentalRepository.findAll();
		List<RentalDTO> rentalsDTO = new ArrayList<RentalDTO>();
		rentals.forEach((rental) -> { 
				rentalsDTO.add(new RentalDTO(
						rental.getId(), 
						rental.getName(),
						rental.getSurface(),
						rental.getPrice(), 
						rental.getPicture(), 
						rental.getDescription(),
						rental.getOwner_id(),
						rental.getCreatedAt(),
						rental.getUpdatedAt()));
		});
		return rentalsDTO;
	}

	public RentalDTO getRentalById(Long id) {		
		Rental rental = rentalRepository.getReferenceById(id);
		return new RentalDTO(
				rental.getId(), 
				rental.getName(),
				rental.getSurface(),
				rental.getPrice(), 
				rental.getPicture(), 
				rental.getDescription(),
				rental.getOwner_id(),
				rental.getCreatedAt(),
				rental.getUpdatedAt());
	}
	
}
