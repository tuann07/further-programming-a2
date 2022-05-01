package com.assignment2.group15.controller;

import com.assignment2.group15.entity.Car;
import com.assignment2.group15.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/car")

public class CarController
{
	private CarService carService;
	
	@Autowired
	public void setCarService(CarService carService)
	{	
		this.carService = carService;
	}
	
	@GetMapping
	public List<Car> getAllCar (@RequestParam(required=false) int page)
	{
		return carService.getAllCar(page);
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
	
	@DeleteMapping(path="{carID")
	public String deleteCar(@PathVariable("carID") Long carID )
	{
		return carService.deleteCar(carID);
	}

}