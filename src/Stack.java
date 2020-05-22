

public class Stack {
	private Argument[] a;
	private int topIndex=-1;
	public Stack(int size)
	{
		a=new Argument[size];
	}
	public boolean isEmpty()
	{
		return(topIndex==-1)?true:false;
	}
	public void Push(Argument f)
	{
		topIndex++;
		a[topIndex]=f;
	}
	
	public Argument Pop() throws Exception
	{
		if(isEmpty())
		{
			throw new Exception("В стеке пусто");
		}
		else
			topIndex--;
		return a[topIndex+1];
	}
	
}
