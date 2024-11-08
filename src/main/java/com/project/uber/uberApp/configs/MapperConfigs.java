package com.project.uber.uberApp.configs;

import com.project.uber.uberApp.dto.PointDTO;
import com.project.uber.uberApp.utils.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfigs {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper() ;

        mapper.typeMap(PointDTO.class , Point.class)
                .setConverter(converter -> {
                    PointDTO pointDTO = converter.getSource() ;
                    return GeometryUtil.createPoint(pointDTO) ;
                });

        mapper.typeMap(Point.class , PointDTO.class)
                .setConverter(converter -> {
                    Point point = converter.getSource() ;
                    double[] coordinates = {point.getX() , point.getY()} ;
                    return new PointDTO(coordinates) ;
                });

        return mapper ;
    }
}
