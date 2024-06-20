package recruitment.words;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * The HowManyWordsTest class tests the correctness of the Words.getUniqueWordsFromSentence() method by verifying that
 * it correctly extracts unique words from a sentence and handles case insensitivity and punctuation.
 */

public class HowManyWordsTest {
    @Test
    public void howManyWords() {
        // Call the method to get unique words from the sentence
        List<String> uniqueWords =
                Words.getUniqueWordsFromSentence("A cat Sat on a mat; a Monkey sat on the Cat.");

        // Define the expected unique words
        List<String> expectedResult = List.of("a", "cat", "sat", "on", "the", "mat", "monkey");

        // Assert statements to verify the results
        Assert.assertEquals(7, uniqueWords.size()); // Check the number of unique words
        for (String word : expectedResult) {
            Assert.assertTrue(String.format("Word '%s' should be included", word), uniqueWords.contains(word));
        }
    }
}
