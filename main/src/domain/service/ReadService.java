package domain.service;

import domain.reader.Reader;

import java.util.Map;

public class ReadService {
    public Map<Integer, Double> read(Reader reader, String filePath) {
        return reader.read(filePath);
    }
}
