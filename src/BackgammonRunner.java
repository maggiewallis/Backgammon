
public class BackgammonRunner {

	public static void main(String[] args) 
	{
		boolean testingMode = false;
		if (testingMode)
		{
			System.out.println("Start tests");
			// enter test code here.
			System.out.println("End tests");
		}
		else
		{
			Referee ref = new Referee();
			System.out.println("Start game");
			ref.playGame();
			System.out.println("End game");
		}
	}

}
