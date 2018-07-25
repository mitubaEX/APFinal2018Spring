package domain.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ExamFileReader implements Reader{
    public Map<Integer, Double> read(String filePath) {
        try {
            HashMap<Integer, Double> map = new HashMap<>();
            BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitedStr = line.split(",");
                map.put(Integer.valueOf(splitedStr[0]), Double.valueOf(splitedStr[1]));
            }
            return map;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new HashMap<>();
    }
}
