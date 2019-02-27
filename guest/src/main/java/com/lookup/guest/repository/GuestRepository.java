package com.lookup.guest.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lookup.guest.entity.Guest;



@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
	 Guest findByGuestName(String guestName);
		 
}



