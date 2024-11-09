package com.project.uber.uberApp.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Point;

@Entity
@Data
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double rating;
    private Integer ratingCount;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean available;
    private String vehicleId ;

    @Column(columnDefinition = "Geometry(Point , 4326)")
    Point currentLocation;
}
