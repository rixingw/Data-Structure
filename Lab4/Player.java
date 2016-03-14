public class Player {
	String name;
	int chips;
	int card1OnHand, card2OnHand;
	
	public Player(String name, int chips){
		this.name = name;
		this.chips = chips;
	}
	
	public Player(int card1, int card2){
		card1OnHand = card1;
		card2OnHand = card2;
 	}
	
	public String getName() {
		return name;
	}
	
	public int getChips() {
		return chips;
	}
	
	public int getCard1(){
	     return card1OnHand;
	}
	
	public int getCard2(){
	     return card2OnHand;
	}
	
	public void betChips()
	{
		chips++;
	}
	public void resetChips()
	{
		chips = 0;
	}
	
    public void showTwoCards(String name){
        System.out.println("Player " + name + " holds these two cards: ");
        System.out.println(card1OnHand + card2OnHand + "\n");
    }
}
