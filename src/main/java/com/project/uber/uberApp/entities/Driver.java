package com.project.uber.uberApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
