package edu.uopeople.cs1103.unit3.grading;

/*
 * CS1103
 * Written Assignment: Unit 3
 * "A Class for Turing Machine Tapes"
 * Author: Michael Pastor
 * Created: 2017.07.05
 * 
 * ==RESULTS=======================================
 *  ================================================
 */

public class Tape {

	public static void main(String[] args){
	}
	
	private Cell currentCell;
	
	public Tape(){
		// Blank tape with 1 blank cell :
		currentCell = createCell();
	}
	
	private Cell createCell(){
		// Creates new cell :
		Cell blankCell = new Cell();
		blankCell.content = ' ';
		blankCell.prev = null;
		blankCell.next = null;
		
		return blankCell;
	}
	
	public void setContent(char pContent){
		currentCell.content = pContent;
	}

	public Cell getCurrentCell(){
		return currentCell;
	}
	
	public char getContent(){
		return currentCell.content;
	}
	
	public String getTapeContents(){
		// Returns all characters from all cells on the tape :
		String tapeContent = "";
		Cell index = currentCell;
		
		// Step 1: rewind :
		while(index.prev != null)
			index = index.prev;
		
		// Step 2: record :
		while(index != null){
			tapeContent += index.content;
			index = index.next;
		}
		
		//Step 3: return clean tape :
		return tapeContent.trim();
	}
	
	public void moveLeft(){
		// Moves cell location 1 cell left of current along tape :
		if(currentCell.prev==null){
			// Create new cell where one does not exist:
			currentCell.prev = createCell();
			currentCell.prev.next = currentCell;
		}
		// Update current cell to the one on left :
		currentCell = currentCell.prev;
	}
	

	public void moveRight(){
		// Moves cell location 1 cell right of current along tape :
		if(currentCell.next==null){
			// Create new cell where one does not exist:
			currentCell.next = createCell();
			currentCell.next.prev = currentCell;
		}
		// Update current cell to the one on right :
		currentCell = currentCell.next;
	}
	
	
	
	
	
	
}