package edu.pb;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import edu.pb.model.Dictionary;
import edu.pb.model.words.Word;
import edu.pb.LearningTemplate.LearningSessionTemplate;
import edu.pb.LearningTemplate.EasyLearningSession;
import edu.pb.LearningTemplate.MediumLearningSession;
import edu.pb.LearningTemplate.HardLearningSession;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        int port = 8080; // Twój port serwera

        // Przykładowo, zestaw obsługiwanych języków: angielski i polski
        Set<String> supportedLanguages = new HashSet<>(Arrays.asList("pol-eng", "pol-fra", "pol-ger", "eng-pol", "fra-pol", "ger-pol"));
        Dictionary dictionary = new Dictionary(supportedLanguages);
        dictionary.populateDictionary("src/main/java/edu/pb/model/english_polish.txt");

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/api/learn/pol-eng/hard", new LearnHandler("pol-eng", "hard", dictionary));
        server.createContext("/api/learn/pol-eng/easy", new LearnHandler("pol-eng", "easy", dictionary));
        server.createContext("/api/learn/pol-eng/medium", new LearnHandler("pol-eng", "medium", dictionary));

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

        public LearnHandler(String dictionaryName, String difficulty, Dictionary dictionary) {
            this.dictionaryName = dictionaryName;
            this.difficulty = difficulty;
            this.dictionary = dictionary;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "Learning session started for " + dictionaryName + " dictionary with difficulty " + difficulty;
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();

            // Rozpocznij sesję nauki dla danego słownika i poziomu trudności
            LearningSessionTemplate learningSession = createLearningSession(difficulty, dictionary);
            learningSession.startLearningSession();
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
