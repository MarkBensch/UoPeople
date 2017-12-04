package edu.uopeople.cs1103.unit3;


/**
 * manages the a Tape in a turing machine.
 *
 * @author Mark Bensch
 *
 * <b>output from TestTuringMachine.java</b>
 * Running machine #1.  Output should be:  Hello
 * Actual output is:                       Hello
 *
 * Running machine #2.  Should throw an IllegalStateExcpetion.
 * Caught Illegal Argument Exception, with error message:
 * Cannot find an applicable rule; tape contents =  ERROR
 *
 * Running machine #3.  Output should be: aababbbababbabbaba aababbbababbabbaba
 * Actual output is:                      aababbbababbabbaba aababbbababbabbaba
 */

public class Tape {
	private Cell cell;
	private char initialValue = ' ';

	/**
	 * creates an initial cell with an initial value
	 */
	public Tape() {
		cell = new Cell();
		setContent( initialValue );
	}

	/**
	 * @return the char from the current cell.
	 */
	public char getContent() {
		return getCurrentCell().content;
	}

	/**
	 * @param content
	 */
	public void setContent( char content ) {
		getCurrentCell().content = content;
	}

	/**
	 * Returns a String consisting of the chars from all the cells on the tape,
	 * read from left to right, except that leading or trailing blank characters should be discarded.
	 *
	 * @return String
	 */
	public String getTapeContents() {
		StringBuilder content = new StringBuilder();
		Cell navigate = getCurrentCell();

		while ( navigate.prev != null ) {
			navigate = navigate.prev;
		}
		while ( navigate.next != null ) {
			content.append( navigate.content );
			navigate = navigate.next;
		}
		content.append( navigate.content );
		return content.toString();
	}

	/**
	 * moves the current cell one position to the left along the tape.
	 */
	public void moveLeft() {
		if ( getCurrentCell().prev == null ) {
			getCurrentCell().prev = new Cell();
			getCurrentCell().prev.next = getCurrentCell();
			getCurrentCell().prev.content = initialValue;
		}
		cell = getCurrentCell().prev;
	}

	/**
	 * moves the current cell one position to the right along the tape.
	 */
	public void moveRight() {
		if ( getCurrentCell().next == null ) {
			getCurrentCell().next = new Cell();
			getCurrentCell().next.prev = getCurrentCell();
			getCurrentCell().next.content = initialValue;
		}
		cell = getCurrentCell().next;
	}

	/**
	 * @return the pointer that points to the current cell.
	 */
	public Cell getCurrentCell() {
		return cell;
	}
}
