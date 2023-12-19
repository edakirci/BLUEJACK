public class BluejackDemo {
    public static void main(String[] args){
        int[] cardNums={1,2,3,4,5,6,7,8,9,10};
        String[] colors={"blue","green","red","yellow"};
        String[] gamedeck=null;
        String[] humandeck=new String[10];
        String[] computerdeck=new String[10];
        String[] signedcards=new String[10];
        int num=0;

        Cards bjcards=new Cards(cardNums,colors,gamedeck,num);
        Decks bjdecks=new Decks(humandeck,computerdeck,bjcards,signedcards,colors);

        System.out.println("the deck :"+bjcards.deck());
        System.out.println("shuffled deck:"+bjcards.shuffle(gamedeck));
        System.out.println("the human deck:"+bjdecks.humanDeck(humandeck));
        System.out.println("the computer deck:"+bjdecks.computerDeck(computerdeck));
    }

}
