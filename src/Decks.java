import java.util.Arrays;

public class Decks {
   private String[] humanDeck=null;
   private String[] computerDeck=null;
   private String[] gameDeck=null;

   public Decks(String[] hd, String[] cd, Cards gameCards){
       humanDeck=hd;
       computerDeck=cd;
       gameDeck=gameCards.getGAMEDECK();
   }

    public void setHUMANDECK(String[] hd) {hd=this.humanDeck;}
    public void setCOMPUTERDECK(String[] cd) {cd=this.computerDeck;}
    public void setGD(String[] gameCards) {gameCards=this.gameDeck;}
    public String[] getHUMANDECK() {return humanDeck;}
    public String[] getCOMPUTERDECK() {return computerDeck;}

    public String[] computerDeck(String[] computerDeck){
       for(int i=0;i<5;i++){
           computerDeck[i]=gameDeck[i];
       }
        System.out.println(Arrays.toString(computerDeck));
        return computerDeck;
    }

    public String[] humanDeck(String[] humanDeck){
       for (int i=0;i<5;i++){
         int n=gameDeck.length;
            humanDeck[i]=gameDeck[n];
            n--;
       }
        System.out.println(Arrays.toString(humanDeck));
        return humanDeck;
    }


}
