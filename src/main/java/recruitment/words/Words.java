package recruitment.words;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Words class contains a single static method, getUniqueWordsFromSentence,
 * which extracts all unique words from a given sentence, converts them to lowercase, and returns them in a sorted list.
 */
public class Words {

    //The method is static, meaning it can be called without creating an instance of the Words class.
    //It takes a single parameter, sentence, which is a String.
    public static List<String> getUniqueWordsFromSentence(String sentence) {
        //If the sentence is null or empty, the method returns an empty list using Collections.emptyList().
        if (sentence == null || sentence.isEmpty()) {
            return Collections.emptyList();
        }
        //A regular expression pattern ("[a-zA-Z]+") is compiled to match sequences of alphabetic characters (both uppercase and lowercase).
        //The input sentence is converted to lowercase using sentence.toLowerCase(). This ensures that the matching is case-insensitive.
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        //A Matcher object is created from the pattern and the lowercase sentence to find all occurrences of the pattern in the sentence.
        Matcher matcher = pattern.matcher(sentence.toLowerCase());

        //A Set is used to store unique words. The HashSet implementation is chosen for its efficient add and contains operations.
        Set<String> uniqueWordsSet = new HashSet<>();
        //The matcher.find() method is used to find the next subsequence of the input sequence that matches the pattern.
        while (matcher.find()) {
            //The matcher.group() method returns the current matched subsequence. This subsequence (word) is added to the uniqueWordsSet.
            uniqueWordsSet.add(matcher.group());
        }

        //The set of unique words is converted to a list.
        List<String> uniqueWordsList = new ArrayList<>(uniqueWordsSet);
        //The list is sorted using Collections.sort(), which sorts the elements in their natural order (lexicographical order for strings).
        Collections.sort(uniqueWordsList);
        //The sorted list of unique words is returned.
        return uniqueWordsList;
    }
}