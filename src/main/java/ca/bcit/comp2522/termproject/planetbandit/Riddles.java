package ca.bcit.comp2522.termproject.planetbandit;

import ca.bcit.comp2522.termproject.planetbandit.Entities.Player;
import com.almasb.fxgl.entity.Entity;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a riddle object.
 *
 * @author Prab and Benny
 * @version 2022
 */
public class Riddles {
    private static final int TOTAL_NUMBER_OF_RIDDLES = 32;
    private final Entity player;
    private int riddleIndex;
    private final HashMap<String, String> riddles;
    private final Random random;
    private final Scanner scanner;  // Create a Scanner object


    /**
     * Constructs a riddle object.
     *
     * @param player a Player object
     */
    public Riddles(final Entity player) {
        this.player = player;
        //this.totalNumberOfRiddles = 32;
        riddles = new HashMap<>();
        random = new Random();
        scanner = new Scanner(System.in);
        riddles.put("What has to be broken before you can use it?", "An egg");
        riddles.put("I’m tall when I’m young, and I’m short when I’m old. What am I?", "A candle");
        riddles.put("What month of the year has 28 days?", "All of them");
        riddles.put("What is always in front of you but can’t be seen?", "The future");
        riddles.put("What goes up but never comes down?", "Your age");
        riddles.put("What gets wet while drying?", "A towel");
        riddles.put("I shave every day, but my beard stays the same. What am I?", "A barber");
        riddles.put("What can’t talk but will reply when spoken to?", "An echo");
        riddles.put("The more of this there is, the less you see. What is it?", "Darkness");
        riddles.put("David’s parents have three sons: Snap, Crackle, and what’s the name of the third son?", "David");
        riddles.put("I follow you all the time and copy your every move, but you can’t touch me or catch me. "
                + "What am I?", "Your shadow");
        riddles.put("What gets bigger when more is taken away?", "A hole");
        riddles.put("I’m found in socks, scarves and mittens; and often in the paws of playful kittens."
                + "What am I?", "Yarn");
        riddles.put("Where does today come before yesterday?", "The dictionary");
        riddles.put("What invention lets you look right through a wall?", "A window");
        riddles.put("If you’ve got me, you want to share me; if you share me, you haven’t"
                + "kept me. What am I?", "A secret");
        riddles.put("If you’re running in a race and you pass the person in second place, what"
                + "place are you in?", "Second");
        riddles.put("It belongs to you, but other people use it more than you do. What is it?", "Your name");
        riddles.put("What has hands, but can’t clap?", "A clock");
        riddles.put("What can you catch, but not throw?", "A cold");
        riddles.put("What has a head and a tail but no body?", "A coin");
        riddles.put("What building has the most stories?", "The library");
        riddles.put("What has 13 hearts, but no other organs?", "A deck of cards");
        riddles.put("I am an odd number. Take away a letter and I become even. What number am I?", "Seven");
        riddles.put("What three numbers, none of which is zero, give the same result whether they’re"
                + "added or multiplied? (Spell out the numbers)", "One, two and three");
        riddles.put("What five-letter word becomes shorter when you add two letters to it?", "Short");
        riddles.put("You see me once in June, twice in November and not at all in May. What am I?", "The letter 'e'");
        riddles.put("What is so fragile that saying its name breaks it?", "Silence");
        riddles.put("What can fill a room but takes up no space?", "Light");
        riddles.put("If you drop me I’m sure to crack, but give me a smile and I’ll always smile back."
                + "What am I?", "A mirror");
        riddles.put("The more you take, the more you leave behind. What are they?", "Footsteps");
        riddles.put("People make me, save me, change me, raise me. What am I?", "Money");
    }

    public String getRiddle() {

        riddleIndex = random.nextInt(0, TOTAL_NUMBER_OF_RIDDLES);
        Object riddle = riddles.keySet().toArray()[riddleIndex];
        return (String) riddle;
    }

    public String getAnswer() {
        Object firstKey = riddles.keySet().toArray()[riddleIndex];
        return riddles.get(firstKey);
    }

    private boolean getUserGuess() {
        System.out.println("Please enter your answer: ");
        String userAnswer = scanner.nextLine();

        userAnswer = userAnswer.stripLeading();
        userAnswer = userAnswer.stripTrailing();
        if (userAnswer.equalsIgnoreCase(getAnswer())) {
            scanner.close();
            return true;
        }
        scanner.close();
        return false;
    }

    /**
     * Constructs the riddle guessing sequence.
     */
    public void generateRiddle() {
        System.out.println(getRiddle());
        boolean guessedCorrectly = getUserGuess();

        if (guessedCorrectly) {
            System.out.println("Correct!");
        } else {
            //player.setHealth(player.getHealth() - 1);
            System.out.println("Incorrect the answer was '" + getAnswer() + "'");
        }
    }
}
