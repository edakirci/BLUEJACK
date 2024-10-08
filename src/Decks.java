import java.util.Random;
import java.util.Arrays;

public class Decks {
    private String[] gameDeck = new String[40];
    private String[] humanDeck = new String[10];
    private String[] computerDeck = new String[10];
    private String[] signedCards = new String[10];
    private String[] colors = {"blue", "green", "red", "yellow"};
    private String[] signs = {"+", "-"};
    private String flipCard = "+/-";
    private String doubleCard = "x2";

    Random rd = new Random();

    public Decks(String[] hd, String[] cd, Cards gameCards, String[] sc, String[] color, String[] sn) {
        humanDeck = hd;
        computerDeck = cd;
        gameDeck = gameCards.getGameDeck();
        signedCards = sc;
        colors = color;
        signs = sn;
    }

    public void setHumanDeck(String[] hd) {
        hd = this.humanDeck;
    }

    public void setComputerDeck(String[] cd) {
        cd = this.computerDeck;
    }

    public void setGameCards(String[] gameCards) {
        gameCards = this.gameDeck;
    }

    public void setSignedCards(String[] sc) {
        sc = this.signedCards;
    }

    public void setSigns(String[] sn) {
        sn = this.signs;
    }

    public String[] getHumanDeck() {
        return humanDeck;
    }

    public String[] getComputerDeck() {
        return computerDeck;
    }

    public String[] getGameCards() {
        return gameDeck;
    }

    public String[] getSignedCards() {
        return signedCards;
    }

    public String[] getSigns() {
        return signs;
    }


    public String[] signedDeck(String[] signedCards) {
        for (int i = 0; i < 10; i++) {
            int index1 = rd.nextInt(colors.length);
            int index2 = rd.nextInt(signs.length);
            int number = rd.nextInt(6) + 1;
            signedCards[i] = colors[index1] + " " + signs[index2] + number;
        }
        return signedCards;
    }

    public String[] computerDeck(String[] computerDeck) {
        int index = 5;
        for (int i = 0; i < 5; i++) {
            computerDeck[i] = gameDeck[i];
        }
        for (int i = 0; i < 3; i++) {
            computerDeck[index] = signedCards[i];
            index++;
        }


        int temp = rd.nextInt(100) + 1;
        int j = 6;
        if (temp >= 1 && temp <= 80) {  //these if statement represents the 80 percent of chance to get two signed cards
            computerDeck[j + 2] = signedCards[j];
            computerDeck[j + 3] = signedCards[j + 1];

        } else if (temp >= 81 && temp <= 88) { //these else if statement represents the 8 percent of chance to get one signed card and one flip card
            computerDeck[j + 2] = signedCards[j];
            computerDeck[j + 3] = flipCard;

        } else if (temp >= 89 && temp <= 96) { //these else if statement represents the 8 percent of chance to get one signed card and one double card
            computerDeck[j + 2] = signedCards[j];
            computerDeck[j + 3] = doubleCard;

        } else if (temp >= 97 && temp <= 100) { //these else if statement represents the 4 percent of chance to get one flip card and one double card
            computerDeck[j + 2] = flipCard;
            computerDeck[j + 3] = doubleCard;
        }
        return computerDeck;
    }

    public String[] humanDeck(String[] humanDeck) {
        int index = 5;
        int n = gameDeck.length;
        for (int i = 0; i < 5; i++) {
            humanDeck[i] = gameDeck[n - 1];
            n--;
        }
        for (int i = 3; i < 6; i++) {
            humanDeck[index] = signedCards[i];
            index++;
        }


        int temp = rd.nextInt(100) + 1;
        int j = 6;
        if (temp >= 1 && temp <= 80) {  //these if statement represents the 80 percent of chance to get two signed cards
            humanDeck[j + 2] = signedCards[j + 2];
            humanDeck[j + 3] = signedCards[j + 3];

        } else if (temp >= 81 && temp <= 88) { //these else if statement represents the 8 percent of chance to get one signed card and one flip card
            humanDeck[j + 2] = signedCards[j + 2];
            humanDeck[j + 3] = flipCard;

        } else if (temp >= 89 && temp <= 96) { //these else if statement represents the 8 percent of chance to get one signed card and one double card
            humanDeck[j + 2] = signedCards[j + 2];
            computerDeck[j + 3] = doubleCard;

        } else if (temp >= 97 && temp <= 100) { //these else if statement represents the 4 percent of chance to get one flip card and one double card
            humanDeck[j + 2] = flipCard;
            humanDeck[j + 3] = doubleCard;
        }
        return humanDeck;
    }

    public String[] computerShuffle(String[] array) {
        array = computerDeck;
        int index;
        String temp = null;
        for (int i = array.length - 1; i > 0; i--) {
            index = rd.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public String[] shuffle(String[] array) {
        array = humanDeck;
        int index;
        String temp = null;
        for (int i = array.length - 1; i > 0; i--) {
            index = rd.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }
}

