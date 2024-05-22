package com.example.carsystem.map;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.carsystem.dto.UserRequest;
import com.example.carsystem.models.CarEntity;
import com.example.carsystem.models.UserEntity;

@Component
public class UserEntityMapper implements Mapper<UserRequest, UserEntity> {
	

    @Override
    public UserEntity map(UserRequest input) {

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(input.getFirstName());
        userEntity.setLastName(input.getLastName());
        userEntity.setEmail(input.getEmail());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            userEntity.setBirthday(dateFormat.parse(input.getBirthday().toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        userEntity.setLogin(input.getLogin());
        userEntity.setPassword(input.getPassword());
        userEntity.setPhone(input.getPhone());

        List<CarEntity> carEntityList = new ArrayList<>();

        input.getCars().forEach(car -> {

            CarEntity carEntity = new CarEntity();
            carEntity.setYear(car.getYear());
            carEntity.setLicensePlate(car.getLicensePlate());
            carEntity.setModel(car.getModel());
            carEntity.setColor(car.getColor());
            carEntity.setUser(userEntity);

            carEntityList.add(carEntity);
        });

       userEntity.getCars().addAll(carEntityList);

        return userEntity;
    }
}

