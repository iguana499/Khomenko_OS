import java.util.ArrayList;
import java.util.Random;

public class Process {
	private String name;
	private ArrayList<Flow> flows=new ArrayList<Flow>();
	public Process(String name)
	{
		this.name=name;
		Random rnd=new Random();
		createFlows(rnd.nextInt(10));
	}
	
	private void createFlows(int count)
	{
		for(int i=0;i<count;i++)
		{
			flows.add(new Flow(name+" "+String.valueOf(i)));
		}
	}
	public String getName()
	{
		return name;
	}
	
	public ArrayList<Flow> getFlows()
	{
		return flows;
	}
}
