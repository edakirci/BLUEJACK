import java.util.Arrays;

public class Decks {
   private String[] humanDeck=null;
   private String[] computerDeck=null;
   private String[] gameDeck=null;

   public Decks(String[] hd, String[] cd, Cards gameCards){
       humanDeck=hd;
       computerDeck=cd;
       gameDeck=gameCards.getGameDeck();
   }

    public void setHumanDeck(String[] hd) {hd=this.humanDeck;}
    public void setComputerDeck(String[] cd) {cd=this.computerDeck;}
    public void setGD(String[] gameCards) {gameCards=this.gameDeck;}
    public String[] getHumanDeck() {return humanDeck;}
    public String[] getComputerDeck() {return computerDeck;}
    public String[] getGD() {return gameDeck;}

    public String[] computerDeck(String[] computerDeck){
       for(int i=0;i<5;i++){
           computerDeck[i]=gameDeck[i];
       }
        System.out.println(Arrays.toString(computerDeck));
        return computerDeck;
    }

    public String[] humanDeck(String[] humanDeck){
        int n=gameDeck.length;
       for (int i=0;i<5;i++){
            humanDeck[i]=gameDeck[n-1];
            n--;
       }
        System.out.println(Arrays.toString(humanDeck));
        return humanDeck;
    }


}
