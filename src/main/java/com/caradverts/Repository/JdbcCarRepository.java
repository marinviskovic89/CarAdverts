package com.caradverts.Repository;

import com.caradverts.Model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCarRepository implements CarRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapper<Car> rowMapper = (rs,rowNum)->{
        Car c = new Car();
        c.setId(rs.getInt("id"));
        c.setTitle(rs.getString("title"));
        c.setFuelType(rs.getString("fuelType"));
        c.setPrice(rs.getInt("price"));
        c.setNewer(rs.getBoolean("isNew"));
        c.setMileage(rs.getInt("mileage"));
        c.setFirstRegistration(rs.getDate("firstRegistration"));
        return c;
    };

    @Override
    public List<Car> findAll(){
        String query = "SELECT * FROM cars ";
        return jdbcTemplate.query(query, rowMapper);
    }

    @Override
    public List<Car> sortByParam(String fieldName){
        String query= "SELECT * FROM cars ORDER BY " + fieldName + " ";
        return jdbcTemplate.query(query, rowMapper);
    }

    @Override
    public int save(Car car) {
        return jdbcTemplate.update("INSERT INTO cars (title, fuelType, isNew, price, mileage, firstRegistration) VALUES(?,?,?,?,?,?)",
                new Object[] {car.getTitle(), car.getFuelType(), car.isNewer(), car.getPrice(), car.getMileage(), car.getFirstRegistration()});
    }

    @Override
    public int update(Car car) {
        String query = "Update cars SET title=?,fueltype=?,isNew=?,price=?,mileage=?,firstregistration=? WHERE id=? ";
        return jdbcTemplate.update(query, car.getTitle(), car.getFuelType(), car.isNewer(), car.getPrice(), car.getMileage(), car.getFirstRegistration(), car.getId());
    }
    @Override
    public Car findById(Long id) {
        String query = "SELECT * FROM cars WHERE id=?";
        return jdbcTemplate.queryForObject(query,rowMapper,id);
    }

    @Override
    public int deleteById(Long id) {
        String query = "DELETE FROM cars WHERE id=?";
        return jdbcTemplate.update(query,id);
    }
}
