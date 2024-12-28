package com.project.uber.uberApp.repositories;

import com.project.uber.uberApp.entities.Payment;
import com.project.uber.uberApp.entities.Ride;
import com.project.uber.uberApp.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {

}
