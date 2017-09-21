/**
 * The dice cup is a class that takes care of rolling two dice, determining whether 
 * doubles were rolled, and keeping track of which moving numbers have been used.
 *
 */
public class DiceCup {
	private int die1, die2;
	private int[] availableMoves;
	
	public DiceCup()
	{
		//--------------------
		availableMoves = new int[4];
		//--------------------
	}
	/**
	 * calculateAvailableMoves - based on the information stored in die1 and die2,
	 * determines which moves could be made by the player and stores them in an
	 * array.
	 * If the dice are doubles, then the player gets 4 of the value of their faces.
	 * 
	 * So if die1 = 5 and die2 = 2, then availableMoves is [5,2]. (in any order)
	 * If die1 = 3 and die3 = 3, then availableMoves is [3,3,3,3]. 
	 */
	public void calculateAvailableMoves()
	{
		//--------------------
		
		availableMoves[0] = die1;
		availableMoves[1] = die2;
		
		if (isDoubles() == true)
		{
			availableMoves[2] = die1;
			availableMoves[3] = die2;
		}
		else
		{
			availableMoves[2] = 0;
			availableMoves[3] = 0;
		}
		//--------------------
	}
	/**
	 * pick two numbers for the dice, and copy these to the available moves. In the
	 * case of doubles, there are four instances. For instance, if the
	 * dice are 3 and 5, then availableMoves = [3,5]. If the dice are 6 and 6, then 
	 * availableMoves = [6, 6, 6, 6].
	 */
	public void roll()
	{
		//--------------------

		die1 = (int)((Math.random()*6) + 1);
		die2 = (int)((Math.random()*6) + 1);
		calculateAvailableMoves();
		
		//--------------------
	}
	
	public String toString()
	{
		String result = "";
		//--------------------
		result += "Available: ";
		for (int i = 0; i < 4; i++)
			result += availableMoves[i] + ", ";
		
		//--------------------
		return result;
		
	}
	/**
	 * isLegal - given a proposed move, determines whether this number is an option.
	 * @param amountToMove
	 * @return - whether the player can move this amount.
	 */
	public boolean isLegal(int amountToMove)
	{
		boolean legal = false;
		//--------------------

		for (int i = 0; i<4; i++)
		{
			if (amountToMove == availableMoves[i])
			{
				legal = true;
				break;
			}
			else
				legal = false;
		}
		//--------------------
		return legal;
	}
	/**
	 * isDoubles - indicates whether the two dice match.
	 * @return
	 */
	public boolean isDoubles()
	{
		boolean doubles = false;
		//--------------------
		if (die1 == die2)
			doubles = true;
			
		//--------------------
		return doubles;
	}
	
	/**
	 * finds the first instance of the amountToMove in availableMoves and resets
	 * it to zero.
	 * @param amountToMove
	 * precondition: amountToMove is a legal move.
	 */
	public void moveMade(int amountToMove)
	{
		//--------------------
		
		for (int i = 0; i < 4; i = i +1)
		{
			if (availableMoves[i] == amountToMove)
				{
				availableMoves[i] = 0;
				System.out.println("Move is legal.");
				break;
				}
		}
	
		//--------------------
	
	}
	
	/**
	 * hasMovesLeft - indicates whether there are still non-zero
	 * values in availableMoves.
	 * @return whether moves are still available.
	 */
	public boolean hasMovesLeft()
	{
		boolean hasMoves = true;
		//--------------------
		if (availableMoves[0] > 0 || availableMoves[1] >0 || availableMoves[2]>0 || availableMoves[3] > 0)
			{
			hasMoves = true;
			}
			else
			hasMoves = false;
		//--------------------
		return hasMoves;
	}
	
	//----------------------
	// FOR TESTING & DEBUGGING ONLY
	/**
	 * sets the dice to a and b, and calculates the availableMoves.
	 * @param a
	 * @param b
	 */
	public void debugSetDice(int a, int b)
	{
		die1=a;
		die2=b;
		calculateAvailableMoves();
	}
	public int[] debugGetDice()
	{
		int[] dice = {die1, die2};
		return dice;
	}
	
	public int[] debugGetAvailableMoves()
	{
		return availableMoves;
	}
}
