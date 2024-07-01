package com.travelbnbf.controller;

import com.travelbnbf.payload.ConsumerDto;
import com.travelbnbf.payload.JWTTokenDto;
import com.travelbnbf.payload.LoginDto;
import com.travelbnbf.repository.ConsumerRepository;
import com.travelbnbf.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/consumer")
public class ConsumerController {

    //http://localhost:8080/api/v1/consumer/createConsumer
    //http://localhost:8080/api/v1/consumer/login
    @Autowired
    ConsumerRepository consumerRepository;
    @Autowired
    ConsumerService consumerService;

    @PostMapping("/createConsumer")
    public ResponseEntity<?> createConsumer(@RequestBody ConsumerDto consumerDto){
        if (consumerRepository.existsByUsername(consumerDto.getUsername())){
            return new ResponseEntity<>("username already present", HttpStatus.BAD_REQUEST);
        }
        if (consumerRepository.existsByEmail(consumerDto.getEmail())){
            return new ResponseEntity<>("email already present",HttpStatus.BAD_REQUEST);
        }

        consumerDto.setPassword(BCrypt.hashpw(consumerDto.getPassword(),BCrypt.gensalt(10)));

        ConsumerDto consumer = consumerService.createConsumer(consumerDto);
        return new ResponseEntity<>(consumer,HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<?> verifyLogin(@RequestBody LoginDto loginDto){
        String token = consumerService.verifyLogin(loginDto);
        if(token!= null){
            JWTTokenDto jwtTokenDto = new JWTTokenDto();
            jwtTokenDto.setToken(token);
            jwtTokenDto.setType("jwt token");

            return new ResponseEntity<>(jwtTokenDto,HttpStatus.OK);
        }
        return new ResponseEntity<>("invalid token",HttpStatus.OK);
    }

}
