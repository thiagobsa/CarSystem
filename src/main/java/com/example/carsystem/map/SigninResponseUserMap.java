package com.example.carsystem.map;

import java.text.SimpleDateFormat;
import com.example.carsystem.dto.SigninResponse;
import com.example.carsystem.model.UserEntity;

public class SigninResponseUserMap implements Mapper<UserEntity, SigninResponse>{
	
	
    public SigninResponse map(UserEntity input){

    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String birthdayFormat = dateFormat.format(input.getBirthday());
        

        return new SigninResponse(
        		
        		input.getId(),
                input.getFirstName(),
                input.getLastName(),
                birthdayFormat,
                input.getPhone(),
                input.getEmail(),
                input.getLastLogin().toString(),
                input.getCreatedAt().toString());
        
        
    }
    

}
