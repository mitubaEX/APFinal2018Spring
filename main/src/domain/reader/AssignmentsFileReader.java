package domain.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class AssignmentsFileReader implements Reader{
    public Map<Integer, Double> read(String filePath) {
        try {
            HashMap<Integer, Double> map = new HashMap<>();
            BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitedStr = line.split(",");
                Double sum = getSumAssign(splitedStr);
                map.put(Integer.valueOf(splitedStr[0]), sum);
            }
            return map;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new HashMap<>();
    }
    Double getSumAssign(String[] arr) {
        Double sum = 0.0;
        for (int i = 1; i < arr.length; i++ ) {
            if (!arr[i].equals(""))
                sum += Double.valueOf(arr[i]);
        }
        return sum;
    }
}
