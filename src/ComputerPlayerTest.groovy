import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


class ComputerPlayerTest {
	def first = 1
	def second = 2
	def third = 3
	char compMark = 'X'
	char humMark = 'O'

	ComputerPlayer testCompPlayer
	
	@Before
	public void setUp() throws Exception {
		testCompPlayer = new ComputerPlayer()
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMakeMove() {
		Board testBoard = new Board();
		testCompPlayer.makeMove testBoard
		assertTrue(testBoard.get(second, second) == 'X' )
	}

	@Test
	public void testMakeCenterMove() {
		Board testBoard = new Board();
		assertTrue(testCompPlayer.makeCenterMove(testBoard))
	}

	@Test
	public void testMakeWinningMove() {
		Board testBoard = new Board()
		assertTrue(testCompPlayer.makeCenterMove(testBoard))
		testBoard.set(first, first, compMark)
		assertTrue(testCompPlayer.makeWinningMove(testBoard))
		assertTrue(testBoard.computerHasWon())
	}

	@Test
	public void testMakeForcedMove() {
		Board testBoard = new Board()
		testBoard.set(third, third, humMark)
		testBoard.set(third, second, humMark)
		assertTrue(testCompPlayer.makeForcedMove(testBoard))
		testBoard.set(third, first, humMark)
		assertTrue(testBoard.get(third, first) == compMark)
	}

	@Test
	public void testMakeCornerMove() {
		Board testBoard = new Board()
		assertTrue(testCompPlayer.makeCornerMove(testBoard))
		assertTrue(testBoard.get(first, first) == compMark)
	}

	@Test
	public void testMakeEdgeMove() {
		Board testBoard = new Board()
		assertTrue(testCompPlayer.makeEdgeMove(testBoard))
		assertTrue(testBoard.get(first, second) == compMark)
	}

}
