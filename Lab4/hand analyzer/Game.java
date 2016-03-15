public class Game 
{
	public static void main(String [] args)
	{
		Card[] hand = new Card[2]; 
		hand[0] = new Card("Hearts", new Rank(6, "Six"), true);
		hand[1] = new Card("Diamonds", new Rank(2, "Two"),true);
		
		Hand h1 = new Hand(hand); 
		
		System.out.println("Players hand");
		h1.printHand();
		   
		System.out.println("\nFlop");
		Card[] flop = new Card[5]; 
		flop[0] = new Card("Spades", new Rank(14, "Ace"), true);
		flop[1] = new Card("Spades", new Rank(13, "King"), true);
		flop[2] = new Card("Spades", new Rank(12, "Queen"), true);
		for (int i = 0; i < 3; i++) 
		{ 
			System.out.println(flop[i].toString());  
		}  
		System.out.println();    
		  
		System.out.println("Turn");  
		flop[3] = new Card("Spades", new Rank(10, "Ten"), true);  
		System.out.println(flop[3].toString());   
		System.out.println();     
		 
		System.out.println("River"); 
		flop[4] = new Card("Spades", new Rank(11, "Jack"), true);   
		System.out.println(flop[4].toString());   
		System.out.println(); 
		     
		System.out.println("Card Analysis"); 
		System.out.println("The highest hand you have is : " + h1.determineHandRank(flop));
		
		System.out.println("Is there a royal flush? " + h1.isRoyalFlush(flop)); 
		System.out.println("Is there straight flush? " + h1.isStraightFlush(flop)); 
		System.out.println("Is there 4 of a kind? " + h1.isFourOfAKind(flop));
		System.out.println("Is there a full house? " + h1.isFullHouse(flop)); 
		System.out.println("is there a flush? " + h1.isFlush(flop));  
		System.out.println("Is there a straight? " + h1.isStraight(flop)); 
		System.out.println("Is there 3 of a kind? " + h1.isThreeOfAKind(flop));
		System.out.println("Is there 2 pairs? " + h1.isTwoPair(flop)); 
		System.out.println("Is there a pair? " + h1.isPair(flop)); 
		System.out.println("High Card? " + h1.getHighCard(flop)); 
	} 

}