
import java.util.Random;
import java.util.Scanner;

public class numbergame {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        Random random = new Random();

        int min= 1;
        int max = 100;
        int maxAttempts = 10;
        int score = 0;

        System.out.println("Number Guessing Game!");

        do {
            int generatedNumber = random.nextInt(max - min + 1) + min;
            int attempts = 0;
            boolean guessedCorrect= false;

            System.out.println("selected a number between " + min+ " and " + max + ". Guess it!");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess number: ");
                int userGuess = sc.nextInt();
                attempts++;

                if (userGuess == generatedNumber) {
                    guessedCorrect = true;
                    break;
                } else if (userGuess < generatedNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (guessedCorrect) {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                score++;
            } else {
                System.out.println("Sorry! You've reached the maximum number of attempts. The correct number was: " + generatedNumber);
            }

            System.out.print("Do you want to play again? (yes/no): ");
        } while (sc.next().equalsIgnoreCase("yes"));

        System.out.println("Game over. Your final score is: " + score);
        sc.close();
    }
}
