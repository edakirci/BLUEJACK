import java.util.*;
import java.io.*;
import java.nio.file.Paths;

public class BluejackDemo {
    public static void main(String[] args) {
        int[] cardNums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] colors = {"blue", "green", "red", "yellow"};
        String[] gamedeck = new String[10];
        String[] humandeck = new String[10];
        String[] computerdeck = new String[10];
        String[] signedcards = new String[10];
        String[] signs = {"+", "-"};
        int num = 0;
        int indexToPick = 0, consIndex = 0;


        Cards bjcards = new Cards(cardNums, colors, gamedeck, num);
        Decks bjdecks = new Decks(humandeck, computerdeck, bjcards, signedcards, colors, signs);
        Random rd = new Random();
        Scanner sc = new Scanner(System.in);

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

        int bjScorePlayer = 0;
        int bjScoreComputer = 0;
        int normalScorePlayer = 0;
        int normalScoreComputer = 0;

        int playerSum = 0;
        int computerSum = 0;

        for (int i = 0; i < 4; i++) {
            computerHand[i] = computerdeck[i];
        }

        for (int i = 0; i < 4; i++) {
            playerHand[i] = humandeck[i];
        }

        while (bjScorePlayer != 1 && bjScoreComputer != 1 && normalScorePlayer != 3 && normalScoreComputer != 3 && playerSum <= 20 && computerSum <= 20) {

            System.out.println("computer hand: " + "X X X X");
            System.out.println("computer board: " + Arrays.toString(computerBoard));
            System.out.println("player board: " + Arrays.toString(playerBoard));
            System.out.println("player hand: " + Arrays.toString(playerHand));

            System.out.println("Player's turn!");
            drawToBoard(playerBoard, gamedeck, 0, consIndex);
            consIndex++;

            System.out.println("computer hand: " + "X X X X");
            System.out.println("computer board: " + Arrays.toString(computerBoard));
            System.out.println("player board: " + Arrays.toString(playerBoard));
            System.out.println("player hand: " + Arrays.toString(playerHand));

            System.out.println("What do you want to do: 1. Stand or 2.Play a card from your hand or 3.End your turn");
            int choice = sc.nextInt();
            while (choice != 1 && choice != 2 && choice != 3) {
                System.out.println("Your choice is invalid. Please try again");
                choice = sc.nextInt();
            }
            switch (choice) {
                case 1:
                    System.out.println("Player chose to stand.");
                    // TODO:computer must play
                    playerSum = calculateBoardSum(playerBoard);
                    computerSum = calculateBoardSum(computerBoard);
                    //TODO: compare the sums
                    System.out.println("Wait for the computer's move");
                    break;
                case 2:
                    System.out.println("Player chose to play a card from their hand.");
                    System.out.println("Your hand: " + Arrays.toString(playerHand));
                    System.out.println("Enter the index of the card you want to play: ");
                    int cardIndex = sc.nextInt();
                    while (cardIndex < 0 || cardIndex >= playerHand.length || playerHand[cardIndex] == null) {
                        System.out.println("Invalid card index. Please try again: ");
                        cardIndex = sc.nextInt();
                    }
                    drawToBoard(playerBoard, playerHand, indexToPick, 0);
                    System.out.println("Player played: " + playerHand[cardIndex - 1]);
                    break;
                case 3:
                    System.out.println("Player chose to end their turn.");
                    break;
            }

            System.out.println("It's computer's turn!");
            while (calculateBoardSum(computerBoard) < 20) {
                String suitableCard = findSuitableCard(computerHand, calculateBoardSum(computerBoard), computerBoard);
                if (suitableCard != null) {
                    drawToBoard(computerBoard, computerHand, indexToPick, 0);
                    System.out.println("Computer played: " + suitableCard + " and stand");
                } else {
                    System.out.println("Computer chose to end its turn.");
                    break;
                }
            }
            if (playerSum > 20) {
                System.out.println("Bust! You lost the set");
                break;
            } else if (computerSum > 20) {
                System.out.println("Bust! The CPU lost the set");
                break;
            }
        }


        winner(playerSum, computerSum, bjScoreComputer, bjScorePlayer);

        GameHistory[] gameHistory = new GameHistory[MAX_HISTORY_SIZE];
        int historyIndex = 0;
        winner(playerSum, computerSum, bjScoreComputer, bjScorePlayer);
        GameHistory currentGame = new GameHistory("Player", "Computer", (playerSum > computerSum) ? "Player" : "Computer");
        gameHistory[historyIndex] = currentGame;
        historyIndex = (historyIndex + 1) % MAX_HISTORY_SIZE;
    }

    public static void drawToBoard(String[] board, String[] deck, int indexToPick, int consIndex) {
        if (indexToPick == 0) {
            if (consIndex >= deck.length) {
                System.out.println("Invalid index.");
                System.exit(consIndex);
            }
            String drawnCard = "";
            if (board.length < 9 && deck.length > 0) {
                drawnCard = deck[consIndex];

                for (int i = 0; i < board.length; i++) {
                    if (board[i] == null) {
                        board[i] = drawnCard;
                        break;
                    }
                }
                deck[consIndex] = null;
            }
        }
        if (consIndex == 0) {
            if (indexToPick > deck.length) {
                System.out.println("Invalid index.");
                System.exit(indexToPick);
            }
            String drawnCard = "";
            if (board.length < 9 && deck.length > 0) {
                drawnCard = deck[indexToPick - 1];

                for (int i = 0; i < board.length; i++) {
                    if (board[i] == null) {
                        board[i] = drawnCard;
                        break;
                    }
                }
                deck[indexToPick - 1] = null;
            }
        }
    }

    public static int calculateBoardSum(String[] board) {
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

    public static String findSuitableCard(String[] hand, int targetSum, String[] board) {
        int maxDifference = Integer.MAX_VALUE;
        String bestCard = null;

        for (String card : hand) {
            if (card != null) {
                String[] parts = card.split(" ");
                int cardValue = Integer.parseInt(parts[parts.length - 1]);
                int currentSum = calculateBoardSum(new String[]{card}) + calculateBoardSum(board);
                int difference = targetSum - currentSum;


                if (difference >= 0 && difference < maxDifference) {
                    maxDifference = difference;
                    bestCard = card;
                }
            }
        }
        return bestCard;
    }

    private static void winner(int playerSum, int computerSum, int bjScoreComputer, int bjScorePlayer) {
        if (playerSum > 20) {
            System.out.println("Player busts! Computer wins!");
            bjScoreComputer = 1;
        } else if (computerSum > 20) {
            System.out.println("Computer busts! Player wins!");
            bjScorePlayer = 1;
        } else {
            if ((20 - playerSum) < (20 - computerSum)) {
                System.out.println("Player wins!");
                bjScorePlayer = 1;
            } else if ((20 - playerSum) > (20 - computerSum)) {
                System.out.println("Computer wins!");
                bjScoreComputer = 1;
            } else {
                System.out.println("It's a tie!");
            }
        }
    }

    private static void saveGameHistory(GameHistory[] gameHistory) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("game_history.txt"))) {
            for (int i = 0; i < MAX_HISTORY_SIZE; i++) {
                if (gameHistory[i] != null) {
                    writer.write(gameHistory[i].toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int MAX_HISTORY_SIZE = 10;
}

