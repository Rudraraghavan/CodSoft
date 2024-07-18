import java.util.Random;
import java.util.Scanner;

public class NUMBER_Game {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int score = 0;
        int round = 0;

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1;
            int numberOfAttempts = 0;
            boolean guessedCorrectly = false;
            int maxAttempts = 10; 

            System.out.println("Round " + (round + 1) + ": Guess the number between 1 and 100.");

            while (!guessedCorrectly && numberOfAttempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                numberOfAttempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the number in " + numberOfAttempts + " attempts.");
                    guessedCorrectly = true;
                    score++;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("You've used all attempts. The correct number was " + numberToGuess + ".");
            }

            round++;
            System.out.print("Do you want to play another round? (yes/no): ");
            String userResponse = scanner.next();

            playAgain = userResponse.equalsIgnoreCase("yes");
        }

        System.out.println("Game Over. You played " + round + " rounds with a score of " + score + ".");
        scanner.close();
    }
}
