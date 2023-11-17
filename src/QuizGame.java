import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    String question;
    List<String> options;
    int correctOption;

    public QuizQuestion(String question, List<String> options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }
}

public class QuizGame {
    private List<QuizQuestion> questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;
    private boolean answerSubmitted;

    public QuizGame(List<QuizQuestion> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.timer = new Timer();
        this.answerSubmitted = false;
    }

    public void startGame() {
        displayNextQuestion();
    }

    private void displayNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
            System.out.println(currentQuestion.question);

            for (int i = 0; i < currentQuestion.options.size(); i++) {
                System.out.println((i + 1) + ". " + currentQuestion.options.get(i));
            }

            startTimer();
            getUserAnswer();
        } else {
            endGame();
        }
    }

    private void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Moving to the next question.");
                checkAnswer(-1);
            }
        }, 15000); // 15 seconds timer
    }

    private void getUserAnswer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Your answer (1-" + questions.get(currentQuestionIndex).options.size() + "): ");
        int userAnswer = scanner.nextInt();
        checkAnswer(userAnswer);
    }

    private void checkAnswer(int userAnswer) {
        if (!answerSubmitted) {
            timer.cancel(); // Cancel the timer as the user has submitted an answer
            answerSubmitted = true;

            QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
            if (userAnswer == currentQuestion.correctOption) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect! The correct answer is: " + currentQuestion.correctOption + "\n");
            }

            currentQuestionIndex++;
            answerSubmitted = false;
            displayNextQuestion();
        }
    }

    private void endGame() {
        System.out.println("Game over! Your final score is: " + score + "/" + questions.size());
        System.out.println("Summary: Correct - " + score + " | Incorrect - " + (questions.size() - score));
    }

    public static void main(String[] args) {
        // Define your quiz questions
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        quizQuestions.add(new QuizQuestion("What is the capital of France?", List.of("Paris", "Berlin", "London"), 1));
        quizQuestions.add(new QuizQuestion("Which planet is known as the Red Planet?", List.of("Mars", "Jupiter", "Venus"), 1));
        quizQuestions.add(new QuizQuestion("What is the largest mammal?", List.of("Elephant", "Blue Whale", "Giraffe"), 2));

        // Create and start the quiz game
        QuizGame quizGame = new QuizGame(quizQuestions);
        quizGame.startGame();
    }
}
