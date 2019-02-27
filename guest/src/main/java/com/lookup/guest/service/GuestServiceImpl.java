package com.lookup.guest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lookup.guest.entity.Guest;
import com.lookup.guest.repository.GuestRepository;

@Service("guestService")
	@Transactional
	public class GuestServiceImpl implements GuestService{
	@Autowired
    private GuestRepository guestRepository;

	@Override
	public Guest findById(Long guestID) {
		// TODO Auto-generated method stub
		return guestRepository.getOne(guestID);
	}

	@Override
	public Guest findByName(String guestName) {
		// TODO Auto-generated method stub
		return guestRepository.findByGuestName(guestName);
	}

	@Override
	public void saveGuest(Guest guest) {
		// TODO Auto-generated method stub
		guestRepository.save(guest);
	}

	@Override
	public void updateGuest(Guest guest) {
		// TODO Auto-generated method stub
		saveGuest(guest);
	}

	@Override
	public void deleteGuestById(Long id) {
		// TODO Auto-generated method stub
		guestRepository.delete(id);
	}

	@Override
	public void deleteAllGuests() {
		// TODO Auto-generated method stub
		guestRepository.deleteAll();
	}

	@Override
	public List<Guest> findAllGuests() {
		// TODO Auto-generated method stub
		return guestRepository.findAll();
	}

	@Override
	public boolean isGuestExist(Guest guest) {
		// TODO Auto-generated method stub
		return findByName(guest.getGuestName())!=null;
	}
}
