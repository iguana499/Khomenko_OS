import java.util.Random;

public class Flow {
	private String name;
	private int currentTime;
	private int finishTime;
	private int priority;
	
	public Flow(String name, int priority)
	{
		this.name=name;
		this.priority=priority;
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

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}
