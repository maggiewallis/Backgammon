import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DiceCupTest 
{

	private DiceCup dc;
	@Before
	public void setUp() throws Exception 
	{
		dc = new DiceCup();
	}

	@Test
	public void testIsDoubles() 
	{
		dc.debugSetDice(3, 4);
		assertFalse(dc.isDoubles());
		dc.debugSetDice(2, 2);
		assertTrue(dc.isDoubles());
	}
	
	@Test
	public void testCalculateAvailableMoves()
	{
		dc.debugSetDice(4,3);
		int[] avail = dc.debugGetAvailableMoves();
		assertTrue(avail[0] == 4 || avail[1]==4);
		assertTrue(avail[0] == 3 || avail[1]==3);
		assertFalse(avail[0] != 3 && avail[0] != 4);
		assertFalse(avail[1] != 3 && avail[1] != 4);
		
		dc.debugSetDice(2, 2);
		avail = dc.debugGetAvailableMoves();
		int num2 = 0;
		int num5 = 0;
		int numOther = 0;
		for (int i=0; i<avail.length; i++)
		{
			if (avail[i]==2)
				num2++;
			else if (avail[i]==5)
				num5++;
			else
				numOther ++;
		}
		assertEquals(4,num2);
		assertEquals(0,num5);
		assertEquals(0,numOther);
	}
	@Test
	public void testIsLegal() 
	{
		dc.debugSetDice(3,5);
		assertTrue(dc.isLegal(5));
		assertTrue(dc.isLegal(3));
		assertFalse(dc.isLegal(2));
		dc.debugSetDice(2,2);
		assertTrue(dc.isLegal(2));
		assertFalse(dc.isLegal(5));
		assertFalse(dc.isLegal(3));
	}

	@Test
	public void testMoveMade() 
	{
		dc.debugSetDice(3,4);
		dc.moveMade(3);
		assertTrue(dc.hasMovesLeft());
		assertTrue(dc.isLegal(4));
		assertFalse(dc.isLegal(3));
		dc.moveMade(4);
		assertFalse(dc.hasMovesLeft());
		
		dc.debugSetDice(1, 1);
		assertTrue(dc.isLegal(1));
		dc.moveMade(1);
		assertTrue(dc.isLegal(1));
		dc.moveMade(1);
		assertTrue(dc.hasMovesLeft());
		assertFalse(dc.isLegal(6));
		dc.moveMade(1);
		assertTrue(dc.isLegal(1));
		dc.moveMade(1);
		assertFalse(dc.isLegal(1));
		assertFalse(dc.hasMovesLeft());
	}

}
