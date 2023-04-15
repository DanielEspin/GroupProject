package com.example.groupproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class UnoGame {
    public static final String[] COLORS = {"Red", "Blue", "Green", "Yellow"};
    public static final int MAX_NUMBER = 9;
    public static final int INITIAL_HAND_SIZE = 7;

    public ArrayList<String> deck;
    public ArrayList<String> playerHand;
    public String currentCard;


    public UnoGame() {
        deck = new ArrayList<>();
        playerHand = new ArrayList<>();

        // Generate deck
        for (String color : COLORS) {
            for (int number = 1; number <= MAX_NUMBER; number++) {
                deck.add(color + " " + number);
            }
        }
        Collections.shuffle(deck, new Random());

        // Deal initial hand to player
        for (int i = 0; i < INITIAL_HAND_SIZE; i++) {
            drawCard();
        }

        // Place first card in the middle
        currentCard = deck.remove(0);
        System.out.println("Current card: " + currentCard);
    }

//    public void playGame() {
//        Scanner scanner = new Scanner(System.in);
//        boolean gameover = false;
//
//        while (!gameover) {
//            System.out.println("Your hand: " + playerHand);
//            System.out.print("Enter the card you want to play (or 'draw' to draw a card, 'quit' to quit): ");
//            String input = scanner.nextLine();
//
//            if (input.equalsIgnoreCase("draw")) {
//                drawCard();
//            } else if (input.equalsIgnoreCase("quit")) {
//                System.out.println("Thank you for playing!");
//                gameover = true;
//            } else {
//                if (isValidMove(input)) {
//                    playCard(input);
//                    if (playerHand.isEmpty()) {
//                        System.out.println("You win!");
//                        gameover = true;
//                    }
//                } else {
//                    System.out.println("Invalid move. Try again.");
//                }
//            }
//        }
//    }

    private void drawCard() {
        if (deck.isEmpty()) {
            System.out.println("No more cards in the deck!");
            return;
        }

        String drawnCard = deck.remove(0);
        playerHand.add(drawnCard);
        System.out.println("You drew: " + drawnCard);
    }

    private boolean isValidMove(String card) {
        String[] cardComponents = card.split(" ");
        String cardColor = cardComponents[0];
        String cardNumber = cardComponents[1];

        String currentCardColor = currentCard.split(" ")[0];
        String currentCardNumber = currentCard.split(" ")[1];

        return cardColor.equalsIgnoreCase(currentCardColor) || cardNumber.equalsIgnoreCase(currentCardNumber);
    }

    private void playCard(String card) {
        playerHand.remove(card);
        currentCard = card;
        System.out.println("You played: " + card);
    }

//    public static void main(String[] args) {
//        UnoGame unoGame = new UnoGame();
//        unoGame.playGame();
//    }
}
