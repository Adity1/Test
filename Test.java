/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anjel
 */
import java.util.*; 
class Card{
    private int rank;
    private String suit;    
    public Card(int rank, String suit) {
        super();
        this.rank = rank;
        this.suit = suit;
    }   
    public int getRank() {
        return rank;
    }  
    public void setRank(int rank) {
        this.rank = rank;
    }  
    public String getSuit() {
        return suit;
    }   
    public void setSuit(String suit) {
        this.suit = suit;
    }   
    public String toString() {
        String rankString = rank == 14 ? "Ace":
                (rank == 11 ? "Jack":
                    (rank == 12 ? "Queen":
                        (rank == 13 ? "King": String.valueOf(rank))));
        return rankString + " of " + suit;
    }
}
class Decks{
    private final int ranks[] = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 , 12, 13, 14};
    private final String suits[]={"Hearts","Diamonds","Clubs","Spades"};
    private Card deck[];
    private final int TOTAL_CARDS=52;
    private Random randomNumber; 
    public Decks(){        
        deck = new Card[TOTAL_CARDS];
        randomNumber = new Random();
        for(int i=0;i<deck.length;i++){
            deck[i] = new Card(ranks[i%13],suits[i/13]);
        }
    }   
    public void shuffle(){
        for(int i=0;i<deck.length;i++){
            int j = randomNumber.nextInt(TOTAL_CARDS);
            Card c = deck[i];
            deck[i] = deck[j];
            deck[j] = c;
        }       
    }  
    public Card getCard(int index){
        return deck[index];        
    }   
}
class Players{
    public final static int MAX_CARD = 3;
    private Card cards[];   
    public Players() {
        cards = new Card[MAX_CARD];
    }
    public Card[] getCards() {
        return cards;
    }
     public Card getCardAtIndex(int index) {
        if (index >= 0 && index < MAX_CARD)
            return cards[index];
        else
            return null;
    }
    public void setCardAtIndex(Card c, int index) {        
        if(index >= 0 && index < MAX_CARD)           
            cards[index] = c;             
    }   
    public int countPair() {
        int count = 0;
        int temp = 0;
        for (int i = 0; i < cards.length; i++) {
            for (int j = i + 1; j < cards.length; j++) {
                if (cards[i].getRank() == (cards[j].getRank())){
                    count++;                    
                }
            }
        }        
       return count;
    }
    public int getPairValue()
    {        
        int temp = 0;
        for (int i = 0; i < cards.length; i++) {
            for (int j = i + 1; j < cards.length; j++) {
                if (cards[i].getRank() == (cards[j].getRank())){
                   temp = cards[i].getRank();                   
                }
            }
        }        
       return temp;
    }
}
class Plays{
    private Players[] players;
    private Decks deck;
    public Plays() {
        deck = new Decks();
        int p; 
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no.of players::");
        p = sc.nextInt();
        System.out.println("\n");
        players = new Players[p];       
        for (int i =0; i< p ; i++){
            players[i] = new Players();
        }
        deck.shuffle();
    }
    public void dealCards() {
        int count = 0;
        for (int i = 0; i < players[0].getCards().length; i++){
            for (int j = 0; j < players.length; j++){
                players[j].setCardAtIndex(deck.getCard(count++), i);
            }
        }        
    }   
    public void showCards() {
        int checkHighest = 0;
         int [] highCard = new int[players.length]; 
         int[] countpair= new int[players.length];
         int checkpair = 0;
        int [] sum = new int[players.length];            
        for (int i = 0; i < players.length; i++) {  
             System.out.print("PLAYER " + (i + 1) + ": ");
            for (int j = 0; j < players[0].getCards().length; j++) {
                System.out.print("\n" + players[i].getCardAtIndex(j).toString());
            } 
            System.out.println("\n");
            System.out.println("================================================================");
            
            if(players[i].countPair()> 0)
            {
                    countpair[i] = players[i].countPair();
                    if(countpair[i] > checkpair)
                        checkpair = countpair[i];
                    highCard[i] = players[i].getPairValue();
                    if(highCard[i] > checkHighest)
                        checkHighest = highCard[i];
            }          
        }

        if(checkpair != 0){
         if(checkHighest!= 0){
            for(int i =0; i<players.length; i++){
                if(checkpair == countpair[i]){                  
                    if(checkHighest == highCard[i]){
                        for(int j = i + 1 ; j<players.length ; j++)
                        {
                            if(highCard[i] == highCard[j]) 
                            {
                               System.out.println(" Draw between Player " + (i+1) + "and" + (j) );
                               
                            }                             
                        } 
                        System.out.println("WINNER: "+ "PLAYER " + (i+1) );                      
                    }
                } 
                
            }
        } 
        }
    }
}
public class Test {
    public static void main(String[] args) {        
        Plays g = new Plays();
        g.dealCards();        
        g.showCards();
    }
}


