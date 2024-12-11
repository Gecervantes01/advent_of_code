import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> lists = getData("input.txt");
        System.out.println(calcGap(lists.get(0), lists.get(1)));
        System.out.println(calcSimilarityScore(lists.get(0), lists.get(1)));
    }

    public static ArrayList<ArrayList<Integer>> getData(String filename) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        
        try {
            File data = new File(filename);
            Scanner fileReader = new Scanner(data);
            while(fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] locations = line.split(" ");

                list1.add(Integer.parseInt(locations[0]));
                list2.add(Integer.parseInt(locations[3]));
            }
            fileReader.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found!");
        }
        
        lists.add(list1);
        lists.add(list2);

        return lists;
    }

    public static int calcGap(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        Collections.sort(list1);
        Collections.sort(list2);

        int gap = 0;

        for(int i = 0; i < list1.size(); i++) {
            gap += Math.abs(list1.get(i) - list2.get(i));
        }
        return gap;
    }

    public static int calcSimilarityScore(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        Collections.sort(list1);
        Collections.sort(list2);

        int score = 0;
        for(Integer i : list1) {
            int counter = 0;
            if(list2.contains(i)) {
                for(int j = list2.indexOf(i); j < list2.size(); j++) {
                    if(i > list2.get(j)) { 
                        break; 
                    }
                    if(i.equals(list2.get(j))) {
                        counter++;
                    }
                }
                score += i * counter;
            }
        }
        return score;
    }
}