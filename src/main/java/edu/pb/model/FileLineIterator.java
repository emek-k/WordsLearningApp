package edu.pb.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FileLineIterator implements Iterator<String>, AutoCloseable {
    private BufferedReader reader;
    private String nextLine;

    public FileLineIterator(String filePath) throws IOException {
        reader = new BufferedReader(new FileReader(filePath));
        nextLine = reader.readLine();
    }

    @Override
    public boolean hasNext() {
        return nextLine != null;
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        String line = nextLine;
        try {
            nextLine = reader.readLine();
        } catch (IOException e) {
            close();
            throw new RuntimeException("Error reading next line", e);
        }
        if (nextLine == null) {
            close();
        }
        return line;
    }

    @Override
    public void close() {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error closing reader", e);
        }
    }
}
