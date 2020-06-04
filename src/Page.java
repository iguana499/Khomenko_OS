
public class Page {
	
	private int startAdress;
	private int endAdress;
	private boolean secondChance;
	public Page(int startAdress , int endAdress, boolean secondChance) {
		this.endAdress = endAdress;
		this.startAdress = startAdress;	
		this.secondChance = secondChance;
	}	
	public int getStartAdress(){
		return startAdress;
	}
	public int getEndAdress() {
		return endAdress;
	}
	public boolean isSecondChance() {
		return secondChance;
	}
	public void setSecondChance(boolean secondChance) {
		this.secondChance = secondChance;
	}
}