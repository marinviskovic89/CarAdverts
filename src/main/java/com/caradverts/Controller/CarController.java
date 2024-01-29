package com.caradverts.Controller;

import com.caradverts.Model.Car;
import com.caradverts.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.util.List;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CarController {
    @Autowired
    CarRepository carRepository;

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars(@RequestParam(required = false) String sortby) {
        List<Car> cars;
        String sortbyLower = sortby.toLowerCase();
        if (sortby == null) {
            cars = carRepository.findAll();
        } else {
            cars = carRepository.sortByParam(sortbyLower);
        }
        if (cars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);

    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") long id) {
        try {
            Car car = carRepository.findById(id);
            return new ResponseEntity<>(car, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No car advert with given id was found.", ex);
        }
    }

    @PostMapping("/cars")
    public ResponseEntity<String> createCar(@RequestBody Car car) {
        try {
            if (car.getPrice() >= 0 & car.getId() >= 0) {
                carRepository.save(car);
                return new ResponseEntity<>("Car was created successfully.", HttpStatus.CREATED);
            } else
                return new ResponseEntity<>("Validation failed.", HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "This is returned if json is invalid or cannot be parsed.", ex);
        }
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<String> updateCar(@PathVariable("id") long id, @RequestBody Car c) {
        try {
            if (c.getPrice() >= 0 & c.getId() >= 0) {
                Car existingCar = carRepository.findById(id);
                Car newDetails = new Car();
                if (existingCar != null) {
                    newDetails.setId(id);
                    newDetails.setTitle(c.getTitle());
                    newDetails.setFuelType(c.getFuelType());
                    newDetails.setPrice(c.getPrice());
                    newDetails.setNewer(c.isNewer());
                    newDetails.setMileage(c.getMileage());
                    newDetails.setFirstRegistration(c.getFirstRegistration());

                    carRepository.update(newDetails);
                }
                return new ResponseEntity<>("Updated details successfully", HttpStatus.OK);
            } else
                return new ResponseEntity<>("Validation failed.", HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "This is returned if a car advert with given id is not found.", ex);
        }

    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCar(@PathVariable("id") long id) {
        int res = carRepository.deleteById(id);
        if (res == 1) {
            return new ResponseEntity<>("Car was deleted successfully.", HttpStatus.OK);

    }
    return new ResponseEntity<>("This is returned if a car advert with given id is not found.", HttpStatus.NOT_FOUND);
    }

}
