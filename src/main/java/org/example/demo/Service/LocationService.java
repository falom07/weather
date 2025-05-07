package org.example.demo.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.demo.DTO.FullLocationDTO;
import org.example.demo.DTO.ShortLocationDTO;
import org.example.demo.Entity.LocationEntity;
import org.example.demo.Repository.LocationRepository;
import org.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("classpath:application.properties")
@Transactional
public class LocationService {

    @Value("${api.key}")
    private static String apiKey = "e8f417fb88d9a7fbab2d2ec05f6860f1";

    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    public LocationService(LocationRepository locationRepository, UserRepository userRepository) {
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }


    private FullLocationDTO getOneLocation(double lat, double lng) throws URISyntaxException, IOException, InterruptedException {
        String uri = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%.2f&lon=%.2f&lang=%s&units=%s&appid=%s", lat, lng, "ua", "metric", apiKey);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI(uri))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String location = response.body();

        FullLocationDTO fullLocationDTO = new ObjectMapper().readValue(location, FullLocationDTO.class);
        fullLocationDTO.getWeather().getFirst().setIcon("https://openweathermap.org/img/wn/" + fullLocationDTO.getWeather().getFirst().getIcon() + "@2x.png");

        return fullLocationDTO;

    }

    private List<ShortLocationDTO> getLocationsByName(String nameOfLocation) throws URISyntaxException, IOException, InterruptedException {
        String uri = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=%d&appid=%s", nameOfLocation, 5, apiKey);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI(uri))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String location = response.body();
        return new ObjectMapper().readValue(location, new TypeReference<List<ShortLocationDTO>>() {
        });
    }

    public List<FullLocationDTO> getAllWeatherByUserID(int userID) throws URISyntaxException, IOException, InterruptedException {
        List<LocationEntity> locationEntities =locationRepository.findAllByUserId(userID);
        List<FullLocationDTO> fullLocationDTO = new ArrayList<>();

        for (LocationEntity locationEntity : locationEntities) {
            double longitude = locationEntity.getLongitude().doubleValue();
            double latitude = locationEntity.getLatitude().doubleValue();

            fullLocationDTO.add(getOneLocation(latitude,longitude));
        }


        return fullLocationDTO;
    }

    public void deleteLocation(BigDecimal longitude,BigDecimal latitude,String loginID) throws URISyntaxException, IOException, InterruptedException {
        locationRepository.deleteWeather(longitude,latitude,loginID);
    }
}
