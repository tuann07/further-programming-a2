package com.assignment2.group15.controller;

import com.assignment2.group15.entity.Car;
import com.assignment2.group15.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/cars")

public class CarController
{
	private CarService carService;
	
	@Autowired
	public void setCarService(CarService carService)
	{	
		this.carService = carService;
	}
	
	@GetMapping
	public List<Car> getAllCar (
			@RequestParam(required=false) Integer page,
			@RequestParam(required=false) Integer limit,
			@RequestParam(required = false) String model,
			@RequestParam(required = false) String color,
			@RequestParam(required = false) Boolean available
	) {
		return carService.getAllCar(page, limit, available, model, color);
	}
	
	@GetMapping(path="{carId}")
	public Car getSingleCar(
			@PathVariable("carId") Long carId
	) {
		return carService.getSingleCar(carId);
	}
	
	@PostMapping
	public Car saveCar(
			@RequestBody Car car
	) {
		return carService.saveCar(car);
	}
	
	@PutMapping(path="{carId}")
	public Car updateCar(
			@PathVariable("carId") Long carId,
			@RequestBody Car car
	) {
		return carService.updateCar(carId, car);
	}
	
	@DeleteMapping(path="{carId}")
	public String deleteCar(
			@PathVariable("carId") Long carId
	) {
		return carService.deleteCar(carId);
	}

	@GetMapping(path="/usage")
	public List<Map<String, Long>> getDaysCarUsedInMonth(
			@RequestParam("month") Integer month,
			@RequestParam("year") Integer year
	) {
		return carService.getCarUsageInMonth(year, month);
	}
}