package com.example.carsystem.map;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.carsystem.dto.CarResponse;
import com.example.carsystem.dto.UserCreated;
import com.example.carsystem.model.UserEntity;

@Component
public class UserCreatedUserMapper implements Mapper<UserEntity, UserCreated> {

    @Override
    public UserCreated map(UserEntity input) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String birthdayFormat = dateFormat.format(input.getBirthday());

        String lastLogin = input.getLastLogin() != null ? input.getLastLogin().toString() : null;

        UserCreated userCreated = new UserCreated(input.getId(),
                input.getFirstName(),
                input.getLastName(),
                input.getEmail(),
                birthdayFormat,
                input.getPhone(),
                input.getCreatedAt().toString(),
                lastLogin);

        List<CarResponse> carResponseList = input.getCars().stream()
                .map(car -> new CarResponse(
                		
                		car.getId(),
                        car.getColor(),
                        car.getLicensePlate(),
                        car.getModel(),
                        car.getCarYear()))
                .collect(Collectors.toList());

        
        userCreated.setCars(carResponseList);

        return userCreated;
    }
}
