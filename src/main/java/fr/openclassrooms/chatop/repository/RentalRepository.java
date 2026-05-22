package fr.openclassrooms.chatop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.openclassrooms.chatop.entity.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long>{

}
