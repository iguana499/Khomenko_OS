
public class Cell {

	private int size;	
	private int status=0;
	// 0 - свобода 1 - занято 2 - выбранно
	
	public Cell(int size) {
		this.size = size;
	}
	public void SetSize(int size) {
		this.size =size;
	}
	public int GetSize() {
		return size;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
