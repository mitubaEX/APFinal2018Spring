package domain.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class MiniexamFileReader implements Reader{
    public Map<Integer, Double> read(String filePath) {
        try {
            HashMap<Integer, Double> map = new HashMap<>();
            BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitedStr = line.split(",");
                Double count = getMiniexamCount(splitedStr);
                map.put(Integer.valueOf(splitedStr[0]), count / 14.0);
            }
            return map;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new HashMap<>();
    }
    Double getMiniexamCount(String[] arr) {
        Double count = 0.0;
        for (int i = 1; i < arr.length; i++ ) {
            if (!arr[i].equals(""))
                count += 1.0;
        }
        return count;
    }
}
