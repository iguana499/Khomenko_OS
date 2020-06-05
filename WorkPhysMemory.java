import java.util.ArrayList;
import java.util.Objects;

public class WorkPhysMemory {
	private int sizeConstant;
	private int sizeSector;
	private int sizePaintSquares;
	private Cell[] place;
	private Cell startSelectedFile;
	private Cell endCell = new Cell(-1);
	
	public WorkPhysMemory(int sizeDisc,int sizeSector) {
		
		this.sizeConstant = sizeDisc;
		this.sizeSector = sizeSector;
		this.sizePaintSquares = (int) Math.sqrt(Double.parseDouble(sizeDisc/sizeSector+""));
		place = new Cell[sizeDisc/sizeSector];
		for (int i = 0; i < place.length; i++) {
			place[i] = new Cell(sizeSector);
		}
	}
	

	public void allocateMemoryForFile(File file) {
		int size = file.getSize();
		int countSectors=size/sizeSector;
		Knot newKnot = new Knot();
		if(size%sizeSector>0)
			countSectors++;
		for (int i = 0; i < place.length; i++) {
			if(place[i].getStatus()==0) {
				newKnot.addCell(place[i]);
				place[i].setStatus(1);
				countSectors--;
			}
			if(countSectors==0)
				break;
		}				
		file.setKnot(newKnot);	
	}
	
	public void clearMemory(File file) {
		Knot knot = file.getKnot();
		for (Cell cell : knot.getCells()) {
			cell.setStatus(0);
		}
		if(file.getFolder()) {
			clearFolder(file.getChilds());
		}
	}

	private void clearFolder(ArrayList<File> childs) {
		for (File file : childs) {
			if(file.getFolder()) {
				clearFolder(file.getChilds());
			}
			clearFile(file.getKnot());
		}
	}


	private void clearFile(Knot knot) {
		for (Cell cell : knot.getCells()) {
			cell.setStatus(0);
		}		
	}


	public void setStartSelectedFile(File file) {
		clearStatus();
		setStatusSelect(file.getKnot());
	}


	private void setStatusSelect(Knot knot) {
		for (Cell cell : knot.getCells()) {
			cell.setStatus(2);
		}			
	}


	private void clearStatus() {
		for (int i = 0; i < place.length; i++) {
			if(place[i].getStatus()==2) {
				place[i].setStatus(1);
			}
		}
		
	}


	public int getSizeDisc() {
		return sizeConstant;
	}


	public void setSizeDisc(int sizeDisc) {
		this.sizeConstant = sizeDisc;
	}


	public int getSizeSector() {
		return sizeSector;
	}


	public void setSizeSector(int sizeSector) {
		this.sizeSector = sizeSector;
	}


	public int getSizePaintSectors() {
		return sizePaintSquares;
	}


	public void setSizePaintSectors(int sizePaintSectors) {
		this.sizePaintSquares = sizePaintSectors;
	}


	public Cell[] getPlace() {
		return place;
	}


	public void setPlace(Cell[] place) {
		this.place = place;
	}
	
	public Cell getCell(int i) {
		return place[i];
	}

}
