import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



class BoardTest {
	
	def secondLine = 14
	def first = 1
	def second = 2
	def third = 3
	
	Board boardBeingTested

	@Before
	public void setUp() throws Exception {
		boardBeingTested = new Board()
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBoard() {
		assertNotNull(boardBeingTested)
	}

	@Test
	public void testSet() {
		char x = 'X'
		boardBeingTested.set(first, first, x)
		assertTrue(boardBeingTested.get(first, first) == 'X')
	}

	@Test
	public void testGet() {
		char testChar = boardBeingTested.get(first, first)
		assertTrue(testChar == ' ')
	}

	@Test
	public void testIsEmpty() {
		assertTrue(boardBeingTested.isEmpty(first, first))
	}

	@Test
	public void testPrint() {
		//based on solution on stackoverflow.com
		//redirect stdOut and check string is formatted
		//correctly
		def bufStr = new ByteArrayOutputStream()
		def oldStdOut = System.out;
		def newStdOut = new PrintStream(bufStr)
		System.out = newStdOut
		boardBeingTested.print()
		System.out = oldStdOut
		String prtTestStr = bufStr.toString()
		assertTrue(prtTestStr[0] == ' ' && prtTestStr[secondLine] == '-')
	}

	@Test
	public void testGameIsOver() {
		assertFalse(boardBeingTested.gameIsOver())
		char mark = 'X'
		for (i in first .. third)
			for (j in first .. third)
				boardBeingTested.set(i, j, mark)
		assertTrue(boardBeingTested.gameIsOver())
	}

	@Test
	public void testComputerHasWon() {
		assertFalse(boardBeingTested.computerHasWon())
		char compMark = 'X'
		fillLine(first, compMark)
		assertTrue(boardBeingTested.computerHasWon())
	}

	@Test
	public void testHumanHasWon() {
		assertFalse(boardBeingTested.humanHasWon())
		char humMark = 'O'
		fillDiag(humMark)
		assertTrue(boardBeingTested.humanHasWon())
	}
	
	private void fillLine(int line, char mark){
		for (i in first .. third)
			boardBeingTested.set(line, i, mark)
	}
	
	private void fillDiag(char mark) {
		for (i in first .. third)
			boardBeingTested.set(i, i, mark)
	}
	

}
