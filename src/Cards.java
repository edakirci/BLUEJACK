import java.util.Random;
import java.util.Arrays;

public class Cards {
    Random rd = new Random();

    private int[] cardNums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private String[] colors = {"blue", "green", "red", "yellow"};
    private String[] gameDeck = new String[colors.length * cardNums.length];
    private int num = 0;

    public Cards(int[] cd, String[] c, String[] gd, int n) {
        cd = cardNums;
        c = colors;
        gd = gameDeck;
        n = num;
    }

    public void setCardNums(int[] cd) {
        cd = cardNums;
    }

    public void setColors(String[] c) {
        c = colors;
    }

    public void setGameDeck(String[] gd) {
        gd = gameDeck;
    }

    public void setNum(int n) {
        n = num;
    }

    public int[] getCardNums() {
        return cardNums;
    }

    public String[] getColors() {
        return colors;
    }

    public String[] getGameDeck() {
        return gameDeck;
    }

    public int getNum() {
        return num;
    }

    public String[] deck() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < cardNums.length; j++) {
                gameDeck[num] = colors[i] + " " + "+" + cardNums[j];
                num++;
            }
        }
        return gameDeck;
    }

    public String[] shuffle(String[] arr) {
        arr = gameDeck;
        int index;
        String temp = null;
        for (int i = arr.length - 1; i > 0; i--) {
            index = rd.nextInt(i + 1);
            temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }
}
