package com.travelbnbf.payload;

import lombok.Data;

@Data
public class JWTTokenDto {

    private String token;
    private String type;
}
