package fr.openclassrooms.chatop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "fr.openclassrooms.chatop.security")
public class SecurityProperties {

	private String jwtKey;
	
	public String getJwtKey() {
		return jwtKey;
	}
	
	public void setJwtKey(String jwtKey) {
		this.jwtKey = jwtKey;
	}

}