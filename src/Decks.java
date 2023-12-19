public class Decks {
   private String[] humanDeck=null;
   private String[] computerDeck=null;

   public Decks(String[] hd, String[] cd, Cards gameCards){
       humanDeck=hd;
       computerDeck=cd;
       gameCards.getGAMEDECK();
   }

    public void setHUMANDECK(String[] hd) {hd=this.humanDeck;}
    public void setCOMPUTERDECK(String[] cd) {cd=this.computerDeck;}
    public String[] getHUMANDECK() {return humanDeck;}
    public String[] getCOMPUTERDECK() {return computerDeck;}

    public String[] playerDecks(String[] humanDeck,String[] computerDeck){
       for(int i=0;i<5;i++){
           int n=gameDeck.length;
           computerDeck[i]=gameDeck[i];
           humanDeck[i]=gameDeck[n];
           n--;
       }
        return computerDeck;
        return humanDeck;
    }


}
