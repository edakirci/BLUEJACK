import java.util.Random;
import java.util.Arrays;

public class Decks {
    private String[] humanDeck = new String[10];
    private String[] computerDeck = new String[10];
    private String[] gameDeck = new String[40];
    private String[] signedCards = new String[10];
    private String[] colors = {"blue", "green", "red", "yellow"};
    private String[] signs = {"+", "-"};
    private int[] percent = {1, 1, 1, 1, 2};
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

    public void setHumanDeck(String[] hd) {hd = this.humanDeck;}
    public void setComputerDeck(String[] cd) {cd = this.computerDeck;}
    public void setGameCards(String[] gameCards) {gameCards = this.gameDeck;}
    public void setSignedCards(String[] sc) {sc = this.signedCards;}
    public void setSigns(String[] sn) {sn = this.signs;}

    public String[] getHumanDeck() {return humanDeck;}
    public String[] getComputerDeck() {return computerDeck;}
    public String[] getGameCards() {return gameDeck;}
    public String[] getSignedCards() {return signedCards;}
    public String[] getSigns() {return signs;}


    public String[] signedDeck(String[] signedCards) {
        for (int i = 0; i < 10; i++) {
            int index1 = rd.nextInt(colors.length);
            int index2 = rd.nextInt(signs.length);
            signedCards[i] = colors[index1] + " " + rd.nextInt(6) + 1 + " " + signs[index2];
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
        for (int i = 0; i < 2; i++) {
            int temp = rd.nextInt(percent.length);
            int j = 6;

            if (percent[temp] == 1) {
                computerDeck[j + 2] = signedCards[j];
                j++;
            } else {
                computerDeck[j + 2] = doubleCard;
                computerDeck[j + 3] = flipCard;
            }
        }
        System.out.println(Arrays.toString(computerDeck));
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
        for (int i = 0; i < 2; i++) {
            int temp = rd.nextInt(percent.length);
            int j = 8;

            if (percent[temp] == 1) {
                humanDeck[j] = signedCards[j];
                j++;
            } else {
                humanDeck[j] = flipCard;
                humanDeck[j + 1] = doubleCard;
            }
        }
        System.out.println(Arrays.toString(humanDeck));
        return humanDeck;
    }
}
