import java.util.ArrayList;
import java.util.Objects;

import javax.swing.tree.DefaultMutableTreeNode;

public class fileManager {
	private File mainFile = new File("root",null,true);
	private File selected = mainFile;
	private File fileForCopy;
	private File fileForMove;
	private DefaultMutableTreeNode treeFile = new DefaultMutableTreeNode(mainFile);
	private DefaultMutableTreeNode selectedNodeTree = treeFile;
	private WorkPhysMemory wPhysMemory;
	
	public fileManager(WorkPhysMemory wPhisMemory) {
		this.wPhysMemory = wPhisMemory;
		mainFile.setSize(1);
		wPhisMemory.allocateMemoryForFile(mainFile);
	}

	public File getRootFile() {
		return mainFile;
	}

	public void setRootFile(File rootFile) {
		this.mainFile = rootFile;
	}

	public File getSelected() {
		return selected;
	}

	public void setSelected(File selected) {
		this.selected = selected;
	}

	public File getForCopy() {
		return fileForCopy;
	}

	public void setForCopy(File forCopy) {
		this.fileForCopy = forCopy;
	}

	public File getForMove() {
		return fileForMove;
	}

	public void setForMove(File forMove) {
		this.fileForMove = forMove;
	}

	public DefaultMutableTreeNode getTreeFile() {
		return treeFile;
	}

	public void setTreeFile(DefaultMutableTreeNode treeFile) {
		this.treeFile = treeFile;
	}

	public DefaultMutableTreeNode getSelectedNodeTree() {
		return selectedNodeTree;
	}

	public void setSelectedNodeTree(DefaultMutableTreeNode selectedNodeTree) {
		this.selectedNodeTree = selectedNodeTree;
		UpdateSelect();
	}

	private void UpdateSelect() {
		this.selected = (File) selectedNodeTree.getUserObject();	
	}

	public File Copy() {
		return fileForCopy = selected;		
	}
	
	public boolean paste() {
		if(selected.getFolder()) {
			try {
				File newFile = fileForCopy.clone();
				newFile.setParrent(selected);
				selected.getChilds().add(newFile);
				wPhysMemory.allocateMemoryForFile(newFile);
				if (newFile.getFolder()) {
					copyFiles(newFile);
				}				
			} catch (CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
		
	}
	
	public void copyFiles(File newFile) {
		for (File file : newFile.getChilds()) {
			wPhysMemory.allocateMemoryForFile(file);
			if(file.getFolder()) {
				copyFiles(file);
			}
		}
	}
	
	public void startDelForlder() {
		wPhysMemory.clearMemory(selected);
		delForder(selected.getChilds());
	}

	public void delForder(ArrayList<File> files) {
		for (File file : files) {
			if(file.getFolder()) {
				delForder(file.getChilds());
			}
			wPhysMemory.clearMemory(file);
		}
	}
	
	public Boolean CreateNewFile(boolean b,String stringNameFile,int fileSize) {
		if(selected.getFolder()) {
			File newFile = new File(stringNameFile+"",selected,b);	
			if(b) {
				newFile.setSize(1);
			} else {
				newFile.setSize(fileSize);			
			}
			wPhysMemory.allocateMemoryForFile(newFile);
			selected.getChilds().add(newFile);
			selectedNodeTree.add(new DefaultMutableTreeNode(newFile));
			return true;
		} else {
			return false;
		}
	}
	
	public boolean move() {
		if(Objects.isNull(fileForMove)) {
			fileForMove = selected;			
		} else {
			if(selected.getFolder()) {
				fileForMove.getParrent().getChilds().remove(fileForMove);
				fileForMove.setParrent(selected);
				fileForMove.getParrent().getChilds().add(fileForMove);
				fileForMove = null;
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
	
	public boolean delete() {
		if(selected == mainFile) {
			return false;
		} else {
			selected.getParrent().getChilds().remove(selected);
			if (selected.getFolder()) {
				startDelForlder();;
			} else {
				wPhysMemory.clearMemory(selected);
			}
		}
		return false;
	}
}
