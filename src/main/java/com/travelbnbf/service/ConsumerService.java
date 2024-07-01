package com.travelbnbf.service;

import com.travelbnbf.payload.ConsumerDto;
import com.travelbnbf.payload.JWTTokenDto;
import com.travelbnbf.payload.LoginDto;

public interface ConsumerService {
     String verifyLogin(LoginDto loginDto);

    ConsumerDto createConsumer(ConsumerDto consumerDto);
}
