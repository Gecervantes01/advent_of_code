import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<int[]> data = getData("day2/input.txt");
        System.out.println(calcTotalSafe(data));
    }

    public static ArrayList<int[]> getData(String filename) {
        ArrayList<int[]> reports = new ArrayList<>();
        File input = new File(filename);
        try (Scanner fileReader = new Scanner(input)){
            while(fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                int[] report = Arrays.stream(line.split(" ")).mapToInt(Integer :: parseInt).toArray();
                reports.add(report);
            }
        } catch(FileNotFoundException e) {
            System.out.println("Could'nt find file with name: " + filename);
        }
    
        return reports;
    }

    public static int calcTotalSafe(ArrayList<int[]> reports) {
        int counter = 0;
        for(int i = 0; i < reports.size(); i++) {
            counter += isSafe(reports.get(i)) ? 1 : 0;
        }
        return counter;
    }

    // TODO
    // Solve part 2
    public static boolean isSafe(int[] report) {
        int counter = 0;
        int errorCounter = 0;
        for(int i = 1; i < report.length; i++) {
            int diff = Math.abs(report[i] - report[i - 1]);
            if(diff == 0 || diff > 3 || diff < 1) {
                if(errorCounter != 0) {
                    return false;
                }
                errorCounter++;
            }
            counter = (report[i] - report[i - 1]) < 0 ? counter - 1 : counter + 1;
        }
        counter *= counter < 0 ? -1 : 1;
        return Math.abs(counter - (report.length - 1)) <= 1;
    }

    // 612, 619
}
