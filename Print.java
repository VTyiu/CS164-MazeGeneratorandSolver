package cs146F20.tang.project3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Stack;

public class Print {
	
	private Cell[][] cellMatrix;
	private int size;
	private int visitedCells;
	
	public Print(Cell[][] theCellMatrix) {
		cellMatrix = theCellMatrix;
		size = theCellMatrix.length;
		visitedCells = 0;
	}
	
	public void writeDFSMazeToFile() {
		
		try {
			File mazeFile = new File("MazeOutputDFS.txt");
			FileWriter fw = new FileWriter(mazeFile);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("Maze:");
			bw.newLine();
			bw.newLine();
			
			for(int r = 0; r < size; r++) {
				Cell[] row = cellMatrix[r];
				
				// print top
				for(Cell cell : row) {
					if(cell.getHasNorthWall()) {
						bw.write("+-");
					}
					else {
						bw.write("+ ");
					}
				}
				bw.write("+");
				bw.newLine();
				
				// print middle
				for(int c = 0; c < size; c++) {
					Cell cell = row[c];
					if(cell.getHasWestWall()) {
						bw.write("| ");
					}
					else {
						bw.write("  ");
					}
					if(c == size - 1) {
						bw.write("|");
						bw.newLine();
					}
				}
				
				// print bottom
				if(r == size - 1) {
					for(Cell cell : row) {
						if(cell.getHasSouthWall()) {
							bw.write("+-");
						}
						else {
							bw.write("+ ");
						}
					}
					bw.write("+");
					bw.newLine();
				}
			}
			
			bw.newLine();
			bw.write("DFS:");
			bw.newLine();
			
			for(int r = 0; r < size; r++) {
				Cell[] row = cellMatrix[r];
				
				// print tops
				for(Cell cell : row) {
					if(cell.getHasNorthWall()) {
						bw.write("+-");
					}
					else {
						bw.write("+ ");
					}
				}
				bw.write("+");
				bw.newLine();
				
				// print middle
				for(int c = 0; c < size; c++) {
					Cell cell = row[c];
					if(cell.getHasWestWall()) {
						if(cell.getLabel() != -1) {
							bw.write("|" + cell.getLabel());
							visitedCells++;
						}
						else {
							bw.write("| ");
						}
					}
					else {
						if(cell.getLabel() != -1) {
							bw.write(" " + cell.getLabel());
							visitedCells++;
						}
						else {
							bw.write("  ");
						}
					}
					if(c == size - 1) {
						bw.write("|");
						bw.newLine();
					}
				}
				
				// print bottom
				if(r == size - 1) {
					for(Cell cell : row) {
						if(cell.getHasSouthWall()) {
							bw.write("+-");
						}
						else {
							bw.write("+ ");
						}
					}
					bw.write("+");
					bw.newLine();
					
				}
			}
			
			bw.newLine();
			bw.write("Shortest Path:");
			bw.newLine();
			
			Stack<Cell> thePath = new Stack<Cell>();
			Cell theCell = cellMatrix[size-1][size-1];
			cellMatrix[0][0].setShortest(true);
			cellMatrix[size-1][size-1].setShortest(true);
			while(theCell != cellMatrix[0][0]) {
				thePath.add(theCell);
				cellMatrix[theCell.getRow()][theCell.getCol()].setShortest(true);
				theCell = theCell.getParent();
			}
			thePath.add(cellMatrix[0][0]);
			
			int pathLength = thePath.size();
			
			for(int r = 0; r < size; r++) {
				Cell[] row = cellMatrix[r];
				
				// print tops
				for(Cell cell : row) {
					if(cell.getHasNorthWall()) {
						bw.write("+-");
					}
					else {
						bw.write("+ ");
					}
				}
				bw.write("+");
				bw.newLine();
				
				// print middle
				for(int c = 0; c < size; c++) {
					Cell cell = row[c];
					if(cell.getHasWestWall()) {
						if(cell.getShortest()) {
							bw.write("|#");
						}
						else {
							bw.write("| ");
						}
					}
					else {
						if(cell.getShortest()) {
							bw.write(" #");
						}
						else {
							bw.write("  ");
						}
					}
					if(c == size - 1) {
						bw.write("|");
						bw.newLine();
					}
				}
				
				// print bottom
				if(r == size - 1) {
					for(Cell cell : row) {
						if(cell.getHasSouthWall()) {
							bw.write("+-");
						}
						else {
							bw.write("+ ");
						}
					}
					bw.write("+");
					bw.newLine();
					
				}
			}
			
			bw.write("Path: ");
			while(thePath.size() != 0) {
				Cell pathCell = thePath.pop();
				bw.write("(" + pathCell.getRow() + "," + pathCell.getCol() + ") ");
			} 
			
			bw.newLine();
			bw.write("Length of path: " + pathLength);
			bw.newLine();
			bw.write("Visited cells: " + visitedCells);
			
			bw.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void writeBFSMazeToFile() {
		
		try {
			File mazeFile = new File("MazeOutputBFS.txt");
			FileWriter fw = new FileWriter(mazeFile);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("Maze:");
			bw.newLine();
			bw.newLine();
			
			for(int r = 0; r < size; r++) {
				Cell[] row = cellMatrix[r];
				
				// print top
				for(Cell cell : row) {
					if(cell.getHasNorthWall()) {
						bw.write("+-");
					}
					else {
						bw.write("+ ");
					}
				}
				bw.write("+");
				bw.newLine();
				
				// print middle
				for(int c = 0; c < size; c++) {
					Cell cell = row[c];
					if(cell.getHasWestWall()) {
						bw.write("| ");
					}
					else {
						bw.write("  ");
					}
					if(c == size - 1) {
						bw.write("|");
						bw.newLine();
					}
				}
				
				// print bottom
				if(r == size - 1) {
					for(Cell cell : row) {
						if(cell.getHasSouthWall()) {
							bw.write("+-");
						}
						else {
							bw.write("+ ");
						}
					}
					bw.write("+");
					bw.newLine();
				}
			}
			
			bw.newLine();
			bw.write("BFS:");
			bw.newLine();
			
			for(int r = 0; r < size; r++) {
				Cell[] row = cellMatrix[r];
				
				// print tops
				for(Cell cell : row) {
					if(cell.getHasNorthWall()) {
						bw.write("+-");
					}
					else {
						bw.write("+ ");
					}
				}
				bw.write("+");
				bw.newLine();
				
				// print middle
				for(int c = 0; c < size; c++) {
					Cell cell = row[c];
					if(cell.getHasWestWall()) {
						if(cell.getLabel() != -1) {
							bw.write("|" + cell.getLabel());
							visitedCells++;
						}
						else {
							bw.write("| ");
						}
					}
					else {
						if(cell.getLabel() != -1) {
							bw.write(" " + cell.getLabel());
							visitedCells++;
						}
						else {
							bw.write("  ");
						}
					}
					if(c == size - 1) {
						bw.write("|");
						bw.newLine();
					}
				}
				
				// print bottom
				if(r == size - 1) {
					for(Cell cell : row) {
						if(cell.getHasSouthWall()) {
							bw.write("+-");
						}
						else {
							bw.write("+ ");
						}
					}
					bw.write("+");
					bw.newLine();
					
				}
			}
			
			bw.newLine();
			bw.write("Shortest Path:");
			bw.newLine();
			
			Stack<Cell> thePath = new Stack<Cell>();
			Cell theCell = cellMatrix[size-1][size-1];
			cellMatrix[0][0].setShortest(true);
			cellMatrix[size-1][size-1].setShortest(true);
			while(theCell != cellMatrix[0][0]) {
				thePath.add(theCell);
				cellMatrix[theCell.getRow()][theCell.getCol()].setShortest(true);
				theCell = theCell.getParent();
			}
			thePath.add(cellMatrix[0][0]);
			
			int pathLength = thePath.size();
			
			for(int r = 0; r < size; r++) {
				Cell[] row = cellMatrix[r];
				
				// print tops
				for(Cell cell : row) {
					if(cell.getHasNorthWall()) {
						bw.write("+-");
					}
					else {
						bw.write("+ ");
					}
				}
				bw.write("+");
				bw.newLine();
				
				// print middle
				for(int c = 0; c < size; c++) {
					Cell cell = row[c];
					if(cell.getHasWestWall()) {
						if(cell.getShortest()) {
							bw.write("|#");
						}
						else {
							bw.write("| ");
						}
					}
					else {
						if(cell.getShortest()) {
							bw.write(" #");
						}
						else {
							bw.write("  ");
						}
					}
					if(c == size - 1) {
						bw.write("|");
						bw.newLine();
					}
				}
				
				// print bottomo
				if(r == size - 1) {
					for(Cell cell : row) {
						if(cell.getHasSouthWall()) {
							bw.write("+-");
						}
						else {
							bw.write("+ ");
						}
					}
					bw.write("+");
					bw.newLine();
					
				}
			}
			
			bw.write("Path: ");
			while(thePath.size() != 0) {
				Cell pathCell = thePath.pop();
				bw.write("(" + pathCell.getRow() + "," + pathCell.getCol() + ") ");
			} 
			
			bw.newLine();
			bw.write("Length of path: " + pathLength);
			bw.newLine();
			bw.write("Visited cells: " + visitedCells);
			
			bw.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		Maze maze = new Maze(7);
		DFS dfs = new DFS(maze.generateMaze());
		Print test1 = new Print(dfs.calcDFS());
		test1.writeDFSMazeToFile();
		
		BFS bfs = new BFS(maze.getCellMatrix());
		Print test2 = new Print(bfs.calcBFS());
		test2.writeBFSMazeToFile();
	}

}
