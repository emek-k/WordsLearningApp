package edu.pb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import edu.pb.model.dictionary.Dictionary;
import edu.pb.model.WordAdapter;
import edu.pb.model.words.Word;
import edu.pb.LearningTemplate.LearningSessionTemplate;
import edu.pb.LearningTemplate.EasyLearningSession;
import edu.pb.LearningTemplate.MediumLearningSession;
import edu.pb.LearningTemplate.HardLearningSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    private static Dictionary dictionary; // Declare the dictionary as a static field
    public static void main(String[] args) throws IOException {
        int port = 8080; // Twój port serwera

        // Przykładowo, zestaw obsługiwanych języków: angielski i polski
        //Set<String> supportedLanguages = new HashSet<>(Arrays.asList("pol-eng", "pol-fr", "pol-ger", "eng-pol", "fr-pol", "ger-pol"));
        dictionary = new Dictionary();
        dictionary.populateDictionary("src/main/java/edu/pb/model/english_polish.txt");

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/api/learn/pol-en/easy", new LearnHandler("pol-eng", "easy", dictionary));
        server.createContext("/api/learn/pol-en/medium", new LearnHandler("pol-eng", "medium", dictionary));
        server.createContext("/api/learn/pol-en/hard", new LearnHandler("pol-eng", "hard", dictionary));
        server.createContext("/api/learn/eng-pol/easy", new LearnHandler("eng-pol", "easy", dictionary));
        server.createContext("/api/learn/eng-pol/medium", new LearnHandler("eng-pol", "medium", dictionary));
        server.createContext("/api/learn/eng-pol/hard", new LearnHandler("eng-pol", "hard", dictionary));
        server.createContext("/api/learn/pol-fr/easy", new LearnHandler("pol-fr", "easy", dictionary));
        server.createContext("/api/learn/pol-fr/medium", new LearnHandler("pol-fr", "medium", dictionary));
        server.createContext("/api/learn/pol-fr/hard", new LearnHandler("pol-fr", "hard", dictionary));
        server.createContext("/api/learn/fr-pol/easy", new LearnHandler("fr-pol", "easy", dictionary));
        server.createContext("/api/learn/fr-pol/medium", new LearnHandler("fr-pol", "medium", dictionary));
        server.createContext("/api/learn/fr-pol/hard", new LearnHandler("fr-pol", "hard", dictionary));
        server.createContext("/api/learn/pol-ger/easy", new LearnHandler("pol-ger", "easy", dictionary));
        server.createContext("/api/learn/pol-ger/medium", new LearnHandler("pol-ger", "medium", dictionary));
        server.createContext("/api/learn/pol-ger/hard", new LearnHandler("pol-ger", "hard", dictionary));
        server.createContext("/api/learn/ger-pol/easy", new LearnHandler("ger-pol", "easy", dictionary));
        server.createContext("/api/learn/ger-pol/medium", new LearnHandler("ger-eng", "medium", dictionary));
        server.createContext("/api/learn/ger-pol/hard", new LearnHandler("ger-eng", "hard", dictionary));

        // Dodaj obsługę ścieżki "/api/words"
        server.createContext("/api/words", new MyHandler());

        server.createContext("/api/dictionary/pol-eng", new DictionaryHandler("pol-eng"));
        server.createContext("/api/dictionary/pol-fr", new DictionaryHandler("pol-fr"));
        server.createContext("/api/dictionary/pol-ger", new DictionaryHandler("pol-ger"));
        server.createContext("/api/dictionary/eng-pol", new DictionaryHandler("eng-pol"));
        server.createContext("/api/dictionary/fr-pol", new DictionaryHandler("fr-pol"));
        server.createContext("/api/dictionary/ger-pol", new DictionaryHandler("ger-pol"));

        // Dodaj obsługę ścieżki "/"
        server.createContext("/", new RootHandler());

        server.createContext("/api/randomWord", new RandomWord());
        server.createContext("/api/addWord", new AddWordHandler(dictionary));

        server.setExecutor(null); // używa domyślnego egzekutora
        server.start();

        System.out.println("Serwer uruchomiony na porcie " + port);
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
    static class LearnHandler implements HttpHandler {
        private final String dictionaryName;
        private final String difficulty;
        private final Dictionary dictionary;
        private static final Gson gson = new GsonBuilder()
                .registerTypeAdapter(Word.class, new WordAdapter())
                .setPrettyPrinting()
                .create();

        public LearnHandler(String dictionaryName, String difficulty, Dictionary dictionary) {
            this.dictionaryName = dictionaryName;
            this.difficulty = difficulty;
            this.dictionary = dictionary;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {
            // Tutaj zamiast wysyłania prostego tekstu, przygotuj obiekt JSON
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("message", "Learning session started for " + dictionaryName + " dictionary with difficulty " + difficulty);
            jsonResponse.addProperty("status", "success");

            // Rozpocznij sesję nauki dla danego słownika i poziomu trudności
            LearningSessionTemplate learningSession = createLearningSession(difficulty, dictionary);
            learningSession.startLearningSession();

            // Dodaj informacje o stanie sesji
            jsonResponse.addProperty("difficulty", learningSession.getDifficulty());
            jsonResponse.addProperty("progress", learningSession.getProgress());

            // Konwertuj obiekt JSON na tekst
            String response = gson.toJson(jsonResponse);

            // Dodaj nagłówek CORS
            t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            t.getResponseHeaders().add("Content-Type", "application/json");

            // Wyslij odpowiedź
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        private LearningSessionTemplate createLearningSession(String difficulty, Dictionary dictionary) {
            switch (difficulty) {
                case "easy":
                    return new EasyLearningSession(dictionary, difficulty);
                case "medium":
                    return new MediumLearningSession(dictionary, difficulty);
                case "hard":
                    return new HardLearningSession(dictionary, difficulty);
                default:
                    throw new IllegalArgumentException("Invalid difficulty level: " + difficulty);
            }
        }
    }

    static class DictionaryHandler implements HttpHandler {
        private final String dictionaryName;
        private static final Gson gson = new GsonBuilder()
                .registerTypeAdapter(Word.class, new WordAdapter())
                .setPrettyPrinting()
                .create();

        public DictionaryHandler(String dictionaryName) {
            this.dictionaryName = dictionaryName;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {
            // Dodaj nagłówek CORS
            t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            t.getResponseHeaders().add("Content-Type", "application/json"); // Dodaj ten nagłówek

            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("dictionaryName", dictionaryName);
            jsonResponse.add("dictionaryContent", gson.toJsonTree(dictionary.getAllWords()));

            String response = gson.toJson(jsonResponse); // Użyj Gson do konwersji obiektu jsonResponse na JSON

            byte[] jsonResponseBytes = response.getBytes("UTF-8");

            t.sendResponseHeaders(200, jsonResponseBytes.length);
            OutputStream os = t.getResponseBody();
            os.write(jsonResponseBytes);
            os.close();
        }
    }

    static class RandomWord implements HttpHandler {
        private static final Gson gson = new GsonBuilder()
                .registerTypeAdapter(Word.class, new WordAdapter())
                .setPrettyPrinting()
                .create();

        @Override
        public void handle(HttpExchange t) throws IOException {
            // Dodaj nagłówek CORS
            t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            t.getResponseHeaders().add("Content-Type", "application/json"); // Dodaj ten nagłówek

            // Generate a new random word for each request
            Word randomWord = dictionary.getRandomWord();

            String response = gson.toJson(randomWord); // Użyj Gson do konwersji obiektu jsonResponse na JSON

            byte[] jsonResponseBytes = response.getBytes("UTF-8");

            t.sendResponseHeaders(200, jsonResponseBytes.length);
            OutputStream os = t.getResponseBody();
            os.write(jsonResponseBytes);
            os.close();
        }
    }
    public static class AddWordHandler implements HttpHandler {
        private final Dictionary dictionary; // Assuming you have a list of words

        public AddWordHandler(Dictionary dictionary) {
            this.dictionary = dictionary;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {
            // Allow requests from any origin (you can refine this based on your needs)
            t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            t.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
            t.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");

            // Check if it's a preflight request (OPTIONS) and handle it
            if (t.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                t.sendResponseHeaders(200, -1);
                return;
            }

            if ("POST".equals(t.getRequestMethod())) {
                // Read the JSON file from the request body
                String requestBody = getRequestBody(t);

                // Parse the JSON file into a Word object
                Gson gson = new GsonBuilder().create();
                Word newWord = gson.fromJson(requestBody, Word.class);

                // Add the new word to the dictionary
                dictionary.addWord(newWord.getName(), newWord);

                // Prepare the response
                JsonObject jsonResponse = new JsonObject();
                jsonResponse.addProperty("message", "Word added successfully");
                jsonResponse.addProperty("status", "success");

                // Convert the response to JSON
                String response = gson.toJson(jsonResponse);
                System.out.println("Received POST request to /api/addWord");
                System.out.println("Request Body: " + requestBody);

                // Set response headers
                t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                t.getResponseHeaders().add("Content-Type", "application/json");

                // Send the response
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                // Handle other HTTP methods if needed
                t.sendResponseHeaders(405, 0); // 405 Method Not Allowed
                t.close();
            }
        }
        private String getRequestBody(HttpExchange exchange) throws IOException {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))) {
                StringBuilder requestBody = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    requestBody.append(line);
                }
                return requestBody.toString();
            }
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
