package org.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.demo.DTO.FullLocationDTO;
import org.example.demo.DTO.ShortLocationDTO;
import org.example.demo.Service.LocationService;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&lang=ua&units=metric&appid=e8f417fb88d9a7fbab2d2ec05f6860f1"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        FullLocationDTO fullLocationDTO = new ObjectMapper().readValue(body, FullLocationDTO.class);
        System.out.println(fullLocationDTO);
        System.out.println();

//        LocationService locationService = new LocationService();

//        FullLocationDTO shortLocationDTO = locationService.getOneLocation(40.00,60.00);
//        System.out.printf(shortLocationDTO.toString());
//        List<ShortLocationDTO> dtoList = locationService.getLocations("Vin");
//
//        for (ShortLocationDTO dto : dtoList) {
//            System.out.println(dto.getLon() + " " + dto.getLat() + " -> " + dto.getName());
//            System.out.println(locationService.getOneLocation(dto.getLat(), dto.getLon()));

//        }
    }
}
//https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&lang=ua&units=metric&appid=e8f417fb88d9a7fbab2d2ec05f6860f1
//http://api.openweathermap.org/geo/1.0/direct?q=London&limit=5&appid=e8f417fb88d9a7fbab2d2ec05f6860f1
//
//1.Поиск одного
//2.Поиск всего
//Страничка
//связать ве єто
//