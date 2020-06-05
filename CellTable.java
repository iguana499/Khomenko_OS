
public class CellTable {
	private File file;
	private Cell startFile;
	
	public CellTable(File file, Cell startInMem) {
		this.file = file;
		this.startFile = startInMem;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Cell getStartFile() {
		return startFile;
	}

	public void setStartFile(Cell startFile) {
		this.startFile = startFile;
	}
}
