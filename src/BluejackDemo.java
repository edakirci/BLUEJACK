import java.util.Arrays;
import java.util.Random;
public class BluejackDemo {
    public static void main(String[] args){
        int[] cardNums={1,2,3,4,5,6,7,8,9,10};
        String[] colors={"blue","green","red","yellow"};
        String[] gamedeck=new String[10];
        String[] humandeck=new String[10];
        String[] computerdeck=new String[10];
        String[] signedcards=new String[10];
        String[] signs= {"+","-"};
        int num=0;

        Cards bjcards=new Cards(cardNums,colors,gamedeck,num);
        Decks bjdecks=new Decks(humandeck,computerdeck,bjcards,signedcards,colors,signs);
        Random rd=new Random();

        System.out.println("the deck :"+bjcards.deck());
        System.out.println("shuffled deck:"+bjcards.shuffle(gamedeck));
        System.out.println("signed cards:"+bjdecks.signedDeck(signedcards));
        System.out.println("the human deck:"+bjdecks.humanDeck(humandeck));
        System.out.println("the computer deck:"+bjdecks.computerDeck(computerdeck));

      //this is the point that my game starts
        String[] computerHand=new String[4];
        String[] computerBoard=new String[9];
        String[] playerHand=new String[4];
        String[] playerBoard=new String[9];

        int bjScorePlayer=0;
        int bjScoreComputer=0;
        int normalScorePlayer=0;
        int normalScoreComputer=0;


        while(bjScorePlayer!=1 && bjScoreComputer!=1 && normalScorePlayer!=3 && normalScoreComputer!=3) {

           for (int i = 0; i < 4; i++) {
               computerHand[i] = computerdeck[i];
           }

           for (int i = 0; i < 4; i++) {
               playerHand[i] = humandeck[i];
           }


       }
        if(bjScorePlayer==1||normalScorePlayer==3) {
            System.out.println("Congrats! You won the game");
        } else if(bjScoreComputer==1|| normalScoreComputer==3){
            System.out.println("The CPU won this game");
        }
    }

}
