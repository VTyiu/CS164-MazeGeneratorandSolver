package cs146F20.tang.project3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maze {
	
	private int[][] adjMatrix;
	private Cell[][] cellMatrix;
	private int size;
	private Random random;
	Stack<Cell> cellStack;
	
	public Maze(int size) {
		this.size = size;
		adjMatrix = new int[size][size];
		cellMatrix = new Cell[size][size];
		random = new Random();
		//random.setSeed(17);
	
		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size; col++) {
				cellMatrix[row][col] = new Cell(row, col);
			}
		}
	}
	
	//public void generateMaze() {
	public Cell[][] generateMaze() {
		
		cellStack = new Stack<Cell>();
		int totalCells = size*size;
		Cell currentCell = cellMatrix[0][0];
		int visitedCells = 1;
		
		while(visitedCells < totalCells) {
			ArrayList<Cell> neighbors = getNeighbors(currentCell);
			if(!neighbors.isEmpty()) {
				int index = random.nextInt(neighbors.size());
				Cell nextCell = neighbors.get(index);
				
				adjMatrix[currentCell.getRow()][currentCell.getCol()] = 1;
				adjMatrix[nextCell.getRow()][nextCell.getCol()] = 1;
				
				// nextCell is south of currentCell
				if(currentCell.getRow() + 1 == nextCell.getRow() && currentCell.getCol() == nextCell.getCol()) {
					currentCell.setHasSouthWall(false);
					nextCell.setHasNorthWall(false);
				}
				
				// nextCell is north of currentCell
				else if(currentCell.getRow() - 1 == nextCell.getRow() && currentCell.getCol() == nextCell.getCol()) {
					currentCell.setHasNorthWall(false);
					nextCell.setHasSouthWall(false);
				}
				
				// nextCell is east of currentCell
				else if(currentCell.getRow() == nextCell.getRow() && currentCell.getCol() + 1 == nextCell.getCol()) {
					currentCell.setHasEastWall(false);
					nextCell.setHasWestWall(false);
				}
				
				// nextCell is west of currentCell
				else if(currentCell.getRow() == nextCell.getRow() && currentCell.getCol() - 1 == nextCell.getCol()) {
					currentCell.setHasWestWall(false);
					nextCell.setHasEastWall(false);
				}
				
				currentCell.setVisited(true);
				nextCell.setVisited(true);
				currentCell.setAllWallsIntact(false);
				nextCell.setAllWallsIntact(false);
				
				cellStack.push(currentCell);
				currentCell = nextCell;
				visitedCells++;
			}
			else {
				currentCell = cellStack.pop();
			}	
		}
		
		cellMatrix[0][0].setHasNorthWall(false);
		cellMatrix[size-1][size-1].setHasSouthWall(false);
		return cellMatrix;
	}
	
	public ArrayList<Cell> getNeighbors(Cell cell){
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		int row = cell.getRow();
		int col = cell.getCol();
		
		if(row - 1 >= 0 && cellMatrix[row - 1][col].getAllWallsIntact() && !cellMatrix[row - 1][col].getVisited()) {
			neighbors.add(cellMatrix[row - 1][col]);
		}
		
		if(row + 1 < size && cellMatrix[row + 1][col].getAllWallsIntact() && !cellMatrix[row + 1][col].getVisited()) {
			neighbors.add(cellMatrix[row + 1][col]);
		}
		
		if(col - 1 >= 0 && cellMatrix[row][col - 1].getAllWallsIntact() && !cellMatrix[row][col - 1].getVisited()) {
			neighbors.add(cellMatrix[row][col - 1]);
		}
		
		if(col + 1 < size && cellMatrix[row][col + 1].getAllWallsIntact() && !cellMatrix[row][col + 1].getVisited()) {
			neighbors.add(cellMatrix[row][col + 1]);
		}
		
		return neighbors;
	}
	
	public void printMaze() {
		
		for(int r = 0; r < size; r++) {
			Cell[] row = cellMatrix[r];
			
			// print top
			for(Cell cell : row) {
				if(cell.getHasNorthWall()) {
					System.out.print("+-");
				}
				else {
					System.out.print("+ ");
				}
			}
			System.out.println("+");
			
			// print middle
			for(int c = 0; c < size; c++) {
				Cell cell = row[c];
				if(cell.getHasWestWall()) {
					System.out.print("| ");
				}
				else {
					System.out.print("  ");
				}
				if(c == size - 1) {
					System.out.println("|");
				}
			}
			
			// print bottom
			if(r == size - 1) {
				for(Cell cell : row) {
					if(cell.getHasSouthWall()) {
						System.out.print("+-");
					}
					else {
						System.out.print("+ ");
					}
				}
				System.out.println("+");
			}
		}
	}
	
	public Cell[][] getCellMatrix(){
		return cellMatrix;
	}
	
	/*
	public static void main(String[] args) {
		Maze test = new Maze(4);
		test.printMaze();
		test.generateMaze();
		test.printMaze();
		
		System.out.println("cellMatrix[2][2] has east wall: " + test.cellMatrix[2][2].getHasEastWall());
		System.out.println(test.cellMatrix[0][1].getHasEastWall());
		//System.out.println(test.cellMatrix[2][2].getRow());
		//System.out.println(test.cellMatrix[2][2].getCol());
		
	}
	*/

}
