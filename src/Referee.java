import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Referee class keeps track of the board, any dice, and all interactions
 * with the players. It keeps track of whose turn it is, displays the board,
 * rolls dice, and asks the users to make their moves. 
 */
public class Referee {

	// TODO: decide which private member variables the Referee should have and declare them here.
	// suggestion: the dice are an array of integers, typically 1-6, where 0 means unrolled or 
	private Board myBoard;
	private DiceCup myDice;
	private int whichPlayer;
	
	/**
	 * constructor - set up the board and players 
	 */
	// write the Referee's constructor
	public Referee()
	{
		myBoard = new Board();
		myDice = new DiceCup();
		whichPlayer = 1;
	}
	
	/**
	 * playGame - the main game loop. Roll the dice, ask the user for a
	 * move, determine whether it is legal, and then execute the move. 
	 * Repeat for any remaining dice.
	 */
	public void playGame()
	{
		// write the Referee's playGame method.
		System.out.println("Playing game."); // placeholder code.
		
		//must say who the player is
		
		while (myBoard.gameIsOver() == false)
		{
			myBoard.toString();
			myDice.roll();
			askForMove();
		}
		if (myBoard.gameIsOver() == true)
		{
			System.out.println("Game over!");
		}
		
		
		
	}

	public void askForMove()
	{
		while (myDice.hasMovesLeft() == true)
		{
			System.out.println(myBoard.toString());
			System.out.println(myDice.toString());
			if (whichPlayer ==1)
			{
				System.out.println("Player 1, You are player 0's, going towards the bottom");
			}
			else
			{
				System.out.println("Player 2, You are player X's, going towards the top");
			}
			System.out.println("What location would you like to move from?");
			int location = getInt(); //starting space
			if (myBoard.playerHasPieceAtLocation(whichPlayer, location) == true)
			{
				System.out.print("How many spots would you like to move?");
				int spots = getInt();
				if (myDice.isLegal(spots) == true && myBoard.isLegal(location, spots) == true)
				{
					myBoard.makeMove(location, spots);
					myDice.moveMade(spots);
				}
				else System.out.println("Move not legal");
			}
			else System.out.println("Move not legal");
		}
		whichPlayer *= -1;
	
	}
	
	public int getInt()
	{   
		Scanner keyboardReader = new Scanner(System.in);
		int result;
		while (true)
		{
			try 
			{
				result = keyboardReader.nextInt();
				break; // leave the loop now.
			}
			catch (InputMismatchException nfe)
			{
				System.out.println("Please enter an integer.");
			}
			keyboardReader.next(); // clears any extra characters.
		}
		return result;
	}
}
