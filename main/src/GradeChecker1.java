import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GradeChecker1 {
    Map<String, Integer>  gradeMap = new HashMap<>();

    // read method
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

    // each calc
    List<Double> passingList = new ArrayList<>();
    List<Double> allList = new ArrayList<>();
    Double calcScore(Double e, Double a, Double t) {
        return Math.ceil((70.0/100.0)*e + (25.0/60.0)*a + 5.0*t);
    }
    String  getGrade(Double score, Double examScore, Double miniexam) {
        allList.add(score);
        if (score >= 90.0) {
            gradeMap.put("秀", gradeMap.getOrDefault("秀", 0) + 1);
            passingList.add(score);
            return "秀";
        } else if( 90.0 > score && score >= 80.0) {
            gradeMap.put("優", gradeMap.getOrDefault("優", 0) + 1);
            passingList.add(score);
            return "優";
        } else if( 80.0 > score && score >= 70.0) {
            gradeMap.put("良", gradeMap.getOrDefault("良", 0) + 1);
            passingList.add(score);
            return "良";
        } else if( 70.0 > score && score >= 60.0) {
            gradeMap.put("可", gradeMap.getOrDefault("可", 0) + 1);
            passingList.add(score);
            return "可";
        }  else if (score < 60 && miniexam * 14.0 <= 7.0) {
            gradeMap.put("＊", gradeMap.getOrDefault("＊", 0) + 1);
            return "＊";
        }  else {
            gradeMap.put("不可", gradeMap.getOrDefault("不可", 0) + 1);
            return "不可";
        }
    }
    EachNumbers createEachNumbers(List<Double> list) {
        Double max = 0.0;
        Double min = Double.MAX_VALUE;
        Double sum = 0.0;
        for (Double num: list) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            sum += num;
        }
        return new EachNumbers(max, min, sum / list.size());
    }
    void printEachNumbers() {
        EachNumbers all = createEachNumbers(allList);
        EachNumbers passing = createEachNumbers(passingList);

        System.out.printf("Avg: %f (%f)\n", all.avg, passing.avg);
        System.out.printf("Max: %f (%f)\n", all.max, passing.max);
        System.out.printf("Min: %f (%f)\n", all.min, passing.min);
    }
    void calcEachIdScore(Map<Integer, Double> examMap, Map<Integer, Double> assignMap, Map<Integer, Double> miniexamMap) {
        for (int i = 1; i < assignMap.keySet().size() + 1; i++) {
            Double examScore = examMap.getOrDefault(i, 0.0);
            Double assignScore = assignMap.get(i);
            Double miniexamScore = miniexamMap.getOrDefault(i, 0.0);
            Double score = calcScore(examScore, assignScore, miniexamScore);
            if (Math.ceil(examScore) >= 80.0) {
                System.out.println(Math.ceil(examScore) + " " + score);
                if (Math.ceil(examScore) >= score) {
                    score = examScore;
                }
                System.out.println(Math.ceil(examScore) + " " + score);
            }
//            if (examScore >= 79.0) {
//                if (Math.ceil(examScore) >= score) {
//                    score = Math.ceil(examScore);
//                }
//            }
            if (examMap.get(i) == null) {
                allList.add(score);
                gradeMap.put("K", gradeMap.getOrDefault("K", 0) + 1);
                System.out.printf("%d, %f, , %f, %f, K\n", i, score, assignScore, miniexamScore);
            } else {
                System.out.printf("%d, %f, %f, %f, %f, %s\n", i, score, examScore, assignScore, miniexamScore, getGrade(score, examScore, miniexamScore));
            }
        }

        printEachNumbers();

        for (String str: gradeMap.keySet()) {
            System.out.printf("%s: %d\n", str, gradeMap.get(str));
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