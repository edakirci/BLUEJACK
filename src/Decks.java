import java.util.Random;
import java.util.Arrays;

public class Decks {
   private String[] humanDeck=null;
   private String[] computerDeck=null;
   private String[] gameDeck=null;
   private String[] signedCards=new String[10];
   private String[] colors={"blue", "green", "red", "yellow"};

   Random rd=new Random();

   public Decks(String[] hd, String[] cd, Cards gameCards, String[] sc,String [] color){
       humanDeck=hd;
       computerDeck=cd;
       gameDeck=gameCards.getGameDeck();
       signedCards=sc;
       colors=color;
   }

    public void setHumanDeck(String[] hd) {hd=this.humanDeck;}
    public void setComputerDeck(String[] cd) {cd=this.computerDeck;}
    public void setGameCards(String[] gameCards) {gameCards=this.gameDeck;}
    public void setSignedCards(String[] sc) {sc=this.signedCards;}

    public String[] getHumanDeck() {return humanDeck;}
    public String[] getComputerDeck() {return computerDeck;}
    public String[] getGameCards() {return gameDeck;}
    public String[] getSignedCards() {return signedCards;}


    public String[] signedDeck(String[] signedCards) {
        for (int i = 0; i < 10; i++) {
            int index = rd.nextInt(colors.length);
            signedCards[i] = colors[index] + " " + rd.nextInt(6);
        }
        return signedCards;
    }

    public String[] computerDeck(String[] computerDeck){
            int index=5;
       for(int i=0;i<5;i++){
           computerDeck[i]=gameDeck[i];
       }
       for(int i=0; i<3;i++){
           computerDeck[index]=signedCards[i];
           index++;
       }
        System.out.println(Arrays.toString(computerDeck));
        return computerDeck;
    }

    public String[] humanDeck(String[] humanDeck){
            int index=5;
            int n=gameDeck.length;
       for (int i=0;i<5;i++){
            humanDeck[i]=gameDeck[n-1];
            n--;
       }
       for(int i=3;i<6;i++) {
           humanDeck[index] = signedCards[i];
           index++;
       }
        System.out.println(Arrays.toString(humanDeck));
        return humanDeck;
    }
}
