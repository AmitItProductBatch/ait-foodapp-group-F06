package com.ait.app.serviceImpl;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.springframework.stereotype.Service;

import com.ait.app.service.GeocodingService;

@Service
public class GeocodingServiceImpl implements GeocodingService {

    @Override
    public double[] getCoordinates(String address) {

        try {

        	String encodedAddress =
        	        URLEncoder.encode(address, StandardCharsets.UTF_8);

        	String url =
        	        "https://nominatim.openstreetmap.org/search"
        	        + "?q=" + encodedAddress
        	        + "&format=jsonv2"
        	        + "&limit=1"
        	        + "&countrycodes=in";

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent", "AIT-FoodDelivery-App/1.0")
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            JSONArray jsonArray =
                    new JSONArray(response.body());

            if (jsonArray.isEmpty()) {
                throw new RuntimeException(
                        "Location not found for address: " + address
                );
            }

            double latitude =
                    jsonArray.getJSONObject(0).getDouble("lat");

            double longitude =
                    jsonArray.getJSONObject(0).getDouble("lon");

            return new double[] { latitude, longitude };

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to get latitude and longitude", e
            );
        }
    }
}
