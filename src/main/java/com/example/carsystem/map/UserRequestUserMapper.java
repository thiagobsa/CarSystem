package com.example.carsystem.map;

import com.example.carsystem.dto.UserRequest;
import com.example.carsystem.model.CarEntity;
import com.example.carsystem.model.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserRequestUserMapper implements Mapper<UserRequest, UserEntity> {

    @Override
    public UserEntity map(UserRequest input) {

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(input.getFirstName());
        userEntity.setLastName(input.getLastName());
        userEntity.setEmail(input.getEmail());
        userEntity.setBirthday(input.getBirthday());
        userEntity.setLogin(input.getLogin());
        userEntity.setPassword(input.getPassword());
        userEntity.setPhone(input.getPhone());

        List<CarEntity> carEntityList = input.getCars().stream()
                .map(car -> {
                    CarEntity carEntity = new CarEntity();
                    carEntity.setYear(car.getYear());
                    carEntity.setLicensePlate(car.getLicensePlate());
                    carEntity.setModel(car.getModel());
                    carEntity.setColor(car.getColor());
                    carEntity.setUser(userEntity);
                    return carEntity;
                })
                .collect(Collectors.toList());

        userEntity.setCars(carEntityList);

        return userEntity;
    }
}