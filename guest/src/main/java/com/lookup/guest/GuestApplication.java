package com.lookup.guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lookup.guest.repository.GuestRepository;

@SpringBootApplication
public class GuestApplication {

	@Autowired
	GuestRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(GuestApplication.class, args);
	}

}
