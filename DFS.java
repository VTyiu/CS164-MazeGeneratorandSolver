package cs146F20.tang.project3;
import java.util.ArrayList;
import java.util.Stack;

public class DFS {
	
	private Cell[][] cellMatrix;
	private Stack<Cell> cellStack;
	private Cell currentCell;
	private int visitedCells;
	private int size;
	
	public DFS(Cell[][] theCellMatrix) {
		cellMatrix = theCellMatrix;
		cellStack = new Stack<Cell>();
		currentCell = cellMatrix[0][0];
		visitedCells = 0;
		size = cellMatrix.length;
	}
	
	public Cell[][] calcDFS() {
		cellStack.push(currentCell);
		currentCell.setLabel(0);
		
		while(currentCell != cellMatrix[size-1][size-1]) {
			currentCell = cellStack.pop();
			if(!currentCell.getVisitedDFS()) {
				currentCell.setLabel((visitedCells++) % 10);
				currentCell.setVisitedDFS(true);
				ArrayList<Cell> unmarkedCell = getNeighbors(currentCell);
				for(Cell neighbors : unmarkedCell) {
					if(!neighbors.getVisitedDFS()) {
						cellStack.push(neighbors);
						neighbors.setParent(currentCell);
					}
				}
			}
		}
		return cellMatrix;
	}
	
	public ArrayList<Cell> getNeighbors(Cell cell){
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		int row = cell.getRow();
		int col = cell.getCol();

		// north of cell
		if(row - 1 >= 0) {
			if(!cellMatrix[row - 1][col].getVisitedDFS() && !cell.getHasNorthWall()) {
				neighbors.add(cellMatrix[row-1][col]);
			}
		}
				
		// south of cell
		if(row + 1 < size) {
			if(!cellMatrix[row + 1][col].getVisitedDFS() && !cell.getHasSouthWall()) {
				neighbors.add(cellMatrix[row + 1][col]);
			}
		}
			
		// west of cell
		if(col - 1 >= 0) {
			if(!cellMatrix[row][col - 1].getVisitedDFS() && !cell.getHasWestWall()) {
				neighbors.add(cellMatrix[row][col - 1]);
			}
		}
				
		// east of cell
		if(col + 1 < size) {
			if(!cellMatrix[row][col + 1].getVisitedDFS() && !cell.getHasEastWall()) {
				neighbors.add(cellMatrix[row][col + 1]);
			}
		}
		return neighbors;
	}
	
	public void printOrder() {
		Stack<Cell> thePath = new Stack<Cell>();
		Cell cell = cellMatrix[size-1][size-1];
		cellMatrix[0][0].setShortest(true);
		cellMatrix[size-1][size-1].setShortest(true);
		while(cell != cellMatrix[0][0]) {
			thePath.add(cell);
			cellMatrix[cell.getRow()][cell.getCol()].setShortest(true);
			cell = cell.getParent();
		}
		thePath.add(cellMatrix[0][0]);
		
		int pathLength = thePath.size();
		
		System.out.print("Path: ");
		while(thePath.size() != 0) {
			Cell pathCell = thePath.pop();
			System.out.print("(" + pathCell.getRow() + "," + pathCell.getCol() + ") ");
		} 
		
		System.out.println();
		System.out.println("Length of path: " + pathLength);
		System.out.println("Visited cells: " + visitedCells);
	}
	
	public int getViistedCells() {
		return visitedCells;
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
		
		// hashtags
		
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
					if(cell.getShortest()) {
						System.out.print("|#");
					}
					else {
						System.out.print("| ");
					}
				}
				else {
					if(cell.getShortest()) {
						System.out.print(" #");
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
		DFS test = new DFS(test1.getCellMatrix());
		test.calcDFS();
		test.printOrder();
		test.printMaze();
		
		
		
	}

}
