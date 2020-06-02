import java.util.Deque;

public class FlowManager {
private final Deque<Flow> flows;
public FlowManager(Deque<Flow>flows)
{
	this.flows=flows;
}

public Flow nextFlow()
{
	return flows.poll();
}

public boolean WorkOver()
{
	for(Flow flow:flows)
	{
		if(!flow.startWork(0))
			return false;
	}
	return true;
}
public void flowEnds(Flow flow)
{
	flows.offer(flow);
}
}
