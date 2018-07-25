package domain.reader;

import java.util.Map;

public interface Reader {
    Map<Integer, Double> read(String filePath);
}
