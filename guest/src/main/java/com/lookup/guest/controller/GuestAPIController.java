package com.lookup.guest.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lookup.guest.*;
import com.lookup.guest.entity.Guest;
import com.lookup.guest.service.GuestService;

	
	@RestController
	@RequestMapping("/")
	public class GuestAPIController {
	 
	    public static final Logger logger = LoggerFactory.getLogger(GuestAPIController.class);
	 
	    @Autowired
	    GuestService guestService; //Service which will do all data retrieval/manipulation work
	 
	    // -------------------Retrieve All Guests---------------------------------------------
	    
	    @RequestMapping(value = "/guest/", method = RequestMethod.GET)
	    public ResponseEntity<List<Guest>> listAllGuests() {
	        List<Guest> guests = guestService.findAllGuests();
	        if (guests.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Guest>>(guests, HttpStatus.OK);
	        }
	 
	    // -------------------Retrieve Single Guest------------------------------------------
	 
	    @RequestMapping(value = "/guest/{id}", method = RequestMethod.GET)
	    public ResponseEntity<?> getGuests(@PathVariable("id") long id) {
	        logger.info("Fetching Guest with id {}", id);
	        Guest guest = guestService.findById(id);
	        if (guest == null) {
	            logger.error("Guest with id {} not found.", id);
	           /* return new ResponseEntity("Guest with id " + id 
	                    + " not found"), HttpStatus.NOT_FOUND);*/
	        }
	        return new ResponseEntity<Guest>(guest, HttpStatus.OK);
	    }
	 
	    // -------------------Create a Guest-------------------------------------------
	 
	    @RequestMapping(value = "/guest/", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	    public ResponseEntity<?> createGuest(@RequestBody Guest guest) {
	        logger.info("Creating Guest : {}", guest);
	 
	        if (guestService.isGuestExist(guest)) {
	            logger.error("Unable to create. A Guest with name {} already exist", guest.getGuestName());
	            /*return new ResponseEntity(new CustomErrorType("Unable to create. A Guest with name " + 
	            guest.getGuestName() + " already exist."),HttpStatus.CONFLICT);*/
	        }
	       // guestService.saveGuest(guest);
	        //guest.setGuestID(guestService.findAllGuests().size() + 1);
	        guestService.saveGuest(guest);
	         return new ResponseEntity<String>(HttpStatus.CREATED);
	    }
	 
	    // ------------------- Update a Guest ------------------------------------------------
	 
	    @RequestMapping(value = "/guest/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<?> updateGuest(@PathVariable("id") long id, @RequestBody Guest guest) {
	        logger.info("Updating Guest with id {}", id);
	 
	        Guest currentGuest = guestService.findById(id);
	 
	        if ( currentGuest  == null) {
	            logger.error("Unable to update. Guest with id {} not found.", id);
	            /*return new ResponseEntity(new CustomErrorType("Unable to upate. Guest with id " + id + " not found."),
	                    HttpStatus.NOT_FOUND);*/
	        }
	 
	        currentGuest.setGuestID(guest.getGuestID());
	        currentGuest.setGuestName(guest.getGuestName());
	        currentGuest.setHotel(guest.getHotel());
	        currentGuest.setReservationNumber(guest.getReservationNumber());
	        guestService.updateGuest(currentGuest);
	        return new ResponseEntity<Guest>(currentGuest, HttpStatus.OK);
	    }
	 
	    // ------------------- Delete a Guest-----------------------------------------
	 
	    @RequestMapping(value = "/guest/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<?> deleteGuest(@PathVariable("id") long id) {
	        logger.info("Fetching & Deleting Guest with id {}", id);
	 
	        Guest guest = guestService.findById(id);
	        if (guest == null) {
	            logger.error("Unable to delete. Guest with id {} not found.", id);
	            /*return new ResponseEntity(new CustomErrorType("Unable to delete. Guest with id " + id + " not found."),
	                    HttpStatus.NOT_FOUND);*/
	        }
	        guestService.deleteGuestById(id);
	        return new ResponseEntity<Guest>(HttpStatus.NO_CONTENT);
	    }
	 
	    // ------------------- Delete All Guests-----------------------------
	 
	    @RequestMapping(value = "/guest/", method = RequestMethod.DELETE)
	    public ResponseEntity<Guest> deleteAllGuests() {
	        logger.info("Deleting All ");
	 
	        guestService.deleteAllGuests();
	        return new ResponseEntity<Guest>(HttpStatus.NO_CONTENT);
	    }
	 
	
}
