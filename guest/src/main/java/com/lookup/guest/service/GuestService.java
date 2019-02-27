package com.lookup.guest.service;

import java.util.List;

import com.lookup.guest.entity.Guest;

public interface GuestService {
	
	 Guest findById(Long guestID);
	 
	    Guest findByName(String guestName);
	 
	    void saveGuest(Guest guest);
	 
	    void updateGuest(Guest guest);
	 
	    void deleteGuestById(Long guestID);
	 
	    void deleteAllGuests();
	 
	    List<Guest> findAllGuests();
	 
	    boolean isGuestExist(Guest guest);

}
