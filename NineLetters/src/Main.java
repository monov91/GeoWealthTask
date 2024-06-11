import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        Set<String> allEnglishWords = loadAllWordsSet();

        Set<String> viableSet = loadAllWordsSet().stream()
                .filter(s -> s.length() == 9)
                .filter(s -> s.contains("A") || s.contains("I"))
                .collect(Collectors.toSet());

        long start = System.currentTimeMillis();

        Set<String> twoLetterWords = allEnglishWords.stream().filter(s -> s.length() == 2).collect(Collectors.toSet());
        Set<String> threeLetterWords = allEnglishWords.stream().filter(s -> s.length() == 3).collect(Collectors.toSet());
        Set<String> fourLetterWords = allEnglishWords.stream().filter(s -> s.length() == 4).collect(Collectors.toSet());
        Set<String> fiveLetterWords = allEnglishWords.stream().filter(s -> s.length() == 5).collect(Collectors.toSet());
        Set<String> sixLetterWords = allEnglishWords.stream().filter(s -> s.length() == 6).collect(Collectors.toSet());
        Set<String> sevenLetterWords = allEnglishWords.stream().filter(s -> s.length() == 7).collect(Collectors.toSet());
        Set<String> eightLetterWords = allEnglishWords.stream().filter(s -> s.length() == 8).collect(Collectors.toSet());

        Set<String> answers = new HashSet<>();

        for (String word : viableSet) {
            for (int i = 0; i < word.length() - 1; i++) {
                String randomEightLetterSubstring = getSubstringWithoutLetterAtIndex(word, i);
                if (eightLetterWords.contains(randomEightLetterSubstring)) {
                    for (int j = 0; j < randomEightLetterSubstring.length(); j++) {
                        String randomSevenLetterSubstring = getSubstringWithoutLetterAtIndex(randomEightLetterSubstring, j);
                        if (sevenLetterWords.contains(randomSevenLetterSubstring)) {
                            for (int k = 0; k < randomSevenLetterSubstring.length(); k++) {
                                String randomSixLetterSubstring = getSubstringWithoutLetterAtIndex(randomSevenLetterSubstring, k);
                                if (sixLetterWords.contains(randomSixLetterSubstring)) {
                                    for (int l = 0; l < randomSixLetterSubstring.length(); l++) {
                                        String randomFiveLetterSubstring = getSubstringWithoutLetterAtIndex(randomSixLetterSubstring, l);
                                        if (fiveLetterWords.contains(randomFiveLetterSubstring)) {
                                            for (int m = 0; m < randomFiveLetterSubstring.length(); m++) {
                                                String randomFourLetterSubstring = getSubstringWithoutLetterAtIndex(randomFiveLetterSubstring, m);
                                                if (fourLetterWords.contains(randomFourLetterSubstring)) {
                                                    for (int n = 0; n < randomFourLetterSubstring.length(); n++) {
                                                        String randomThreeLetterSubstring = getSubstringWithoutLetterAtIndex(randomFourLetterSubstring, n);
                                                        if (threeLetterWords.contains(randomThreeLetterSubstring)) {
                                                            for (int o = 0; o < randomThreeLetterSubstring.length(); o++) {
                                                                String randomTwoLetterSubstring = getSubstringWithoutLetterAtIndex(randomThreeLetterSubstring, o);
                                                                if (twoLetterWords.contains(randomTwoLetterSubstring)) {
                                                                    if (randomTwoLetterSubstring.contains("I") || randomTwoLetterSubstring.contains("A")) {
                                                                        answers.add(word);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.printf("Time elapsed: %d milliseconds\n", timeElapsed);

        System.out.printf("Viable 9 letter words quantity: %d\n", answers.size());
        System.out.printf("Viable 9 letter words: %s\n", answers);
    }

    private static Set<String> loadAllWordsSet() throws IOException {

        URL wordsURL = new URL("https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt");
        try (BufferedReader br = new BufferedReader(new
                InputStreamReader(wordsURL.openConnection().getInputStream()))) {
            return br.lines().skip(2).collect(Collectors.toSet());

        }
    }

    private static String getSubstringWithoutLetterAtIndex(String word, int index) {

        return word.substring(0, index) + word.substring(index + 1);
    }
}