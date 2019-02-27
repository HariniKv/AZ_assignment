package com.lookup.guest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="GUEST")
public class Guest {
 @Id
  @Column(name="GUESTID", nullable=false)
 private Long guestId;
 
 @Column(name="GUESTNAME", nullable=false)
  private String guestName;

 @Column(name="HOTEL", nullable=false)
 private String hotel;
 
 @Column(name="RESERVATIONNUMBER", nullable=false)
 private int reservationNumber;
 
 public Guest() {
	 super();
 }
 
 public Guest(Long guestId, String guestName, String hotel, int reservationNumber) {
	 super();
	 this.guestId = guestId;
	 this.guestName= guestName;
	 this.hotel= hotel;
	 this.reservationNumber= reservationNumber;
 }
 
public Long getGuestID() {
	return guestId;
}
public void setGuestID(Long guestId) {
	this.guestId = guestId;
}
public String getGuestName() {
	return guestName;
}
public void setGuestName(String guestName) {
	this.guestName = guestName;
}
public String getHotel() {
	return hotel;
}
public void setHotel(String hotel) {
	this.hotel = hotel;
}
public int getReservationNumber() {
	return reservationNumber;
}
public void setReservationNumber(int reservationNumber) {
	this.reservationNumber = reservationNumber;
}
@Override
public String toString() {
	return String.format("Guest [id=%s, name=%s, hotel=%s, reservationNumber=%s]", guestId, guestName, hotel, reservationNumber);
}
	
}
