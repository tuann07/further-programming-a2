package com.assignment2.group15.controller;

import com.assignment2.group15.entity.Booking;
import com.assignment2.group15.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/booking")

public class BookingController
{
	private BookingService bookService;
	
	@Autowired
	public void setBookService(BookingService bookService)
	{
		this.bookService = bookService;
	}
	
	@GetMapping
	public List<Booking> getAllBooking(@RequestParam(required=false) Integer page)
	{
		return bookService.getAllBooking(page);
	}
	
	@GetMapping(path="{bookID}")
	public Booking getSingleBooking(@PathVariable("bookID") Long bookID)
	{
		return bookService.getSingleBooking(bookID);
	}
	
	@PostMapping
	public Booking saveBook(@RequestBody Booking booking)
	{
		return bookService.saveBooking(booking);
	}
	
	@PutMapping(path="{bookID}")
	public Booking updateBooking(@PathVariable("bookID") Long bookID, @RequestBody Booking booking)
	{
		return bookService.updateBooking(bookID, booking);
	}
	
	@DeleteMapping(path="{bookID}")
	public String deleteBooking(@PathVariable("bookID") Long bookID)
	{
		return bookService.deleteBooking(bookID);
	}
}