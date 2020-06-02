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
	CreateProcess(rand.nextInt(10));
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
			int max=flows.getFirst().getPriority();
			Flow flowInWork=flows.getFirst();
			for (Flow flow : flows) {
						if(max<flow.getPriority()) {
							max=flow.getPriority();
							flowInWork=flow;
						}
			}
			flowInWork.setPriority(flowInWork.getPriority()-1);
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
			Random rand=new Random();
			for (Flow flow : flows) {
				flow.setPriority(rand.nextInt(10));
				}
			}
		}
}
