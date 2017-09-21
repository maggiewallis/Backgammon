/**
 * The board class keeps track of 24 spaces (the skinny triangles on 
 * a regular board) and the bar for each player. It keeps track of 
 * these values and determines whether a given move is legal or not.
 */
public class Board {
	
	private int[] board;
	public Board()
	{
		//--------------------
		//we want positives to start at 2 on 1, 5 on 12, 3 on 17, 5 on 19
		board = new int[26];
		//positive starting
		board[1] = 2;
		board[12] = 5;
		board[17] = 3;
		board[19] = 5;
		//negative starting
		board[24] = -2;
		board[13] = -5;
		board[8] = -3;
		board[6] = -5;
		
		//--------------------
	}
	
	/**
	 * toString - create a string representing the state of the board.
	 * @return a string containing the board state.
	 * for example, it might look like:
	 * 0 (BAR) O
	 * 1
	 * 2 OO
	 * 3 OOO
	 * 4 XX
	 * 5 
	 * 6 XXXXX
	 * ....
	 * 23 O
	 * 24 XX
	 * 25 (BAR) XX
	 */
	public String toString()
	{
		String result = "";
		//--------------------
		for (int i = 0; i<26; i++)
		{
			result += i + "\t";
			if (board[i]<0)
			{
				for (int x = 0;x < -1*board[i]; x++)
				{
					result += "X";
				}
			}
			else
				if (board[i]>0)
				{
					for (int x = 0;x < board[i]; x++)
					{
						result += "O";
					}
				}
				else
					if (board[i]==0)
					{
						result += "-";
					}
			result += "\n";
		}

		//--------------------
		return result;
	}
	
	/**
	 * playerHasPieceAtLocation - determines whether the player has at 
	 * least one chip at the given space.
	 * @param whichPlayer - this can be -1 or 1.
	 * @param location - the number of the space in question.
	 * @return whether (true/false) the player has a chip of his/her own 
	 * color at this space.
	 */
	public boolean playerHasPieceAtLocation(int whichPlayer, int location)
	{
		boolean hasPiece = false;
		//--------------------
		if (whichPlayer > 0)
		{
			if (board[location] > 0)
			{
				hasPiece = true;
			}
			else
			{
				hasPiece = false;
			}
		}
		else 
			if (whichPlayer < 0)
			{
				if (board[location] < 0)
				{
					hasPiece = true;
				}
				else
				{
					hasPiece = false;
				}
			}
		
		//--------------------
		return hasPiece;
	}
	
	/**
	 * isLegal - determines whether a chip at the given space can move
	 * the desired number of spaces
	 * @param - startingSpace
	 * @param - numSpacesToMove (this is a positive number, but might be 
	 * a move up or down, depending on what chip is in the starting space)
	 * @return whether (true/false) the player is allowed to make such a move.
	 */
	public boolean isLegal(int startingSpace, int numSpaces)
	{
		boolean legal = false;
		//--------------------
		if (board[startingSpace] < 0)
		{
			if (board[startingSpace - numSpaces] < 2)
			{
				legal = true;
			}
		}
		else
			if (board[startingSpace] > 0)
		{
				if (board[startingSpace + numSpaces] > -2)
				{
					legal = true;
				}
		}
		//is there a chip, is there only one or less of the opponent
		//--------------------
		return legal;
		
	}
	
	
	
	public boolean canMoveOff(int startingSpace)
	{
		boolean legal = false;
		//--------------------
		if (board[startingSpace] > 0)
		{

			for (int i = 0; i < 19; i++)
			{
				if (board[i] > 0)
				{
					legal = false;
					break;
				}
				else
					legal = true;
			}

		}
		else
			if (board[startingSpace] < 0)
			{

				for (int i = 25; i > 6; i--)
				{
					if (board[i] < 0)
					{
						legal = false;
						break;
					}
					else
						legal = true;
				}

			}
		//is there a chip, is there only one or less of the opponent
		//--------------------
		return legal;
		
	}
	
	/**
	 * makeMove - moves one chip from the given space by the specified amount;
	 * @param - startingSpace
	 * @param - numSpacesToMove (this is a positive number, but might be 
	 * a move up or down, depending on what chip is in the starting space)
	 * precondition: there is a chip at the starting space, hasPiece
	 * postcondition: the chip may be moved to a different space, or off the board.
	 * If the chip lands on a single enemy piece, that piece is sent to its bar.
	 */
	public void makeMove(int startingSpace, int numSpacesToMove)
	{
		//--------------------
		//add one to starting spaces and then subtract one from numSpaces (all if its negative)
		//if numSpaces + starting spaces equals one then subtract two
		//if negative chips and negative number of moves
		//all this does is make the move
		if (board[startingSpace] < 0 && isLegal(startingSpace, numSpacesToMove) == true)
		{
			int newSpace = startingSpace + numSpacesToMove;
			if (newSpace < 0)
			if (newSpace < 0)
			{
				if (canMoveOff(startingSpace) == true)
				{
					board[startingSpace] += 1; //for if its negative
					if (board[newSpace] == 1)
					{
						board[newSpace] -= 1;
						board[0] += 1;
					}
					board[newSpace] -= 1;
				}
			}
				
		}
		else 
			if (board[startingSpace] > 0 && isLegal(startingSpace, numSpacesToMove) == true)
		{
			int newSpace = startingSpace + numSpacesToMove;
			if (newSpace > 25)
			{
				if (canMoveOff(startingSpace) == true)
				{
					board[startingSpace] -= 1; //for if its positive
					if (board[newSpace] == 1)
					{
						board[newSpace] += 1;
						board[25] -= 1;
					}
					board[newSpace] += 1;
				}
					
			}
		}
		
		//--------------------
	}
	
	
	/**
	 * gameIsOver - determines whether either player has removed all 
	 * his/her pieces from the board.
	 * @return - whether (true/false) the game is over.
	 */
	public boolean gameIsOver()
	{
		boolean gameOver = false;
		//--------------------
		for (int i = 1; i < 26; i ++)
		{
			if (board[i] < 0)
			{
				gameOver = false;
				break;
			}
			else gameOver =true;
		}
		for (int i = 0; i < 25; i ++)
		{
			if (board[i] > 0)
			{
				gameOver = false;	
				break;
			}
			else gameOver =true;
		}
		
		//--------------------
		return gameOver;
	}
}
