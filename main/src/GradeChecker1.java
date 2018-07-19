import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GradeChecker1 {
    Map<Integer, Double> getMapFromExamFile(String filename) {
        try {
            HashMap<Integer, Double> map = new HashMap<>();
            BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
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
    Map<Integer, Double> getMapFromAssignFile(String filename) {
        try {
            HashMap<Integer, Double> map = new HashMap<>();
            BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
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
    Map<Integer, Double> getMapFromMiniexamFile(String filename) {
        try {
            HashMap<Integer, Double> map = new HashMap<>();
            BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitedStr = line.split(",");
                Double count = getCount(splitedStr);
                map.put(Integer.valueOf(splitedStr[0]), count / 14.0);
            }
            return map;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new HashMap<>();
    }
    Double getCount(String[] arr) {
        Double count = 0.0;
        for (int i = 1; i < arr.length; i++ ) {
            if (!arr[i].equals(""))
                count += 1.0;
        }
        return count;
    }
    Double calcScore(Double e, Double a, Double t) {
        return Math.ceil((70.0/100.0)*e + (25.0/60.0)*a + 5.0*t);
    }
    String  getGrade(Double examScore) {
        if (examScore >= 90.0) {
            return "秀";
        } else if( 90.0 > examScore && examScore >= 80.0) {
            return "優";
        } else if( 80.0 > examScore && examScore >= 70.0) {
            return "良";
        } else if( 70.0 > examScore && examScore >= 60.0) {
            return "可";
        } else {
            return "不可";
        }
    }
    void calcEachIdScore(Map<Integer, Double> examMap, Map<Integer, Double> assignMap, Map<Integer, Double> miniexamMap) {
        for (int i = 1; i < assignMap.keySet().size() + 1; i++) {
            Double examScore = examMap.getOrDefault(i, 0.0);
            Double assignScore = assignMap.get(i);
            Double miniexamScore = miniexamMap.getOrDefault(i, 0.0);
            Double score = calcScore(examScore, assignScore, miniexamScore);
            if (examMap.get(i) == null) {
                System.out.printf("%d, %f, , %f, %f, K\n", i, score, assignScore, miniexamScore);
            } else {
                System.out.printf("%d, %f, %f, %f, %f, %s\n", i, score, examScore, assignScore, miniexamScore, getGrade(examScore));
            }
        }
    }
    void run(String file1, String file2, String file3){
        Map<Integer, Double> examMap = getMapFromExamFile(file1);
        Map<Integer, Double> assignMap = getMapFromAssignFile(file2);
        Map<Integer, Double> miniexamMap = getMapFromMiniexamFile(file3);
        calcEachIdScore(examMap, assignMap, miniexamMap);
    }
    public static void main(String[] args){
        GradeChecker1 app = new GradeChecker1();
        app.run(args[0], args[1], args[2]);
    }
}