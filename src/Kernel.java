import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class Kernel {
	private final Deque<Flow> flows=new ArrayDeque<Flow>();
	private final Deque<Process> processes=new ArrayDeque<Process>();
	private final int time=1;
	private FlowManager flowManager=new FlowManager(flows);
	
	
	public Kernel()
	{
	Random rand=new Random();
	CreateProcess(rand.nextInt(3));
	getAllFlows();
	}
	private void getAllFlows()
	{
		for(Process process:processes)
		{
			flows.addAll(process.getFlows());
		}
	}
	
	public void CreateProcess(int count)
	{
	for(int i=0;i<count;i++)
	{
		processes.add(new Process(String.valueOf(i)));
	}
}

	public void KernelWorkStart()
	{
		while (!flowManager.WorkOver())
		{
		Flow flowInWork=flowManager.nextFlow();
			if(flowInWork==null)
		{
				System.out.println(" Work ended");
		}
		System.out.println(flowInWork.getName()+" Working");
		if(flowInWork.startWork(time))
		{
			System.out.println(flowInWork.getName()+" Work ended");
		}
		else
		{
			System.out.println(flowInWork.getName()+" Not enough Time, switching flow");
			flowManager.flowEnds(flowInWork);
		}
		}
	}
	
	
}
