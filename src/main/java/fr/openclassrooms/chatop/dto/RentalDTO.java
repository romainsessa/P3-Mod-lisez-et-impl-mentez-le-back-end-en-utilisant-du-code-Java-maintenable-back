package fr.openclassrooms.chatop.dto;

import java.time.LocalDateTime;

public class RentalDTO {

	private Long id;
	private String name;
	private long surface;
	private long price;
	private String picture;
	private String description;
	private Long owner_id;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public RentalDTO() { }	

	public RentalDTO(Long id, String name, long surface, long price, String picture, String description, Long owner_id,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.surface = surface;
		this.price = price;
		this.picture = picture;
		this.description = description;
		this.owner_id = owner_id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSurface() {
		return surface;
	}

	public void setSurface(long surface) {
		this.surface = surface;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(Long owner_id) {
		this.owner_id = owner_id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
