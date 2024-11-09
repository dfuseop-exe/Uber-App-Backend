package com.project.uber.uberApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {
    private UserDTO user;
    private Double rating;
    private Integer ratingCount;
    private Boolean available;
    private String vehicleId ;
}
