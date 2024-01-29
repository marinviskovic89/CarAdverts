package com.caradverts.Repository;

import com.caradverts.Model.Car;

import java.util.List;

public interface CarRepository {
    List<Car> findAll();
    List<Car> sortByParam(String fieldName);
    int save(Car car);
    int update(Car car);
    Car findById(Long id);
    int deleteById(Long id);
}
