package com.assignment2.group15.controller;

import com.assignment2.group15.entity.Car;
import com.assignment2.group15.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	public List<Car> getAllCar (@RequestParam(required=false) Integer page,
								@RequestParam(required=false) String start,
								@RequestParam(required=false) String end)
	{
		return carService.getAllCar(page, start, end);
	}
	
	@GetMapping(path="{carID}")
	public Car getSingleCar(@PathVariable("carID") Long carID)
	{
		return carService.getSingleCar(carID);
	}
	
	@PostMapping
	public Car saveCar(@RequestBody Car car)
	{
		return carService.saveCar(car);
	}
	
	@PutMapping(path="{carID}")
	public Car updateCar(@PathVariable("carID") Long carID, @RequestBody Car car)
	{
		return carService.updateCar(carID, car);
	}
	
	@DeleteMapping(path="{carID}")
	public String deleteCar(@PathVariable("carID") Long carID )
	{
		return carService.deleteCar(carID);
	}

}