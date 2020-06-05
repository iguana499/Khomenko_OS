import java.util.ArrayList;

public class Knot {

	ArrayList<Cell> cells = new ArrayList<Cell>();

	public ArrayList<Cell> getCells() {
		return cells;
	}

	public void setCells(ArrayList<Cell> cells) {
		this.cells = cells;
	}
	
	public void addCell(Cell cell) {
		cells.add(cell);
	}
	public void delCell(Cell cell) {
		cells.remove(cell);
	}
}
