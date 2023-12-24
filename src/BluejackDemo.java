import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class BluejackDemo {
    public static void main(String[] args) {
        int[] cardNums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] colors = {"blue", "green", "red", "yellow"};
        String[] gamedeck = new String[40];
        String[] humandeck = new String[10];
        String[] computerdeck = new String[10];
        String[] signedcards = new String[10];
        String[] signs = {"+", "-"};
        int num = 0;
        int gameDeckIndex = 0;


        Cards bjcards = new Cards(cardNums, colors, gamedeck, num);
        Decks bjdecks = new Decks(humandeck, computerdeck, bjcards, signedcards, colors, signs);
        Random rd = new Random();
        Scanner sc = new Scanner(System.in);
        Score scores=new Score(0, 0, 0, 0);

        bjcards.deck();
        bjcards.shuffle(gamedeck);
        bjdecks.signedDeck(signedcards);
        bjdecks.humanDeck(humandeck);
        bjdecks.computerDeck(computerdeck);

        //this is the point that my game starts
        String[] computerHand = new String[4];
        String[] computerBoard = new String[9];
        String[] playerHand = new String[4];
        String[] playerBoard = new String[9];


        int playerSum = 0;
        int computerSum = 0;


        for (int i = 0; i < 4; i++) {
            computerHand[i] = computerdeck[i];
        }

        for (int i = 0; i < 4; i++) {
            playerHand[i] = humandeck[i];
        }

        while (scores.getNormalScorePlayer()!= 3 && scores.getNormalScoreComputer()!= 3){

            for(int i=0;i<playerBoard.length;i++){
                playerBoard[i]=null;
            }
            for(int i=0;i<computerBoard.length;i++){
                computerBoard[i]=null;
            }


            System.out.println("computer hand: " + "X X X X");
            System.out.println("computer board: " + Arrays.toString(computerBoard));
            System.out.println("player board: " + Arrays.toString(playerBoard));
            System.out.println("player hand: " + Arrays.toString(playerHand));

            System.out.println("Player's turn! You picked a card from game deck.");
            System.out.println("You drew:" + bjcards.getGameDeck()[gameDeckIndex]);
            deckToBoard(bjcards.getGameDeck(), playerBoard, gameDeckIndex);
            gameDeckIndex++;


            System.out.println("computer hand: " + "X X X X");
            System.out.println("computer board: " + Arrays.toString(computerBoard));
            System.out.println("player board: " + Arrays.toString(playerBoard));
            System.out.println("player hand: " + Arrays.toString(playerHand));

            System.out.println("What do you want to do: 1. Stand or 2.Play a card from your hand or 3.End your turn");
            int choice = getIntInput(sc);
            while (choice != 1 && choice != 2 && choice != 3) {
                System.out.println("Your choice is invalid. Please try again");
                choice = getIntInput(sc);
            }
            switch (choice) {
                case 1:
                    System.out.println("Player chose to stand.");
                    System.out.println("Wait for the computer's move");
                    break;
                case 2:
                    System.out.println("Player chose to play a card from their hand.");
                    System.out.println("Your hand: " + Arrays.toString(playerHand));
                    System.out.println("Enter the index of the card you want to play: ");
                    int cardIndex = getIntInput(sc);
                    System.out.println("Player played: " + playerHand[cardIndex - 1]);
                    handToBoard(playerHand, playerBoard, cardIndex-1);
                    break;
                case 3:
                    System.out.println("Player chose to end their turn.");
                    break;
            }

            System.out.println("It's computer's turn!");
            System.out.println("Computer drew: " + bjcards.getGameDeck()[gameDeckIndex]);
            deckToBoard(bjcards.getGameDeck(), computerBoard, gameDeckIndex);
            gameDeckIndex++;
            String suitableCard = findSuitableCard(computerHand, 20 - calculateBoardSum(computerBoard, scores), computerBoard,scores);

            if (calculateBoardSum(computerBoard,scores) < 20) {
                int suitableCardIndex = 0;
                if (suitableCard != null && !suitableCard.isEmpty()) {
                    for (String eachCard : computerHand) {
                        if (suitableCard.equals(eachCard)) {
                            break;
                        }
                        suitableCardIndex++;
                    }
                }
                handToBoard(computerHand, computerBoard, suitableCardIndex);
                System.out.println("Computer played: " + suitableCard + " and stand");
            }
            if (suitableCard == null && calculateBoardSum(computerBoard,scores) > 20) {
                System.out.println("Computer busts!");
            }
            if (suitableCard == null && calculateBoardSum(computerBoard,scores) <= 20) {
                System.out.println("Computer picked a card from game deck and chose to end its turn.");
            }

            System.out.println("computer hand: " + "X X X X");
            System.out.println("computer board: " + Arrays.toString(computerBoard));
            System.out.println("player board: " + Arrays.toString(playerBoard));
            System.out.println("player hand: " + Arrays.toString(playerHand));

            winner(playerSum, computerSum, playerBoard, computerBoard,scores);


        }
        String winner = (playerSum > computerSum) ? "Player" : "Computer";
        GameHistory currentGame = new GameHistory("Player", "Computer", winner);
        gameHistory[historyIndex] = currentGame;
        historyIndex = (historyIndex + 1) % MAX_HISTORY_SIZE;

        saveGameHistory();
    }

    public static void handToBoard(String[] userHand, String[] userBoard, int handIndex) {
        if (handIndex > userHand.length) {
            System.out.println("Invalid index.");
            System.exit(handIndex);
        }
        String drawnCard = "";
        drawnCard = userHand[handIndex];

        for (int i = 0; i < userBoard.length; i++) {
            if (userBoard[i] == null) {
                userBoard[i] = drawnCard;
                break;
            }
        }
        userHand[handIndex] = null;
    }

    public static void deckToBoard(String[] gameDeck, String[] userBoard, int gameDeckIndex) {
        if (gameDeckIndex >= gameDeck.length) {
            System.out.println("Invalid index.");
            System.exit(gameDeckIndex);
        }

        String drawnDeckCard = "";
        drawnDeckCard = gameDeck[gameDeckIndex];

        for (int i = 0; i < userBoard.length; i++) {
            if (userBoard[i] == null) {
                userBoard[i] = drawnDeckCard;
                break;
            }
        }
        gameDeck[gameDeckIndex] = null;
    }

    public static int calculateBoardSum(String[] board,Score scores) {
        Scanner sc=new Scanner(System.in);
        int sum = 0;
        for (String card : board) {
            if (card != null) {
                String[] parts = card.split(" ");
                int numericValue = Integer.parseInt(parts[parts.length - 1]);
                sum += numericValue;
            }
        }
        return sum;
    }

    public static String findSuitableCard(String[] hand, int targetSum, String[] board,Score scores) {
        String bestCard = "";
        int currentSum = calculateBoardSum(board,scores);

        for (String card : hand) {
            if (card != null) {
                int cardValue = Integer.parseInt(card.split(" ")[card.split(" ").length - 1]);
                int newSum = currentSum + cardValue;

                if (newSum <= targetSum && (targetSum - newSum) < (targetSum - currentSum)) {
                    bestCard = card;
                    currentSum = newSum;
                }
            }
        }
        return bestCard;
    }

    private static void winner(int playerSum, int computerSum, String[] playerBoard, String[] computerBoard, Score scores) {

        playerSum = calculateBoardSum(playerBoard,scores);
        computerSum = calculateBoardSum(computerBoard,scores);

        if (playerSum > 20) {
            System.out.println("Player busts! Computer wins this set!");
            scores.setNormalScoreComputer(scores.getNormalScoreComputer()+1);
        } else if (computerSum > 20) {
            System.out.println("Computer busts! Player wins this set!");
            scores.setNormalScorePlayer(scores.getNormalScorePlayer()+1);
        } else {
            if ((20 - playerSum) < (20 - computerSum)) {
                System.out.println("Player wins this set!");
                scores.setBjScorePlayer(scores.getNormalScorePlayer()+1);
            } else if ((20 - playerSum) > (20 - computerSum)) {
                System.out.println("Computer wins!");
                scores.setNormalScoreComputer(scores.getNormalScoreComputer()+1);
            } else {
                System.out.println("This set is a tie!");
            }
        }
        System.out.println("************************************************************");
        System.out.println("Computer Score:"+scores.getNormalScoreComputer()+
                " Player Score:"+scores.getNormalScorePlayer());
        System.out.println("************************************************************");




    }


    public static int getIntInput(Scanner sc) {
        while (true) {
            if (sc.hasNextInt()) {
                int input = sc.nextInt();
                sc.nextLine();
                return input;
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.nextLine();
            }
        }
    }

    private static int MAX_HISTORY_SIZE = 10;
    private static GameHistory[] gameHistory = new GameHistory[MAX_HISTORY_SIZE];
    private static int historyIndex = 0;

    private static void saveGameHistory() {
        System.out.println("Game History:");
        for (int i = 0; i < MAX_HISTORY_SIZE; i++) {
            if (gameHistory[i] != null) {
                System.out.println(gameHistory[i].toString());
            }
        }
    }
    }

