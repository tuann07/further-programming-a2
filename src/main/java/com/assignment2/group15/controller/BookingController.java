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
	private BookingService bookService;
	
	@Autowired
	public void setBookService(BookingService bookService)
	{
		this.bookService = bookService;
	}
	
	@GetMapping
	public List<Booking> getAllBooking(@RequestParam(required=false) Integer page,
									   @RequestParam(required=false) String start,
									   @RequestParam(required=false) String end)
	{
		return bookService.getAllBooking(page, start, end);
	}
	
	@GetMapping(path="{id}")
	public Booking getSingleBooking(@PathVariable("id") Long bookID)
	{
		return bookService.getSingleBooking(bookID);
	}
	
	@PostMapping
	public Booking saveBooking(@RequestBody Booking booking, @RequestParam Long customerId, @RequestParam Long driverId)
	{
		return bookService.saveBooking(booking, customerId, driverId);
	}
	
	@PutMapping(path="{id}")
	public Booking updateBooking(@PathVariable("id") Long bookID, @RequestBody Booking booking)
	{
		return bookService.updateBooking(bookID, booking);
	}
	
	@DeleteMapping(path="{id}")
	public String deleteBooking(@PathVariable("id") Long bookID)
	{
		return bookService.deleteBooking(bookID);
	}
}