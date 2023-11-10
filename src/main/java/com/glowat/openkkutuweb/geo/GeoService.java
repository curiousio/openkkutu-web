package com.glowat.openkkutuweb.geo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GeoService {

  private final String apiKey;
  private final String apiDomain;
  private final ObjectMapper objectMapper;

  @Autowired
  public GeoService(
      @Value("${geo.api-key}") String apiKey,
      @Value("${geo.api-domain}") String apiDomain,
      ObjectMapper objectMapper
  ) {
    this.apiKey = apiKey;
    this.apiDomain = apiDomain;
    this.objectMapper = objectMapper;
  }

  @Cacheable(value = "ipGeoInfoCache", key = "#ip")
  public String getGeoCountry(String ip) {
    String response = requestHttp(apiDomain + "/lookup/" + ip + "/" + apiKey);
    try {
      JsonNode jsonNode = objectMapper.readTree(response);

      JsonNode geoLocation = jsonNode.get("geoLocation");
      return (geoLocation != null) ? geoLocation.get("country").textValue() : null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public String requestHttp(String url) {
    try {
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI(url))
          .GET()
          .build();

      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      return response.body();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
