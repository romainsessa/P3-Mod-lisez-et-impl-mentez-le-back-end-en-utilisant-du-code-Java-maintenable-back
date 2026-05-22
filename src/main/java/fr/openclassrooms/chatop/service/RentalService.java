package fr.openclassrooms.chatop.service;

import java.time.LocalDateTime;
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

	public void saveRental(RentalDTO rental) {
		Rental rentalEntity = new Rental();
		
		rentalEntity.setName(rental.getName());
		rentalEntity.setSurface(rental.getSurface());
		rentalEntity.setPrice(rental.getPrice());
		rentalEntity.setPicture(rental.getPicture());
		rentalEntity.setDescription(rental.getDescription());
		rentalEntity.setOwner_id(rental.getOwner_id());
		rentalEntity.setCreatedAt(LocalDateTime.now());
		rentalEntity.setUpdatedAt(LocalDateTime.now());
		
		rentalRepository.save(rentalEntity);
	}

	public void updateRental(RentalDTO rental) {
		Rental rentalEntity = rentalRepository.getReferenceById(rental.getId());
		rentalEntity.setName(rental.getName());
		rentalEntity.setSurface(rental.getSurface());
		rentalEntity.setPrice(rental.getPrice());
		rentalEntity.setDescription(rental.getDescription());
		rentalEntity.setUpdatedAt(LocalDateTime.now());

		rentalRepository.save(rentalEntity);
	}
	
}
