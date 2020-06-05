package os_4;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MyJPanel extends JPanel {
	WorkPhysMemory wPhysMemory;
	
	public MyJPanel(WorkPhysMemory wPhysMemory) {
		this.wPhysMemory = wPhysMemory;
	}
	
	@Override
	public void paint(Graphics g) {
		int sizeX = this.getWidth()/wPhysMemory.getSizePaintSectors();
		int sizeY = this.getHeight()/wPhysMemory.getSizePaintSectors()-4;
		super.paint(g);
		int x = 0;
		int y = 0;
		for (int i = 0; i < wPhysMemory.getPlace().length; i++) {
			if (x+sizeX>=getWidth()) {
				x=0;
				y+=sizeY;
			}
			if(wPhysMemory.getCell(i).getStatus()==2) {			
				g.setColor(Color.red);
			} else if(wPhysMemory.getCell(i).getStatus()==0) {
				g.setColor(Color.gray);
			} else {
				g.setColor(Color.blue);
			}
			g.fillRect(x, y, sizeX, sizeY);
			g.setColor(Color.black);
			g.drawRect(x, y, sizeX, sizeY);
			x+=sizeX;
		}		
	}

}
