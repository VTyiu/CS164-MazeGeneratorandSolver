package cs146F20.tang.project3;

public class Cell {

	private Cell parent;
	private int col;
	private int row;
	private boolean allWallsIntact;
	private boolean hasNorth;
	private boolean hasEast;
	private boolean hasSouth;
	private boolean hasWest;
	private boolean visitedBFS;
	private boolean visitedDFS;
	private boolean visited;
	private boolean shortest;
	private int label;
	
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
		allWallsIntact = true;
		hasNorth = true;
		hasEast = true;
		hasSouth = true;
		hasWest = true;
		visitedDFS = false;
		visited = false;
		label = -1;
		parent = null;
		visitedBFS = false;
		shortest = false;
		
	}
	
	public void setShortest(boolean theShortest) {
		shortest = theShortest;
	}
	
	public boolean getShortest() {
		return shortest;
	}
	
	public void setVisitedBFS(boolean visited) {
		visitedBFS = visited;
	}
	
	public boolean getVisitedBFS() {
		return visitedBFS;
	}
	
	public void setParent(Cell cell) {
		parent = cell;
	}
	
	public Cell getParent() {
		return parent;
	}
	
	public void setLabel(int label) {
		this.label = label;
	}
	
	public int getLabel() {
		return label;
	}
	
	public void setVisitedDFS(boolean visited) {
		visitedDFS = visited;
	}
	
	public boolean getVisitedDFS() {
		return visitedDFS;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public boolean getVisited() {
		return visited;
	}
	
	/*
	 * Sets the boolean of the cell to show whether its blocked
	 * @param blocked the boolean to set isBlockedCell to
	 */
	public void setAllWallsIntact(boolean wallsIntact) {
		this.allWallsIntact = wallsIntact;
	}
	
	public boolean getHasNorthWall() {
		return hasNorth;
	}
	public boolean getHasEastWall() {
		return hasEast;
	}
	public boolean getHasSouthWall() {
		return hasSouth;
	}
	public boolean getHasWestWall() {
		return hasWest;
	}
	
	public void setHasNorthWall(boolean wall) {
		hasNorth = wall;
	}
	public void setHasEastWall(boolean wall) {
		hasEast = wall;
	}
	public void setHasSouthWall(boolean wall) {
		hasSouth = wall;
	}
	public void setHasWestWall(boolean wall) {
		hasWest = wall;
	}
	
	/*
	 * Gets the true or false value of isBlockedCell
	 * @return true or false value of isBlockedCell
	 */
	public boolean getAllWallsIntact() {
		return allWallsIntact;
	}
	
	/*
	 * Sets the position of the cell
	 * @param x the x to set the cell to
	 * @param y the y to set the cell to
	 */
	public void setPos(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/*
	 * Gets the x of the cell
	 * @return the x of the cell
	 */
	public int getRow() {
		return row;
	}
	
	/*
	 * Gets the y of the cell
	 * @return the y of the cell 
	 */
	public int getCol() {
		return col;
	}
	
	/*
	 * Sets the x of the cell
	 * @param x the x to set the cell to
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/*
	 * Sets the y of the cell
	 * @param y the y to set the cell to
	 */
	public void setCol(int col) {
		this.col = col;
	}
}
