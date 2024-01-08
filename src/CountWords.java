import java.util.Scanner;

public class CountWords {

    /**
     * Counts the number of words in a text.
     * @param text
     * @return number of words
     */
    public static int countWords(String text) {
        int count = 0;
        Scanner scanner = new Scanner(text);
        while (scanner.hasNext()) {
            count++;
        }
        return count;
    }
}