package cs146F20.tang.project3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFS {

	private Cell[][] cellMatrix;
	private Queue<Cell> cellQueue;
	private int visitedCells;
	private int size;
	
	public BFS(Cell[][] theCellMatrix) {
		cellMatrix = theCellMatrix;
		cellQueue = new LinkedList<Cell>();
		visitedCells = 0;
		size = cellMatrix.length;
		
	}
	
	public Cell[][] calcBFS() {
		Cell currentCell = cellMatrix[0][0];
		cellQueue.add(currentCell);
		currentCell.setLabel(0);
		
		while(currentCell != cellMatrix[size-1][size-1]) {
			currentCell = cellQueue.poll();
			
			if(!currentCell.getVisitedBFS()) {
				currentCell.setLabel((visitedCells++) % 10);
				currentCell.setVisitedBFS(true);
				ArrayList<Cell> unmarkedCell = getNeighbors(currentCell);
				for(Cell neighbors : unmarkedCell) {
					if(!neighbors.getVisitedBFS()) {
						cellQueue.add(neighbors);
						neighbors.setParent(currentCell);
					}
				}
			}
		}
		System.out.println();
		System.out.println();
		//parents.add(cellMatrix[size-1][size-1]);
		return cellMatrix;
	}
	
	public ArrayList<Cell> getNeighbors(Cell cell){
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		int row = cell.getRow();
		int col = cell.getCol();

		// north of cell
		if(row - 1 >= 0) {
			if(!cellMatrix[row - 1][col].getVisitedBFS() && !cell.getHasNorthWall()) {
				neighbors.add(cellMatrix[row-1][col]);
			}
		}
		
		// south of cell
		if(row + 1 < size) {
			if(!cellMatrix[row + 1][col].getVisitedBFS() && !cell.getHasSouthWall()) {
				neighbors.add(cellMatrix[row + 1][col]);
			}
		}
		
		// west of cell
		if(col - 1 >= 0) {
			if(!cellMatrix[row][col - 1].getVisitedBFS() && !cell.getHasWestWall()) {
				neighbors.add(cellMatrix[row][col - 1]);
			}
		}
		
		// east of cell
		if(col + 1 < size) {
			if(!cellMatrix[row][col + 1].getVisitedBFS() && !cell.getHasEastWall()) {
				neighbors.add(cellMatrix[row][col + 1]);
			}
		}

		return neighbors;
	}
	
	public void printPath(){
		
		Stack<Cell> thePath = new Stack<Cell>();
		Cell cell = cellMatrix[size-1][size-1];
		while(cell != cellMatrix[0][0]) {
			thePath.add(cell);
			cell = cell.getParent();
		}
		thePath.add(cellMatrix[0][0]);
		
		
		
		while(thePath.size() != 0) {
			Cell pathCell = thePath.pop();
			System.out.print("(" + pathCell.getRow() + "," + pathCell.getCol() + ") ");
		}
		
		System.out.println("Length of path: " + thePath.size());
		
	}
	
	public void printMaze() {
		
		for(int r = 0; r < size; r++) {
			Cell[] row = cellMatrix[r];
			
			// print tops
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
					if(cell.getLabel() != -1) {
						System.out.print("|" + cell.getLabel());
					}
					else {
						System.out.print("| ");
					}
				}
				else {
					if(cell.getLabel() != -1) {
						System.out.print(" " + cell.getLabel());
					}
					else {
						System.out.print("  ");
					}
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

	
	public static void main(String[] args) {
		Maze test1 = new Maze(4);
		test1.generateMaze();
		BFS test = new BFS(test1.getCellMatrix());
		test.printMaze();
		test.calcBFS();
		test.printMaze();
		test.printPath();
		
		
	}
	

}
