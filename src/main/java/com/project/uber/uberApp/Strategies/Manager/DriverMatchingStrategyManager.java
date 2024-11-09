package com.project.uber.uberApp.Strategies.Manager;

import com.project.uber.uberApp.Strategies.DriverMatchingStrategy;
import com.project.uber.uberApp.Strategies.Implementation.DriverMatchingHighestRatedDriverStrategy;
import com.project.uber.uberApp.Strategies.Implementation.DriverMatchingNearestDriverStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DriverMatchingStrategyManager {
    private final DriverMatchingNearestDriverStrategy driverMatchingNearestDriverStrategy;
    private final DriverMatchingHighestRatedDriverStrategy driverMatchingHighestRatedDriverStrategy;

    public DriverMatchingStrategy getDriverMatchingStrategy(double riderRating) {
        if (riderRating >= 4.5) {
            return driverMatchingHighestRatedDriverStrategy;
        } else {
            return driverMatchingNearestDriverStrategy;
        }
    }
}
