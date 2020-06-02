import java.util.Random;

public class Flow {
	private String name;
	private int currentTime;
	private int finishTime;
	
	public Flow(String name)
	{
		this.name=name;
		generateFlow();
		printFlow();
	}
	
	private void generateFlow()
	{
		Random rand=new Random();
		finishTime=rand.nextInt(10)+1;
	}
	
	public void printFlow()
	{
		System.out.print("Flow name:"+name+" Current Time:"+currentTime+" Finish Time:"+finishTime+"\n");
	}
	
	public Boolean startWork(int WorkingTime)
	{
		if(currentTime+WorkingTime>=finishTime)
		{
			currentTime=finishTime;
			return true;
		}
		else
		{
			currentTime+=WorkingTime;
			return false;
		}
	}
	public String getName()
	{
		return name;
	}
}
