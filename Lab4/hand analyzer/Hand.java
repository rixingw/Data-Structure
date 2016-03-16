/**
 * Hand.java
 * @author leeg3
 */ 

import java.util.Arrays;  
import java.util.Comparator;
import java.util.stream.Stream;

public class Hand 
{
	//creates the array of cards that will be the hand
	private Card[] hand = new Card[2];
	
	//enum for returning the hand ranks
	public enum HandRank  
	{
	    ROYAL_FLUSH,
	    STRAIGHT_FLUSH,
	    FOUR_OF_A_KIND,
	    FULL_HOUSE,
	    FLUSH,
	    STRAIGHT,
	    THREE_OF_A_KIND,
	    TWO_PAIR,
	    PAIR, 
	    HIGH_CARD;
	}
	
	/**
	 * sets the hand to the card array that gets passed to it
	 * @param hand
	 */
	public Hand(Card[] hand) 
	{ 
	    this.hand = hand;
	}
	
	/**
	 * returns the hand 
	 * @return hand
	 */
	public Card[] getHand() 
	{ 
	    return hand;
	}
	/**
	 * sets the players hand to the card array that is passed to it
	 * @param hand
	 */
	public void setHand(Card[] hand) 
	{
	    this.hand = hand;
	}
	
	/**
	 * prints the players hand 
	 */
	public void printHand() 
	{
	    for (Card c : hand) 
	    {
	        System.out.println(c);
	    }
	}
	
	/**
	 * determines what rank the hand + flop are 
	 * @param flop
	 * @return correct hand rank 
	 */
	public HandRank determineHandRank(Card[] flop) 
	{
	    if (isRoyalFlush(flop)) {
	        return HandRank.ROYAL_FLUSH;
	    } else if (isStraightFlush(flop)) {
	        return HandRank.STRAIGHT_FLUSH;
	    } else if (isFourOfAKind(flop)) {
	        return HandRank.FOUR_OF_A_KIND;
	    } else if (isFullHouse(flop)) {
	        return HandRank.FULL_HOUSE;
	    } else if (isFlush(flop)) {
	        return HandRank.FLUSH;
	    } else if (isStraight(flop)) {
	        return HandRank.STRAIGHT;
	    } else if (isThreeOfAKind(flop)) {
	        return HandRank.THREE_OF_A_KIND;
	    } else if (isTwoPair(flop)) { 
	    	return HandRank.TWO_PAIR;
	    } else if (isPair(flop)) {
	        return HandRank.PAIR;
	    } else {
	        return HandRank.HIGH_CARD; 
	    }
	
	}
	
	/**
	 * organizes the cards by rank from least to greatest
	 */
	public Comparator<Card> byRank = (Card left, Card right) -> 
	{
	    if (left.getCardRank().getRankValue() < right.getCardRank().getRankValue()) 
	    {
	        return -1;
	    } 
	    else 
	    {
	        return 1;
	    }
	};
	
	/**
	 * checks to see if the flop and hand cards are a royal flush 
	 * @param flop
	 * @return true if there is a royal flush 
	 */
	public boolean isRoyalFlush(Card[] flop)  
	{
	    if (isStraight(flop) && isFlush(flop)) 
	    {
	        Card[] allCards = Stream.concat(Arrays.stream(flop), Arrays.stream(hand)).toArray(Card[]::new);
	        boolean aceExists = false, kingExists = false, queenExists = false, jackExists = false, tenExists = false;
	        String rank;
	        for (Card c : allCards) 
	        {
	        	rank = c.getCardRank().getRankRank().toLowerCase();
	            switch (rank) 
	            { 
	                case "ace":
	                    aceExists = true; 
	                    break;
	                case "king":
	                    kingExists = true;
	                    break;
	                case "queen":
	                    queenExists = true;
	                    break;
	                case "jack":
	                    jackExists = true;
	                    break;
	                case "ten": 
	                    tenExists = true;
	                    break;
	            }
	        }
	        return (aceExists && kingExists && queenExists && jackExists && tenExists);
	    } 
	    else 
	    {
	        return false;
	    } 
	}
	
	/**
	 * determines if the flop and hand cards are in order  
	 * @param flop
	 * @return true if 5 of the 7 cards are straight 
	 */
	public boolean isStraight(Card[] flop)  
	{
	    Card[] allCards = Stream.concat(Arrays.stream(flop), Arrays.stream(hand)).toArray(Card[]::new);
	    Arrays.sort(allCards, byRank);
	    int noOfCardsInARow = 0;
	    int pos = 0;
	    while (pos < allCards.length-1) 
	    {
	        if (allCards[pos + 1].getCardRank().getRankValue() - allCards[pos].getCardRank().getRankValue() == 1) 
	        {
	            noOfCardsInARow++;
	            if (noOfCardsInARow == 4) 
	            {
	                return true;
	            }
	            else 
	            {
	                pos++;
	            } 
	        } 
	        else 
	        {
	            pos++;
	        }
	    }
	    return false;
	}
	
	/**
	 * determines if the flop and hand cards are all the same suit
	 * @param flop
	 * @return true if all cards are the same suit 
	 */
	public boolean isFlush(Card[] flop) 
	{
	    Card[] allCards = Stream.concat(Arrays.stream(flop), Arrays.stream(hand)).toArray(Card[]::new);
	    int numOfClubs = 0;
	    int numOfSpades = 0;
	    int numOfHearts = 0;
	    int numOfDiamonds = 0;
	    for (Card c : allCards) 
	    {
	        switch (c.getCardSuit()) 
	        {
	            case "Heart":
	            	numOfHearts++;
	                break;
	            case "Spades":
	            	numOfSpades++;
	                break;
	            case "Clubs":
	            	numOfClubs++;
	                break;
	            case "Diamonds":
	            	numOfDiamonds++;
	                break;
	        }
	    }
	    return (numOfClubs >= 5 || numOfSpades >= 5 || numOfHearts >= 5 || numOfDiamonds >= 5);
	}
	
	/**
	 * determines if the flop and hand cards are the same suit and also in numerical order 
	 * @param flop
	 * @return true if the cards are the same suit and in numerical order 
	 */
	public boolean isStraightFlush(Card[] flop) 
	{
	    if (isFlush(flop) && isStraight(flop)) 
	    {
	        return true;
	    }
	    else 
	    {
	        return false;
	    } 
	}
	
	/**
	 * determines if the flop and hand cards have 4 of the same cards 
	 * @param flop
	 * @return true if there are 4 cards with the same value, false if there isnt 
	 */
	public boolean isFourOfAKind(Card[] flop) 
	{
		Card[] allCards = Stream.concat(Arrays.stream(flop), Arrays.stream(hand)).toArray(Card[]::new);
		Arrays.sort(allCards, byRank);
	    for (int i = 0; i < allCards.length - 3; i++) 
	    { 
	        for (int j = i+1; j < allCards.length - 2; j++) 
	        {
	        	for (int k = i+2; k < allCards.length - 1; k++)
	        	{
	        		for (int l = i+3; l < allCards.length; l++)
	        		{
	        			if (allCards[i].getCardRank().getRankValue() == allCards[j].getCardRank().getRankValue()) 
			            {
			                if (allCards[i].getCardRank().getRankValue() == allCards[k].getCardRank().getRankValue()) 
			                {
			                	if (allCards[i].getCardRank().getRankValue() == allCards[l].getCardRank().getRankValue())
			                	{
			                		return true;
			                	}
			                }
			            }
	        		}
	        	}
	        }
	    }
	    return false;
	}
	
	/**
	 * determines if the flop and the hand cards are a full house 
	 * @param flop
	 * @return true if the is a pair and 3 of a kind, false if there is not 
	 */
	public boolean isFullHouse(Card[] flop) 
	{
	    Card[] allCards = Stream.concat(Arrays.stream(flop), Arrays.stream(hand)).toArray(Card[]::new);
	    Arrays.sort(allCards, byRank);
	    int c1 = 0, c2 = 0;
	    boolean pair = false;
	    boolean threeOfAKind = false; 
	    for (int i = 0; i < allCards.length - 1; i++)
	    {
	    	for (int j = i+1; j < allCards.length; j++)
	    	{
		    	if (allCards[i].getCardRank().getRankValue() == allCards[j].getCardRank().getRankValue()) 
		    	{
		    		pair = true;
		    		c1 = i;
		    		c2 = j; 
		        }
	    	}
	    }
	    
	    if (pair == false)
	    {
	    	return false; 
	    }
	    else 
	    {
		    Card[] temp = new Card[5];
		    int index = 0; 
		    for(int i = 0; i < allCards.length; i++)
		    {
		    	if (allCards[i] != allCards[c1] && allCards[i] != allCards[c2])
		    	{
		    		if (allCards[i] != null) 
		    		{
		    			temp[index] = allCards[i];
		    			index++;  
		    		}
		    	}
		    }
		    
		    for (int i = 0; i < temp.length - 2; i++)
		    {	    	
		        for (int j = i+1; j < temp.length - 1; j++) 
		        {
		        	for (int k = i+2; k < temp.length; k++)
		        	{
			            if (temp[i].getCardRank().getRankValue() == temp[j].getCardRank().getRankValue())  
			            {
			                if (temp[k].getCardRank().getRankValue() == temp[j].getCardRank().getRankValue()) 
			                {
			                	threeOfAKind = true; 
			                }
			            }
		        	}
		        } 
		    }
	    }
	    return (pair && threeOfAKind);  
	}
	 
	/**
	 * determines if there are 3 of the same cards among the flop and hand cards 
	 * @param flop
	 * @return true if there are 3 cards of the same value, false if otherwise 
	 */
	public boolean isThreeOfAKind(Card[] flop) 
	{
	    Card[] allCards = Stream.concat(Arrays.stream(flop), Arrays.stream(hand)).toArray(Card[]::new);
	    for (int i = 0; i < allCards.length - 2; i++)
	    {
	        for (int j = i+1; j < allCards.length - 1; j++) 
	        {
	        	for (int k = i+2; k < allCards.length; k++)
	        	{
		            if (allCards[i].getCardRank().getRankValue() == allCards[k].getCardRank().getRankValue()) 
		            {
		                if (allCards[i].getCardRank().getRankValue() == allCards[j].getCardRank().getRankValue()) 
		                {
		                    return true; 
		                }
		            }
	        	}
	        }
	    }
	    return false;
	}
	
	/**
	 * determines if there are 2 pairs among the flop and hand cards 
	 * @param flop
	 * @return return true if there are 2 different pairs, false if otherwise 
	 */
	public boolean isTwoPair(Card[] flop)
	{
	    Card[] allCards = Stream.concat(Arrays.stream(flop), Arrays.stream(hand)).toArray(Card[]::new);
	    int numOfPairs = 0;
	    for (int i = 0; i < allCards.length - 1; i++)
	    {
	        for (int j = i+1; j < allCards.length; j++) 
	        {
	            if (allCards[i].getCardRank().getRankValue() == allCards[j].getCardRank().getRankValue()) 
	            {
	            	numOfPairs++;
	                if (numOfPairs == 2) 
	                {
	                    return true; 
	                }
	            }
	        }
	    }
	    return false;
	}
	
	/**
	 * determines if there is a pair among the flop and hand cards
	 * @param flop
	 * @return true if there is a pair, false if otherwise 
	 */
	public boolean isPair(Card[] flop) 
	{
	    Card[] allCards = Stream.concat(Arrays.stream(flop), Arrays.stream(hand)).toArray(Card[]::new);
	    for (int i = 0; i < allCards.length - 1; i++)
	    {
	    	for (int j = i+1; j < allCards.length; j++)
	    	{
		    	if (allCards[i].getCardRank().getRankValue() == allCards[j].getCardRank().getRankValue()) 
		        {
		    		return true; 
		        }
	    	}
	    }
	    return false;
	}
	
	/**
	 * determines the highest card among the flop and hand cards 
	 * @param flop
	 * @return the highest card 
	 */
	public Card getHighCard(Card[] flop) 
	{
	    Card[] allCards = Stream.concat(Arrays.stream(flop), Arrays.stream(hand)).toArray(Card[]::new);
	    Arrays.sort(allCards, byRank);
	    int highestCardIndex = 0;  
	    for (int i = 1; i < allCards.length; i++)
	    {
	    	if (allCards[highestCardIndex].getCardRank().getRankValue() < allCards[i].getCardRank().getRankValue())
	    	{
	    		highestCardIndex = i; 
	    	}
	    }
	    return allCards[highestCardIndex];
	}

}