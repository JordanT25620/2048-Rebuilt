package me.jord.rebuilt2048;

import java.util.Random;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Board extends GridPane{	//Inherits from GridPane
	private StackPane[][] board = new StackPane[4][4];	//4x4 board containing StackPanes (each StackPane is initialized with a box/border and a Tile.
	private int numTilesFilled;							//Tracker to determine when game has ended. Currently not being used.
	
	//Constructor: Initializes the GridPane with Color.Tan boxes and invisible Tiles. 
	//Randomly selects a grid coordinate to set the first Tile as visible.
	public Board() {
		setHgap(6); //Horizontal gap between each grid cell
		setVgap(6); //Vertical gap between each grid cell
		
		//Initializing each grid cell to contain a box and an invisible tile
		for(int x=0; x<4; x++) {
			for(int y=0; y<4; y++) {
				Rectangle box = new Rectangle(53, 53);
				box.setFill(Color.BEIGE);
				box.setStroke(Color.TAN);
				box.setStrokeWidth(1.7);
				box.setArcWidth(10);
				box.setArcHeight(10);
				Tile tile = new Tile(true); //Create an empty(invisible) Tile which has a value of 0.
				board[x][y] = new StackPane(box, tile);	//Add invisible Tile to the GridPane
				numTilesFilled = 1;						//Increment the number of Tiles present on the board
				add(board[x][y], y, x);					//Displaying the array as a JavaFX GridPane
			}
		}
		//Randomly adding the first Tile with value (not an empty/invisible Tile).
		Random rand = new Random();
		int randX = rand.nextInt(4);
		int randY = rand.nextInt(4);
		((Tile)board[randX][randY].getChildren().get(1)).fillEmptyTile();
	}
	
	//moveLeft() is called for all board shifts after a rotations to the board using rotateBoard(), resulting in shifts in all 4 directions.
	public boolean moveLeft() {
		boolean success = false; //Variable to track if any Tiles were moved or merged
		
		//Tracking if anything was moved or merged during the shift (if nothing was changed, a new Tile should NOT be spawned).
		for(int x=0; x<4; x++) {
			success |= shiftArray(board[x]);
		}
		
		//If no successful shifts are possible, the game is over.
		if(!possibleMove()) {
			Stage primaryStage = (Stage) getScene().getWindow();
			primaryStage.setScene(new TitleScene(primaryStage));
		} else { //If there are successful moves possible, check if the recent move resulted in any shift or merge.
			if(success) {	//If anything was moved or merged, a new Tile is randomly spawned in an empty Grid cell.
				Random rand = new Random();
				int randX = rand.nextInt(4);
				int randY = rand.nextInt(4);
				while(! (	(Tile)board[randX][randY].getChildren().get(1)	).isEmpty()) {
					randX = rand.nextInt(4);
					randY = rand.nextInt(4);
				}
				((Tile)board[randX][randY].getChildren().get(1)).fillEmptyTile(); //Turns an invisible Tile visible and assigns it a value of 2 or 4.
				numTilesFilled++;	//Increment number of filled Tiles
			}
		}
		return success;
	}
	
	//Uses board rotations so moveLeft() can be reused for shifting the board to the right.
	public boolean moveRight() {
		boolean success;
		rotateBoard();
		rotateBoard();
		success = moveLeft();
		rotateBoard();
		rotateBoard();
		return success;
	}
	
	//Uses board rotations so moveLeft() can be reused for shifting the board upwards.
	public boolean moveUp() {
		boolean success;
		rotateBoard();
		success = moveLeft();
		rotateBoard();
		rotateBoard();
		rotateBoard();
		return success;
	}
	
	//Uses board rotations so moveLeft() can be reused for shifting the board downwards.
	public boolean moveDown() {
		boolean success;
		rotateBoard();
		rotateBoard();
		rotateBoard();
		success = moveLeft();
		rotateBoard();
		return success;
	}
	
	//Shifts one row of the board at a time.
	private boolean shiftArray(StackPane[] arr) {
		int target, stopMerge=0; //target is used to determine where a Tile should be moved or merged.
								//stopMerge tells the algorithm when to stop searching in the case of multiple merges in a row.
		boolean success = false; //Tracker that is set to true if any moves or merges happen.
		for(int x=0; x<4; x++) {
			if(((Tile)arr[x].getChildren().get(1)).isEmpty() == false) { //If the Tile's value is NOT 0
				target = findTargetIndex(arr, x, stopMerge);			//Find a potential move or merge
				if(target!=x) {											//If a target has been found (besides the current index)
					if(((Tile)arr[target].getChildren().get(1)).isEmpty()) { //If the target cell is empty, this will be a move
						((Tile)arr[target].getChildren().get(1)).changeVal(((Tile)arr[x].getChildren().get(1)).getVal()); //Change the target cell (move)
					//If the target cell is not empty, this will be a merge
					} else if (((Tile)arr[target].getChildren().get(1)).getVal()==((Tile)arr[x].getChildren().get(1) ).getVal()) {
						((Tile)arr[target].getChildren().get(1)).timesTwo(); //Double the target cell
						stopMerge = target + 1;								//Set the index to stop at for any other merges in this row of the board
						numTilesFilled--;									//Decrement the number of Tiles present (since there was a merge)
					}
					//Empty the cell that was moved or merged into another cell
					((Tile)arr[x].getChildren().get(1)).changeVal(0);
					((Tile)arr[x].getChildren().get(1)).eraseVisibleTile();
					success = true;	//Return value that says there was a move or merge in this row of the board.
				}
			}
		}
		return success;
	}
	
	//This function works exactly like shiftArray() but leaves the board unchanged and only calculates the return value. This is useful for determining
	//if the game has any possible moves remaining.
	private boolean testShift(StackPane[] arr) {
		int target, stopMerge=0;	//target is used to determine where a Tile should be moved or merged.
									//stopMerge tells the algorithm when to stop searching in the case of multiple merges in a row.
		boolean success = false;	//Tracker that is set to true if any moves or merges happen.
		for(int x=0; x<4; x++) {
			if(((Tile)arr[x].getChildren().get(1)).isEmpty() == false) { //If the Tile's value is NOT 0
				target = findTargetIndex(arr, x, stopMerge);			//If a target has been found (besides the current index)
				if(target!=x) {	//There is a possible move or merge
					success = true;
				}
			}
		}
		return success;
	}
	
	//This function traverses a board row backwards (to the left) looking for possible moves or merges.
	private int findTargetIndex(StackPane[] array, int index, int stop) {
		if(index == 0) { //Index 0 is the leftmost index in the array, so no moves or merges are possible
			return index;
		}
		for(int t=index-1; ; t--) {	//Moving backwards through the board row
			if(( (Tile)array[t].getChildren().get(1) ).getVal() != 0) { //The first nonzero Tile to the left of the Tile being checked
				if(( (Tile)array[t].getChildren().get(1) ).getVal() != ( (Tile)array[index].getChildren().get(1) ).getVal()) {
					return t+1;	//If the target and index Tile values are different, only a move to the tile after the target is possible
				}
				return t;		//If the target and index Tile values are not different, a merge is possible into the target cell
			} else {	//Target Tile has value of 0
				if (t==stop) {	//Continue traversing through all continuous Tiles with value of 0 until reaching the limit of the row (or reaching
								//a previously merged Tile within the row.
					return t;
				}
			}
		}
	}
	
	//Rotates the board - this is useful so that the board can be shifted left for each move. It can then just be rotated back to the correct orientation.
	private void rotateBoard() {
		StackPane temp;
		for(int i=0; i<2; i++) {
			for(int j=i; j<3-i; j++) {
				temp = board[i][j];
				board[i][j] = board[j][3-i];
				board[j][3-i] = board[3-i][3-j];
				board[3-i][3-j] = board[3-j][i];
				board[3-j][i] = temp;
			}
		}
	}
	
	//Checks all directions to see if there is a possible move. Returns a boolean value that is used to determine whether the game should be ended.
	private boolean possibleMove() {
		boolean possible = false;
		//Testing left shift
		for(int x=0; x<4; x++) {
			possible |= testShift(board[x]);
		}
		//Testing upward shift
		rotateBoard();
		for(int x=0; x<4; x++) {
			possible |= testShift(board[x]);
		}
		//Testing right shift
		rotateBoard();
		for(int x=0; x<4; x++) {
			possible |= testShift(board[x]);
		}
		//Testing downward shift
		rotateBoard();
		for(int x=0; x<4; x++) {
			possible |= testShift(board[x]);
		}
		rotateBoard();	//Correcting the board to its prior orientation
		return possible;
	}
}