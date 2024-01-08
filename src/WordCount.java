import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCount {
    /**
     * Returns a map of words and their counts.
     * @param text
     * @return map of words and their counts
     */
    public static Map<String, Integer> countWords(String text) {
        Map<String, Integer> words = new HashMap<>();
        Scanner scanner = new Scanner(text);
        while (scanner.hasNext()) {
            String word = scanner.next();
            words.put(word, words.getOrDefault(word, 0) + 1);
        }
        return words;
    }
}
