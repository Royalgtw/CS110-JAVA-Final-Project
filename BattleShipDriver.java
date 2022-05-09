/**
 * Hoang Huynh
 * CS 110 Final Project
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class BattleShipDriver {
    public static void main(String[] args) throws FileNotFoundException {

        Game game = new Game();
        Random rand = new Random();
        boolean playerMove = rand.nextBoolean();//randomly deciding who goes first
        Scanner keyboard = new Scanner(System.in);

        //startup message
        System.out.println("Welcome to Battleship!\n");
        if (playerMove) {
            System.out.println("You won the coin toss and get to go first.");
        } else {
            System.out.println("The computer won the coin toss and gets to go first.");
            System.out.println("Computer's turn. Press Enter to continue.");
        }

        while (!(game.computerDefeated() || game.userDefeated())) {
            if (playerMove) {
                try {
                    System.out.println("Your Turn: ");
                    String moveStr = keyboard.nextLine().toUpperCase();
                    String message = game.makePlayerMove(moveStr);

                    //if the player move sunk one of the computer's ship
                    if (message != null) {
                        System.out.printf("The Computer says: %s", message);
                    }
                    playerMove = false;
                } catch (Exception e) {
                    System.out.println("Invalid move, please try again.");
                }
            } else {
                System.out.println("Computer's turn. Press Enter key to continue.");

                String[] arrayString = game.makeComputerMove();
                System.out.printf("Computer Chose : %s\n", arrayString[0]);

                playerMove = true;
            }
            System.out.println(game);
        }

        //game end message
        System.out.println("Game over!");
        if (game.userDefeated()) {
            System.out.println("The computer won!");
        } else {
            System.out.println("You won!");
        }
    }
}