package com.assignment2.group15.controller;

import com.assignment2.group15.entity.Booking;
import com.assignment2.group15.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/bookings")

public class BookingController
{
	private BookingService bookingService;
	
	@Autowired
	public void setBookingService(BookingService bookingService)
	{
		this.bookingService = bookingService;
	}
	
	@GetMapping
	public List<Booking> getAllBooking(
			@RequestParam(required=false) Integer page,
			@RequestParam(required=false) Integer limit,
			@RequestParam(required=false) String start,
			@RequestParam(required=false) String end
	) {
		return bookingService.getAllBooking(page, limit, start, end);
	}
	
	@GetMapping(path="{bookingId}")
	public Booking getSingleBooking(@PathVariable("bookingId") Long bookID)
	{
		return bookingService.getSingleBooking(bookID);
	}
	
	@PostMapping
	public Booking saveBooking(
			@RequestBody Booking booking,
			@RequestParam Long customerId,
			@RequestParam Long carId
	) {
		return bookingService.saveBooking(booking, customerId, carId);
	}
	
	@PutMapping(path="{bookingId}")
	public Booking updateBooking(
			@PathVariable("bookingId") Long bookID,
			@RequestBody Booking booking
	) {
		return bookingService.updateBooking(bookID, booking);
	}
	
	@DeleteMapping(path="{bookingId}")
	public String deleteBooking(
			@PathVariable("bookingId") Long bookID
	) {
		return bookingService.deleteBooking(bookID);
	}
}