public class Card 
{ 
	private String suit;
	private Rank rank;
	//isDealt: checks if the card is in play and out of the deck
	private boolean isDealt;
	
	public Card(String suit, Rank rank, boolean isDealt) 
	{
	    this.suit = suit;
	    this.rank = rank;
	    this.isDealt = isDealt;
	}
	
	public String getCardSuit() 
	{
	    return suit;
	}
	public void setCardSuit(String suit) 
	{
	    this.suit = suit;
	} 
	
	public Rank getCardRank() 
	{
	    return rank;
	}
	public void setRank(Rank rank) 
	{
	    this.rank = rank;
	}
	
	public boolean isIsDealt() 
	{
	    return isDealt;
	}
	public void setIsDealt(boolean isDealt) 
	{
	    this.isDealt = isDealt;
	}
	
	public String toString() 
	{
	    return rank + " of " + suit;// + " " + rank.getRankValue();
	}

}