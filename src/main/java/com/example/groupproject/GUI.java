package com.example.groupproject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static java.lang.Math.random;
import static javafx.application.Application.launch;

public class GUI extends Application {

    public static final String[] COLORS = {"Red", "Blue", "Green", "Yellow"};
    public static final int MAX_NUMBER = 9;
    public static final int INITIAL_HAND_SIZE = 7;

    private ArrayList<String> deck;
    private ArrayList<ImageView> cardImages;
    private Group root;
    private Button drawOneCard;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        root = new Group();
        Scene scene = new Scene(root, 1200, 800, Color.BLUEVIOLET);

        //button draw cards
        Button drawButton = new Button("Draw Cards");
        drawButton.setOnAction(event -> drawCards());

        drawOneCard = new Button("Draw One Card");
        drawOneCard.setOnAction(event -> drawOneCard());
        drawOneCard.setDisable(true);
        root.getChildren().add(drawOneCard);

        generateInitialCard();

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        hbox.getChildren().addAll(drawButton, drawOneCard);
        root.getChildren().add(hbox);


        stage.setScene(scene);
        stage.show();

    }
    public void createDeck() {
        deck = new ArrayList<>();
        cardImages = new ArrayList<>();

        //Deck
        for (String color : COLORS) {
            for (int number = 0; number <= MAX_NUMBER; number++) {
                String cardValue = color + " " + number;
                deck.add(cardValue);

                ImageView cardImage = new ImageView(getClass().getResource("/UNO CARDS/" + color.toLowerCase() + number + ".png").toExternalForm());
                cardImage.setFitWidth(100);
                cardImage.setFitHeight(150);
                cardImages.add(cardImage);
            }
        }
    }

    private void generateInitialCard() {

            Random r = new Random();
            String s = COLORS[r.nextInt(COLORS.length)];
            int index = r.nextInt(MAX_NUMBER);

        ImageView initialCard = new ImageView(getClass().getResource("/UNO CARDS/" + s.toLowerCase() + index + ".png").toExternalForm());
            initialCard.setFitWidth(100);
            initialCard.setFitHeight(150);
            initialCard.setY(200);
            initialCard.setX(400);


            root.getChildren().add(initialCard);

    }

    private void drawCards() {
        createDeck();
        Collections.shuffle(deck);

        for (int i = 0; i < INITIAL_HAND_SIZE ; i++) {
//            String cardValue = deck.get(i);
            Random r = new Random();
            int index = r.nextInt(39);
//            int number = random.nextInt(40);
//            int ale = (i/i * number -1);c
            if (i + index - INITIAL_HAND_SIZE < 0){
                ImageView cardImage = cardImages.get(i + index);
                cardImage.setX(i * 100);
                cardImage.setY(400);
                root.getChildren().add(cardImage);
            } else {
                ImageView cardImage = cardImages.get(i + index - INITIAL_HAND_SIZE);
                cardImage.setX(i * 100);
                cardImage.setY(400);
                root.getChildren().add(cardImage);
            }

        }


        drawOneCard.setDisable(false);

    }

    private void drawOneCard(){
        if(deck.isEmpty()) {
            drawOneCard.setDisable(true);
            return;
        }

        ArrayList<ImageView> hand = new ArrayList<>();
        for(int i = 0; i < root.getChildren().size();i++){
            if(root.getChildren().get(i) instanceof ImageView){
                hand.add((ImageView) root.getChildren().get(i));
            }
        }


        String cardValue = deck.get(0);
        Random random = new Random();
        int rd = 39;
        int int_random = random.nextInt(rd);
        ImageView cardImage = cardImages.get(int_random);
        cardImage.setX(hand.size() * 100);
        cardImage.setY(400);
        root.getChildren().add(cardImage);
        deck.remove(0);

    }

    private boolean checkValidCard(String cardValue) {
        // Get the color and number of the card to be drawn
        String[] cardInfo = cardValue.split(" ");
        String cardColor = cardInfo[0];
        int cardNumber = Integer.parseInt(cardInfo[1]);

        // Get the color and number of the top card on the stack
        String[] topCardInfo = deck.get(0).split(" ");
        String topCardColor = topCardInfo[0];
        int topCardNumber = Integer.parseInt(topCardInfo[1]);

        // Check if the drawn card matches the color or number of the top card on the stack
        return cardColor.equals(topCardColor) || cardNumber == topCardNumber;
    }

}