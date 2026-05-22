package fr.openclassrooms.chatop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	public String saveFile(MultipartFile picture) {
		return "test";
	}

}
