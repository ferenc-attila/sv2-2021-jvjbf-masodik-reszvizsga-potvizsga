package algorithms;

import java.util.ArrayList;
import java.util.List;

public class Sentences {

    private List<String> listOfSentences = new ArrayList<>();

    public void addSentence(String sentence) {
        validateSentence(sentence);
        listOfSentences.add(sentence);
    }

    private void validateSentence(String sentence) {
        if (sentence == null || sentence.isBlank()) {
            throw new IllegalArgumentException("Sentence cannot be empty or null!");
        }
        if (sentence.charAt(0) <= 'A' || sentence.charAt(0) >= 'Z') {
            throw new IllegalArgumentException("Sentence must start with capital!");
        }
        if (!sentence.endsWith(".") && !sentence.endsWith("!") && !sentence.endsWith("?")) {
            throw new IllegalArgumentException("Sentence must end with ending mark!");
        }
    }

    public String findLongestSentence() {
        validateSentencesList();
        String longest = listOfSentences.get(0);
        for (String sentence : listOfSentences) {
            if (sentence.length() > longest.length()) {
                longest = sentence;
            }
        }
        return longest;
    }

    private void validateSentencesList() {
        if (listOfSentences.isEmpty()) {
            throw new IllegalStateException("Sentences list is empty!");
        }
    }

    public List<String> getListOfSentences() {
        return new ArrayList<>(listOfSentences);
    }
}
