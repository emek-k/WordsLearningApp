package edu.pb;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {
        int port = 8080; // Twój port serwera
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Dodaj obsługę ścieżki "/api/words"
        server.createContext("/api/words", new MyHandler());


        server.createContext("/api/dictionary/pol-eng", new DictionaryHandler("pol-eng"));


        server.createContext("/api/dictionary/pol-fra", new DictionaryHandler("pol-fra"));

        server.createContext("/api/dictionary/pol-ger", new DictionaryHandler("pol-ger"));

        server.createContext("/api/dictionary/eng-pol", new DictionaryHandler("eng-pol"));

        server.createContext("/api/dictionary/fra-pol", new DictionaryHandler("fra-pol"));

        server.createContext("/api/dictionary/ger-pol", new DictionaryHandler("ger-pol"));

        // Dodaj obsługę ścieżki "/"
        server.createContext("/", new RootHandler());

        server.setExecutor(null); // używa domyślnego egzekutora
        server.start();

        System.out.println("Serwer uruchomiony na porcie " + port);

        // Wysyłaj zapytania do serwera po uruchomieniu
        sendGetRequest("/api/words");
        sendPostRequest("/api/words", "{\"word\": \"example\", \"translation\": \"przykład\"}");
    }

    static class MyHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            // Tutaj możesz obsłużyć żądanie HTTP i zwrócić odpowiedź
            String response = "Hello, this is your server response for /api/words!";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    static class DictionaryHandler implements HttpHandler {
        private final String dictionaryName;

        public DictionaryHandler(String dictionaryName) {
            this.dictionaryName = dictionaryName;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {
            // Obsługa zapytania dla danego słownika
            String response = "Hello, this is your response for the " + dictionaryName + " dictionary!";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            // Obsługa żądania na ścieżkę "/"
            String response = "Hello, this is your server response for the root path!";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static void sendGetRequest(String path) throws IOException {
        System.out.println("Sending GET request to: " + path);
        RestClient.get(path);
    }

    static void sendPostRequest(String path, String requestBody) throws IOException {
        System.out.println("Sending POST request to: " + path);
        RestClient.post(path, requestBody);
    }
}
