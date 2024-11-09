package com.project.uber.uberApp.Strategies.Manager;

import com.project.uber.uberApp.Strategies.Implementation.RideFareDefaultFareCalculationStrategy;
import com.project.uber.uberApp.Strategies.Implementation.RideFareSurgePricingFareCalculationStrategy;
import com.project.uber.uberApp.Strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class FareStrategyManager {

    private final RideFareDefaultFareCalculationStrategy rideFareDefaultFareCalculationStrategy;
    private final RideFareSurgePricingFareCalculationStrategy rideFareSurgePricingFareCalculationStrategy;

    private boolean isSurgedHours(LocalDateTime currentDateTime) {
        LocalTime currentTime = currentDateTime.toLocalTime();
        LocalTime surgeStartTime = LocalTime.of(18, 0); // Example: Surge pricing starts at 6 PM
        LocalTime surgeEndTime = LocalTime.of(22, 0); // Example: Surge pricing ends at 10 PM

        return !currentTime.isBefore(surgeStartTime) && currentTime.isBefore(surgeEndTime);
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy() {
        if (isSurgedHours(LocalDateTime.now())) {
            return rideFareSurgePricingFareCalculationStrategy;
        } else {
            return rideFareDefaultFareCalculationStrategy;
        }
    }
}
