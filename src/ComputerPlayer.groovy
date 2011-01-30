/**
*/

class ComputerPlayer {
	
	char compMark = 'X'
	char humMark = 'O'
	def first = 1
	def second = 2
	def third = 3

	void makeMove(Board board){
		boolean moved
		moved = makeCenterMove(board)
		if (!moved)
			moved = makeWinningMove(board)
		if (!moved)
			moved = makeForcedMove(board)
		if (!moved)
			moved = makeCornerMove(board)
		if (!moved)
			moved = makeEdgeMove(board)
		//if moved not true by now code is broken
		assert(moved)
		
	}
	
	boolean makeCenterMove(Board board) {
		if (board.isEmpty(second, second)) {
			board.set second, second, compMark
			return true
		}
		return false
	}
	
	boolean makeWinningMove(Board board) {
		return testLines(board, compMark)
	}
	
	boolean makeForcedMove(Board board) {
		return testLines(board, humMark)
	}
	
	boolean makeCornerMove(Board board) {
		for (i in [first, third])
			for (j in [first, third]){
				if (board.isEmpty(i, j)) {
					board.set(i, j, compMark)
					return true
				}
			}
		return false
	}
	
	boolean makeEdgeMove(Board board) {
		if (board.isEmpty(first, second)){
			board.set(first, second, compMark)
			return true;
		}
		else {
			for (i in [first, third]) {
				if (board.isEmpty(second, i)) {
					board.set(second, i. compMark);
					return true;
				}
			}
		}
		if (board.isEmpty(third, second)) {
			board.set(third, second. compMark)
			return true;
		}
		return false
	}

	private boolean testLines(Board board, char chMark)
	{
		int testLine = testHorizontals(board, chMark)
		if (testLine > 0)
			return moveHorizontal(board, testLine)
		else {
			testLine = testVerticals(board, chMark)
			if (testLine > 0)
				return moveVertical(board, testLine)
			else {
					testLine = testDiagonals(board, chMark)
					if (testLine > 0)
						return moveDiagonal(board, testLine)
					else return false
			}
		}
	}
	
	private boolean moveHorizontal(Board board, int line)
	{
		if (line < first || line > third)
			return false
		for (i in first .. third) {
			if (board.isEmpty(line, i)){
				board.set(line, i, compMark)
				return true
		}
	}
		return false
	}

	private boolean moveVertical(Board board, int line)
	{
		if (line < first || line > third)
			return false
			for (i in first .. third) {
				if (board.isEmpty(i, line)) {
					board.set(i, line, compMark)
					return true
				}
			}
			return false
	}

	private boolean moveDiagonal(Board board, int line)
	{
		if (line < first || line > second)
			return false
			if (board.isEmpty(second, second)) {
				board.set(second, second, compMark)
				return true
			}
			if (line == first) {
				if (board.isEmpty(first, first)) {
					board.set(first, first, compMark)
					return true;
				}
				else {
					if (!board.isEmpty(third, third))
					return false
					board.set(third, third, compMark)
					return true
				}
			}
			if (board.isEmpty(first, third)) {
				board.set(first, third, compMark)
				return true
			}
			if (board.isEmpty(third, first)) {
				board.set(third, first, compMark)
				return true
			}
	
			return false
	}

//test for possible winning line
private int testHorizontals(Board board, char ch) {
	def score
	for (i in first .. third){
		score = 0
		for (j in first .. third) {
			char found = board.get(i, j)
			if (found == ch)
				score++
			else if (found != ' ') {
				score = -1
				continue
			}
		}
		if (score == 2)
			return i
	}
	return -1
}

private int testVerticals(Board board, char ch) {
	boolean winnable = true
	def score
	for (i in first .. third){
		score = 0
		for (j in first .. third) {
			char found = board.get(j, i)
			if (found == ch)
				score++
			else if (found != ' ') {
				score = -1
				continue
			}
		}
		if (score == 2)
			return i
	}
	return -1
}

private int testDiagonals(Board board, char ch) {
	char foundCenter = board.get(second, second)
	if (foundCenter !=ch && foundCenter != ' ')
		return -1;
	//top left to bottom right
	int score = (foundCenter == ch)? 1:0
	char foundTopLeft = board.get(first, first)
	if (foundTopLeft == ch)
		score++
	else if (foundTopLeft != ' ')
		score = -1
	if (score > 0) {
		char foundBottomRight = board.get(third, third)
		if (foundBottomRight == ch)
			return first;
		if (score == 2 && foundBottomRight == ' ')
			return first;
	}
	//top right to bottom left
	score = (foundCenter == ch)? 1:0
	char foundTopRight = board.get(first, third)
	if (foundTopRight == ch)
		score++
	else if (foundTopRight != ' ')
		return -1
	if (score > 0) {
		char foundBottomLeft = board.get(third, first)
		if (foundBottomLeft == ch)
			return second;
		if (score == 2 && foundBottomLeft == ' ')
			return second
	}
	
	return -1

}

}