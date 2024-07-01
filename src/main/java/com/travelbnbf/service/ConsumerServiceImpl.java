package com.travelbnbf.service;

import com.travelbnbf.entity.Consumer;
import com.travelbnbf.payload.ConsumerDto;
import com.travelbnbf.payload.JWTTokenDto;
import com.travelbnbf.payload.LoginDto;
import com.travelbnbf.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsumerServiceImpl implements ConsumerService{

    @Autowired
    ConsumerRepository consumerRepository;

    @Autowired
    JWTService jwtService;

    @Override
    public String verifyLogin(LoginDto loginDto) {
        Optional<Consumer> byUsername = consumerRepository
                .findByUsername(loginDto.getUsername());
        if (byUsername.isPresent()){
            Consumer consumer = byUsername.get();

            if (BCrypt.checkpw(loginDto.getPassword(),consumer.getPassword())){
                String token = jwtService.generateToken(consumer);

                return token;
            }
        }
        return null;
    }

    @Override
    public ConsumerDto createConsumer(ConsumerDto consumerDto) {
        Consumer savedData = consumerRepository.save(dtoToEntity(consumerDto));
        return entityToDto(savedData);
    }

    Consumer dtoToEntity(ConsumerDto dto){
        Consumer entity = new Consumer();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    ConsumerDto entityToDto(Consumer entity){
        ConsumerDto dto = new ConsumerDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        return dto;
    }
}
