package edu.pb;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    private static final String BASE_URL = "http://localhost:8080"; // Zaktualizuj URL backendu

    public static void main(String[] args) {
        // Przykłady użycia
        get("/api/words"); // Przykładowe zapytanie GET
        post("/api/words", "{\"word\": \"example\", \"translation\": \"przykład\"}"); // Przykładowe zapytanie POST
    }

    public static void get(String path) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .build();

        sendRequest(client, request);
    }

    public static void post(String path, String requestBody) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        sendRequest(client, request);
    }

    private static void sendRequest(HttpClient client, HttpRequest request) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            String responseBody = response.body();

            System.out.println("Status Code: " + statusCode);
            System.out.println("Response Body: " + responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}