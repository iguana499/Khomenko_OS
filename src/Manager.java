import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.StyledEditorKit.ForegroundAction;


public class Manager {
	Random rnd = new Random();
	private int memorySize = 2048;
	private int pageSize = 256;
	private boolean secondChance;
	private ArrayList<Page> phisicalMemory = new ArrayList<Page>();
	//private ArrayList<Integer> usages = new ArrayList<Integer>();
	private ArrayList<Page> virtualMemory = new ArrayList<Page>();	
	public void writePages() {
		int start=0;
		int finish=start + pageSize;
		for (int i = 0; i < memorySize/pageSize/2 ; i++) {
			switch (rnd.nextInt(1)) {
			case 0:
				secondChance = false;
				break;
			case 1:
				secondChance = true;

			default:
				break;
			}
			virtualMemory.add(new Page(start,finish, secondChance));
			//usages.add(rnd.nextInt(10)+1);
			start=finish;
			finish = start + pageSize;
		}
		for (int i = 0; i < memorySize/pageSize/2 ; i++) {
			phisicalMemory.add(new Page(start,finish, secondChance));
			start=finish;
			finish = start + pageSize;
		}
	}
	public void getPageFromPhisicalMemory(int adress) {
		boolean pickInPhisikalMemory = false;
		System.out.println("запрашиваемый адрес " + adress);
		for (int i = 0; i < phisicalMemory.size(); i++) {
			if(adress <= phisicalMemory.get(i).getEndAdress() 
					&& adress >= phisicalMemory.get(i).getStartAdress()){
						pickInPhisikalMemory=true;
						System.out.println("возвращена страница из физической памяти " + i);
						//usages.set(i,usages.get(i)+1);
					}
		}
		if (!pickInPhisikalMemory) {
			System.out.println("переход в виртуальную память");
			getPageFromVirtualMemory(adress);			
		}
	}
	public void getPageFromVirtualMemory(int adress) {
		int needPageIndex = 0;
		int replasePageIndex = 0;
		for (int i = 0; i < virtualMemory.size(); i++) {
			 			if(adress <= virtualMemory.get(i).getEndAdress() 
			 					&& adress >= virtualMemory.get(i).getStartAdress()){
									needPageIndex = i;
			 					}
		}
		for (int i = 0; i < phisicalMemory.size(); i++) {
				 replasePageIndex = i;
			 }
		for (int i=0; i < virtualMemory.size(); i++) {
			if (!virtualMemory.get(i).isSecondChance()) {
				swapPages(i, replasePageIndex);
				needPageIndex = i;
				break;
			}
			else {
				Page swap = null;
				swap = virtualMemory.get(i);
				virtualMemory.set(0,virtualMemory.get(virtualMemory.size())); 
				virtualMemory.set(virtualMemory.size(), swap);
				i=0;
			}
		}

		 System.out.println("в физическую память добавлена страница " + needPageIndex + " из виртуальной памяти  вместо страницы " + replasePageIndex);
	}
	public void swapPages(int needPageIndex,int replasePageIndex) {
		 Page swap;
		 swap = virtualMemory.get(needPageIndex);
		 virtualMemory.set(needPageIndex,phisicalMemory.get(replasePageIndex));
		 phisicalMemory.set(replasePageIndex, swap);
		 //usages.set(replasePageIndex,0);
	}
	public void printMemory() {
		System.out.println("==========Физическая мамять==========");
		for (int i = 0; i < phisicalMemory.size(); i++) {
			System.out.println(i +" start "+ phisicalMemory.get(i).getStartAdress() + " finish " + phisicalMemory.get(i).getEndAdress());
		}
		System.out.println("==========Виртуальная мамять==========");
		for (int i = 0; i < virtualMemory.size(); i++) {
			System.out.println(i +" start "+ virtualMemory.get(i).getStartAdress() + " finish " + virtualMemory.get(i).getEndAdress());
		}
		System.out.println("\n");
	}
	public void work() {
		System.out.println("---------------------------------------------");
		writePages();
		System.out.println("заполнение памяти");
		System.out.println("---------------------------------------------");
		for (int i = 0; i< rnd.nextInt(5)+1; i++) {
			System.out.println("---------------------------------------------");
			printMemory();
			getPageFromPhisicalMemory(rnd.nextInt(2000)+1);
			printMemory();
			System.out.println("---------------------------------------------");
		}
	}
}
