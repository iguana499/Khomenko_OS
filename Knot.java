package os_4;

import java.util.ArrayList;

public class Knot {

	public Knot(int size) {
		super();
		this.size = size;
	}
	ArrayList<Cell> cells = new ArrayList<Cell>();
	int size;
	Knot knot;

	public ArrayList<Cell> getCells() {
		if(knot==null) {
			return cells;
		}
		else {
			ArrayList<Cell> tmp = new ArrayList<Cell>();
			tmp.addAll(cells);
			tmp.addAll(knot.getCells());
			return tmp;
		}
	}

	 private void setCells(ArrayList<Cell> cells) {
		this.cells = cells;
	}
	
	public void addCell(Cell cell) {
		if(cells.size()+1>size && knot==null) {
			knot=new Knot(size);
		}
		else if(cells.size()+1>size) {
			knot.addCell(cell);
		}
		else
		{
			cells.add(cell);
		}
	}
	public void delCell(Cell cell) {		
		if(cells.contains(cell)) {
			cells.remove(cell);
		}
		else {
			knot.delCell(cell);
		}
	}
}
