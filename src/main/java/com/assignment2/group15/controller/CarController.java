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
	public List<Car> getAllCar (@RequestParam(required=false) Integer page)
	{
		return carService.getAllCar(page);
	}
	
	@GetMapping(path="{id}")
	public Car getSingleCar(@PathVariable("id") Long id)
	{
		return carService.getSingleCar(id);
	}
	
	@PostMapping
	public Car saveCar(@RequestBody Car car)
	{
		return carService.saveCar(car);
	}
	
	@PutMapping(path="{id}")
	public Car updateCar(@PathVariable("id") Long id, @RequestBody Car car)
	{
		return carService.updateCar(id, car);
	}
	
	@DeleteMapping(path="{id}")
	public String deleteCar(@PathVariable("id") Long id )
	{
		return carService.deleteCar(id);
	}

}