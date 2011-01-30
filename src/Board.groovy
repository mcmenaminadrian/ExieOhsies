/**
 * @author keith & dave, adrian_mcmenamin
 *
 */
class Board {

	char[][] board
	def first = 1
	def second = 2
	def third = 3
		
	Board(){
		board = new char[3][3] 
		for (row in 0 .. 2)
			for (col in 0 ..2)
				board[row][col] = ' '
	}
	
	void set(int row, int column, char ch) {
		if (ch != 'X' && ch != 'O') {
			errorOut("Illegal move - bad character");
			return	
		}
		else if (row < first || row > third || column < first
				|| column > third) {
			errorOut("Illegal move: ($row, $column) is out of bounds")
			return
		}
		else if (!isEmpty(row, column)) {
			errorOut("Bad move: ($row, $column) already marked with " +
					get(row, column));
			return
		}
		
		board[row - 1][column - 1] = ch
	}	
	
	char get(int row, int column){
			assert(row >= first && row <= third && column >= first
				&& column <= third)
			return board[row - 1][column - 1]
	}
	
	boolean isEmpty(int row, int column){
		return get(row,column) == ' '
	}
	
	void print(){
		def line = "---+---+---"
		for (row in first .. third) {
			for (col in first .. third) {
				print " ${get(row, col)} "
				if (col != third)
					print "|"
			}
			if (row != third) {
				print "\r\n"
				println line
			}
		}
		
	}
	
	boolean gameIsOver() {
		for (row in first ..third)
			for (col in first ..third)
				if (isEmpty(row, col))
						return false
		if (computerHasWon())
			return true
		else return humanHasWon()
	}
	
	boolean computerHasWon() {
		char compMark = 'X'
		return (checkWinningLines(compMark) || checkWinningDiag(compMark))
	}
	
	boolean humanHasWon() {
		char humMark = 'O'
		return (checkWinningLines(humMark) || checkWinningDiag(humMark))
	}
	
	private void errorOut(String errMsg){
		println(errMsg)
	}
	
	private boolean checkWinningLines(char ch) {
		for (i in first .. third) {
			if (get(i, first) == ch && get(i, second) == ch
					&& get(i, third) == ch)
				return true;
			else if (get(first, i) == ch && get(second, i) == ch
					&& get(third, i) == ch)
				return true;
		}
		return false;
	}
	
	private boolean checkWinningDiag(char ch) {
		if (get(second, second) != ch) return false;
		if (get(first, first) == ch && get(third, third) == ch)
			return true;
		else if (get(first, third) == ch && get(third, first) == ch)
			return true;
		return false;
	}

} //class Board
