package org.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FullLocationDTO {

    @JsonAlias("coord")
    private Coordinate coordinate;
    private List<Weather> weather;
    private Main2 main;
    private Sys sys;
    @JsonAlias("name")
    private String nameOfLocation;
    private int cod;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Coordinate {
        private double lon;
        private double lat;
    }



    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main2 {
        private int temp;
        private int humidity;
        @JsonAlias("feels_like")
        private int feelLike;

    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sys {
        private String country;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather {
        private String description;
        private String icon;
    }




}

