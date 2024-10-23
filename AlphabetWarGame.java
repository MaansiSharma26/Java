import java.util.HashMap;
import java.util.Scanner;

public class AlphabetWarGame {

    private HashMap<Character, Integer> leftStrengths;
    private HashMap<Character, Integer> rightStrengths;

    // Default constructor with predefined strengths
    public AlphabetWarGame() {
        leftStrengths = new HashMap<>();
        rightStrengths = new HashMap<>();
        setDefaultStrengths();
    }

    // Constructor to customize strengths
    public AlphabetWarGame(HashMap<Character, Integer> left, HashMap<Character, Integer> right) {
        this.leftStrengths = left;
        this.rightStrengths = right;
    }

    // Set default strengths
    private void setDefaultStrengths() {
        leftStrengths.put('w', 4);
        leftStrengths.put('p', 3);
        leftStrengths.put('b', 2);
        leftStrengths.put('s', 1);
        rightStrengths.put('m', 4);
        rightStrengths.put('q', 3);
        rightStrengths.put('d', 2);
        rightStrengths.put('z', 1);
    }

    // Method to determine winner based on a single word
    public String AlphabetWar(String word) {
        return AlphabetWar(word, word);
    }

    // Method to determine winner based on separate left and right words
    public String AlphabetWar(String leftWord, String rightWord) {
        int leftScore = calculateScore(leftWord, leftStrengths);
        int rightScore = calculateScore(rightWord, rightStrengths);

        if (leftScore > rightScore) {
            return "Left side wins!";
        } else if (rightScore > leftScore) {
            return "Right side wins!";
        } else {
            return "Let's fight again!";
        }
    }

    // Calculate the total strength score for the given word
    private int calculateScore(String word, HashMap<Character, Integer> strengths) {
        int score = 0;
        for (char c : word.toLowerCase().toCharArray()) { // Ensure comparison is case insensitive
            score += strengths.getOrDefault(c, 0); // Default to 0 if character not found
        }
        return score;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlphabetWarGame game = new AlphabetWarGame();

        // Test cases
        System.out.println(game.AlphabetWar("z")); // Expected: Right side wins!
        System.out.println(game.AlphabetWar("zdqmwpbs")); // Expected: Let's fight again!
        System.out.println(game.AlphabetWar("wwwwwwz")); // Expected: Left side wins!

        // Infinite loop for continuous input until the user decides to exit
        while (true) {
            System.out.print("Enter the left word (or type 'exit' to quit): ");
            String leftWord = scanner.nextLine();
            if (leftWord.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Enter the right word: ");
            String rightWord = scanner.nextLine();

            // Get the result of the battle
            String result = game.AlphabetWar(leftWord, rightWord);
            System.out.println(result);
        }

        scanner.close();
    }
}