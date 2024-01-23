package edu.pb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import edu.pb.LearningTemplate.*;
import edu.pb.model.WordAdapter;
import edu.pb.model.dictionary.Dictionary;
import edu.pb.model.dictionary.DifficultyLevel;
import edu.pb.model.dictionary.Language;
import edu.pb.model.words.Word;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Set;

public class Main {
    private static Dictionary dictionary;
    public static void main(String[] args) throws IOException {
        int port = 8080;

        dictionary = new Dictionary();
        dictionary.getDetails();


        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/api/learn/pol-en/easy", new LearnHandler("pol-eng", "easy", dictionary));
        server.createContext("/api/learn/pol-en/medium", new LearnHandler("pol-eng", "medium", dictionary));
        server.createContext("/api/learn/pol-en/hard", new LearnHandler("pol-eng", "hard", dictionary));
        server.createContext("/api/learn/pol-fr/easy", new LearnHandler("pol-fr", "easy", dictionary));
        server.createContext("/api/learn/pol-fr/medium", new LearnHandler("pol-fr", "medium", dictionary));
        server.createContext("/api/learn/pol-fr/hard", new LearnHandler("pol-fr", "hard", dictionary));
        server.createContext("/api/learn/pol-ge/easy", new LearnHandler("pol-ger", "easy", dictionary));
        server.createContext("/api/learn/pol-ge/medium", new LearnHandler("pol-ger", "medium", dictionary));
        server.createContext("/api/learn/pol-ge/hard", new LearnHandler("pol-ger", "hard", dictionary));

        server.createContext("/api/words", new MyHandler());

        server.createContext("/api/dictionary/pol-eng", new DictionaryHandler("pol-eng"));
        server.createContext("/api/dictionary/pol-fr", new DictionaryHandler("pol-fr"));
        server.createContext("/api/dictionary/pol-ger", new DictionaryHandler("pol-ger"));
        server.createContext("/api/dictionary/eng-pol", new DictionaryHandler("eng-pol"));
        server.createContext("/api/dictionary/fr-pol", new DictionaryHandler("fr-pol"));
        server.createContext("/api/dictionary/ger-pol", new DictionaryHandler("ger-pol"));

        server.createContext("/", new RootHandler());

        server.createContext("/api/randomWord", new RandomWord());
        server.createContext("/api/addWord", new AddWordHandler(dictionary));

        server.setExecutor(null);
        server.start();

        System.out.println("Serwer uruchomiony na porcie " + port);
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
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
            try {
                JsonObject jsonResponse = new JsonObject();
                jsonResponse.addProperty("message", "Learning session started for " + dictionaryName + " dictionary with difficulty " + difficulty);
                jsonResponse.addProperty("status", "success");

                LearningSessionTemplate learningSession = createLearningSession(difficulty, dictionary);
                List<LearningData> learningDataList = learningSession.startLearningSession();

                jsonResponse.add("data", gson.toJsonTree(learningDataList));

                String response = gson.toJson(jsonResponse);
                byte[] responseBytes = response.getBytes("UTF-8");

                t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                t.getResponseHeaders().add("Content-Type", "application/json");

                t.sendResponseHeaders(200, responseBytes.length);
                OutputStream os = t.getResponseBody();
                os.write(responseBytes);
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
                String errorResponse = "Internal Server Error";
                t.sendResponseHeaders(500, errorResponse.length());
                OutputStream os = t.getResponseBody();
                os.write(errorResponse.getBytes());
                os.close();
            }
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
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("dictionaryName", dictionaryName);
            jsonResponse.add("dictionaryContent", gson.toJsonTree(dictionary.getAllWords()));

            String response = gson.toJson(jsonResponse);
            byte[] responseBytes = response.getBytes("UTF-8");

            t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            t.getResponseHeaders().add("Content-Type", "application/json");

            t.sendResponseHeaders(200, responseBytes.length);
            OutputStream os = t.getResponseBody();
            os.write(responseBytes);
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
            t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            t.getResponseHeaders().add("Content-Type", "application/json");

            Word randomWord = dictionary.getRandomWord();
            String response = gson.toJson(randomWord);

            byte[] jsonResponseBytes = response.getBytes("UTF-8");

            t.sendResponseHeaders(200, jsonResponseBytes.length);
            OutputStream os = t.getResponseBody();
            os.write(jsonResponseBytes);
            os.close();
        }
    }
    public static class AddWordHandler implements HttpHandler {
        private final Dictionary dictionary;

        public AddWordHandler(Dictionary dictionary) {
            this.dictionary = dictionary;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {
            t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            t.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
            t.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");

            if (t.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                t.sendResponseHeaders(200, -1);
                return;
            }

            if ("POST".equals(t.getRequestMethod())) {
                String requestBody = getRequestBody(t);

                Gson gson = new GsonBuilder().create();
                Word newWord = gson.fromJson(requestBody, Word.class);


                Language langEN = new Language("English");
                dictionary.add(langEN);
                DifficultyLevel easy = new DifficultyLevel("easy");
                DifficultyLevel medium = new DifficultyLevel("medium");
                DifficultyLevel hard = new DifficultyLevel("hard");
                langEN.addLevel(easy);
                langEN.addLevel(medium);
                langEN.addLevel(hard);

                String difficulty = newWord.getDifficulty();
                if (difficulty.equals("easy")) {
                    easy.addWords(newWord);
                } else if (difficulty.equals("medium")) {
                    medium.addWords(newWord);
                } else if (difficulty.equals("hard")) {
                    hard.addWords(newWord);
                }


                JsonObject jsonResponse = new JsonObject();
                jsonResponse.addProperty("message", "Word added successfully");
                jsonResponse.addProperty("status", "success");

                String response = gson.toJson(jsonResponse);
                System.out.println("Received POST request to /api/addWord");
                System.out.println("Request Body: " + requestBody);

                t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                t.getResponseHeaders().add("Content-Type", "application/json");

                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                t.sendResponseHeaders(405, 0);
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
            String response = "Hello, this is your server response for the root path!";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
