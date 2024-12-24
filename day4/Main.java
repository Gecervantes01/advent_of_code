import java.io.*;
import java.util.*;

public class Main {

    final static int MATRIX_SIZE = 140;

    public static void main(String[] args) {
        char[][] crossword = fillMatrix("day4/input.txt");
        System.out.println(checkCrossword("XMAS", crossword));
    }

    public static char[][] fillMatrix(String filename) {
        File input = new File(filename);
        char[][] result = new char[MATRIX_SIZE][MATRIX_SIZE];
        int counter = 0;
        try(Scanner readFile = new Scanner(input)) {
            while(readFile.hasNextLine()) {
                String line = readFile.nextLine();
                char[] row = line.toCharArray();
                for(int i = 0; i < row.length; i++) {
                    result[counter][i] = row[i];
                }
                counter++;
            }
        } catch(FileNotFoundException e) {
            System.out.println("Couldn't find file with name: " + filename);
        }
        return result;
    }

    public static int checkCrossword(String wordToFind, char[][] crossword) {
        int occurrences = 0;
        char start = wordToFind.charAt(0);
        for(int i = 0; i < MATRIX_SIZE - 1; i++) {
            for(int j = 0; j < MATRIX_SIZE - 1; j++) {
                if(crossword[i][j] == start) {
                    if(isHorizontal(crossword, i, j, wordToFind)) {
                        occurrences++;
                    }
                    if(isReverseHorizontal(crossword, i, j, wordToFind)) {
                        occurrences++;
                    }
                    if(isVertical(crossword, i, j, wordToFind)) {
                        occurrences++;
                    }
                    if(isReverseVertical(crossword, i, j, wordToFind)) {
                        occurrences++;
                    }
                    if(isDiagTopLeft(crossword, i, j, wordToFind)) {
                        occurrences++;
                    }
                    if(isDiagTopRight(crossword, i, j, wordToFind)) {
                        occurrences++;
                    }
                    if(isDiagBottomLeft(crossword, i, j, wordToFind)) {
                        occurrences++;
                    }
                    if(isDiagBottomRight(crossword, i, j, wordToFind)) {
                        occurrences++;
                    }
                }
            }
        }
        return occurrences;
    }

    /**
     * Checks for match horizontally (left to right).
     * 
     * @param crossword :matrix to be searched.
     * @param x :starting x index.
     * @param y :starting y index.
     * @param wordToFind :word to be matched.
     * @return true, if word is found horizontally.
     */
    public static boolean isHorizontal(char[][] crossword, int x, int y, String wordToFind) {
        if(x > (MATRIX_SIZE - 4)) {
            return false;
        }

        for(int i = 1; i < 4; i++) {
            if(crossword[y][x + i] != wordToFind.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks for match horizontally but in reverse (right to left).
     * 
     * @param crossword :matrix to be searched.
     * @param x :starting x index.
     * @param y :starting y index.
     * @param wordToFind :word to be matched.
     * @return true, if word is found horizontally.
     */
    public static boolean isReverseHorizontal(char[][] crossword, int x, int y, String wordToFind) {
        if(x < 3) {
            return false;
        }

        for(int i = 1; i < 4; i++) {
            if(crossword[y][x - i] != wordToFind.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks for match vertically (top to bottom).
     * 
     * @param crossword :matrix to be searched.
     * @param x :starting x index.
     * @param y :starting y index.
     * @param wordToFind :word to be matched.
     * @return true, if word is found vertically.
     */
    public static boolean isVertical(char[][]crossword, int x, int y, String wordToFind) {
        if(y > (MATRIX_SIZE - 4)) {
            return false;
        }
        for(int i = 1; i < 4; i++) {
            if(crossword[y + i][x] != wordToFind.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks for match vertically but in reverse (bottom to top).
     * 
     * @param crossword :matrix to be searched.
     * @param x :starting x index.
     * @param y :starting y index.
     * @param wordToFind :word to be matched.
     * @return true, if word is found vertically.
     */
    public static boolean isReverseVertical(char[][]crossword, int x, int y, String wordToFind) {
        if(y < 3) {
            return false;
        }
        for(int i = 1; i < 4; i++) {
            if(crossword[y - i][x] != wordToFind.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks for match diagonally towards the bottom right.
     * 
     * @param crossword :matrix to be searched.
     * @param x :starting x index.
     * @param y :starting y index.
     * @param wordToFind :word to be matched.
     * @return true, if word is found diagonally.
     */
    public static boolean isDiagBottomRight(char[][]crossword, int x, int y, String wordToFind) {
        if(y > (MATRIX_SIZE - 4) || x > (MATRIX_SIZE - 4)) {
            return false;
        }
        for(int i = 1; i < 4; i++) {
            if(crossword[y + i][x + i] != wordToFind.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks for match diagonally towards the bottom left.
     * 
     * @param crossword :matrix to be searched.
     * @param x :starting x index.
     * @param y :starting y index.
     * @param wordToFind :word to be matched.
     * @return true, if word is found diagonally.
     */
    public static boolean isDiagBottomLeft(char[][]crossword, int x, int y, String wordToFind) {
        if(y > (MATRIX_SIZE - 4) || x < 3) {
            return false;
        }
        for(int i = 1; i < 4; i++) {
            if(crossword[y + i][x - i] != wordToFind.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks for match diagonally towards the top right.
     * 
     * @param crossword :matrix to be searched.
     * @param x :starting x index.
     * @param y :starting y index.
     * @param wordToFind :word to be matched.
     * @return true, if word is found diagonally.
     */
    public static boolean isDiagTopRight(char[][]crossword, int x, int y, String wordToFind) {
        if(y < 3 || x > (MATRIX_SIZE - 4)) {
            return false;
        }
        for(int i = 1; i < 4; i++) {
            if(crossword[y - i][x + i] != wordToFind.charAt(i)) {
                return false;
            }
        }
        return true;
    }

        /**
     * Checks for match diagonally towards top left.
     * 
     * @param crossword :matrix to be searched.
     * @param x :starting x index.
     * @param y :starting y index.
     * @param wordToFind :word to be matched.
     * @return true, if word is found diagonally.
     */
    public static boolean isDiagTopLeft(char[][]crossword, int x, int y, String wordToFind) {
        if(y < 3 || x < 3) {
            return false;
        }
        for(int i = 1; i < 4; i++) {
            if(crossword[y - i][x - i] != wordToFind.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
