package com.project.uber.uberApp.services.Implementation;

import com.project.uber.uberApp.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {

    private static final String OSRM_API_BASE_URL = "http://router.project-osrm.org/route/v1/driving/";

    @Override
    public double calculateDistance(Point src, Point dest) {
// Correct format for OSRM: lon,lat;lon,lat
        String uri = src.getX() + "," + src.getY() + ";" + dest.getX() + "," + dest.getY();

        try {
            // Construct the full URL with coordinates
            String url = OSRM_API_BASE_URL + uri + "?overview=false&steps=false";
            OsrmResponseDTO osrmResponseDTO = RestClient.builder()
                    .baseUrl(OSRM_API_BASE_URL)
                    .build()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .body(OsrmResponseDTO.class) ;

            assert osrmResponseDTO != null;
            return osrmResponseDTO.getRoutes().get(0).getDistance() / 1000.0;
        } catch (Exception e) {
            throw new RuntimeException("Error while getting data from OSRM", e);
        }
    }
}

@Data
class OsrmResponseDTO {
    private List<OsrmRoutes> routes;
}

@Data
class OsrmRoutes {
    private Double distance;
}