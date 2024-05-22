package com.example.carsystem.map;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.carsystem.dto.SigninResponse;
import com.example.carsystem.models.UserEntity;

@Component
public class SigninResponseMapper implements Mapper<UserEntity, SigninResponse> {

    @Override
    public SigninResponse map(UserEntity input) {

        Date birthday = input.getBirthday();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String birthdayFormat = dateFormat.format(birthday);

        return new SigninResponse(input.getId(),
                input.getFirstName(),
                input.getLastName(),
                input.getEmail(),
                birthdayFormat,
                input.getPhone(),
                input.getLastLogin().toString(),
                input.getCreatedAt().toString());
    }
}

