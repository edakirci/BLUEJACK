import java.util.*;
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
        Scanner sc=new Scanner(System.in);

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
        String drawnCardPlayer=null;
        String drawnCardComputer=null;

        int bjScorePlayer=0;
        int bjScoreComputer=0;
        int normalScorePlayer=0;
        int normalScoreComputer=0;

        int playerPoint=0;
        int computerPoint=0;



        public void playerDrawToBoard(String drawnCardPlayer,String[] playerBoard){
            if (playerBoard.length < 9) {
                playerBoard[playerBoard.length - 1] = drawnCardPlayer;
            }
        }

        public void computerDrawToBoard(String drawnCardComputer,String[] computerBoard){
            if (computerBoard.length < 9) {
                computerBoard[computerBoard.length - 1] = drawnCardComputer;
            }
        }


        while(bjScorePlayer!=1 && bjScoreComputer!=1 && normalScorePlayer!=3 && normalScoreComputer!=3 && playerPoint<=20 && computerPoint<=20) {

            for (int i = 0; i < 4; i++) {
                computerHand[i] = computerdeck[i];
            }

            for (int i = 0; i < 4; i++) {
                playerHand[i] = humandeck[i];
            }
            System.out.println("computer hand: " + "XXXX");
            System.out.println("player hand: " + Arrays.toString(playerHand));

                while(true){
                    System.out.println("Player's turn");
                    String drawnCardPlayer = playerDrawToBoard(gamedeck);
                    System.out.println("What do you want to do: 1. Stand or 2.Play a card from your hand.Enter your choice or 3.End your turn");
                    int choice=sc.nextInt();
                      if(choice!=1&&choice!=2&&choice!=3){
                         System.out.println("Your choice is invalid. Please try again");
                         choice=sc.nextInt();
                      }


                }
        }


        if(bjScorePlayer==1||normalScorePlayer==3) {
            System.out.println("Congrats! You won the game");
        } else if(bjScoreComputer==1|| normalScoreComputer==3){
            System.out.println("The CPU won this game");
        }
    }

}
