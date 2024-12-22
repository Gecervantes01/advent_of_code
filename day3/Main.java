import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        int[] nums = getIntstructions("day3/input.txt");
        int result = completeCalculations(nums);
        System.out.println(result);
    }

    public static int[] getIntstructions(String filename) {
        File input = new File(filename);
        ArrayList<Integer> nums = new ArrayList<>();

        try(Scanner readFile = new Scanner(input)) {

            Pattern instruction = Pattern.compile("mul[(](-?\\d{1,3}),(-?\\d{1,3})[)]", Pattern.CASE_INSENSITIVE);

            // Pattern enable = Pattern.compile("do[(][)]", Pattern.CASE_INSENSITIVE);
            // Pattern disable = Pattern.compile("don't[(][)]", Pattern.
            // CASE_INSENSITIVE);

            while(readFile.hasNextLine()) {
                String line = readFile.nextLine();
                Matcher matcher = instruction.matcher(line);
                // Matcher findEnable = enable.matcher(line);
                // Matcher findDisable = disable.matcher(line);
                // boolean isEnabled = true;

                // Answer to part 1
                while(matcher.find()) {
                    nums.add(Integer.valueOf(matcher.group(1)));
                    nums.add(Integer.valueOf(matcher.group(2)));
                }
            }
            readFile.close();
        } catch(FileNotFoundException e) {
            System.out.println("Could not find file with name: " + filename);
        }

        return nums.stream().mapToInt(i -> i).toArray();
    }

    public static int completeCalculations(int[] nums) {
        int total = 0;
        for(int i = 1; i < nums.length; i += 2) {
            total += mul(nums[i-1], nums[i]);
        }
        return total;
    }

    public static int mul(int x, int y) {
        return x * y;
    }
}