public class BluejackDemo {
    public static void main(String[] args){
        int[] cardNums={1,2,3,4,5,6,7,8,9,10};
        String[] colors={"blue","green","red","yellow"};
        String[] gamedeck=null;
        int a=40;
        int num=0;
        Cards cd=new Cards(cardNums,colors,gamedeck,num);
        System.out.println("the deck :"+cd.Deck());
        System.out.println("shuffled deck:"+cd.Shuffle(gamedeck,a));
    }

}
